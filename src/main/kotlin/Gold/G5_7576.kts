import java.io.*
import java.util.*

data class Pos(var i: Int, var j: Int, var d: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    var (N, M) = br.readLine().split(" ").map { it.toInt() }

    var box: Array<IntArray> = Array(M){ IntArray(N){ 0 } }
    var tomato: ArrayList<Pos> = arrayListOf()

    for(i in 0 until M){
        br.readLine().split(" ").forEachIndexed { j, v ->
            box[i][j] = v.toInt()
            if(box[i][j] == 1) tomato.add(Pos(i, j,0))
        }
    }

    var queue: Deque<Pos> = LinkedList(tomato)
    var q: Pos = Pos(0, 0, 0)

    while(queue.isNotEmpty()){
        q = queue.poll()

        if(q.i+1 >= 0 && q.i+1 < M && q.j >= 0 && q.j < N && box[q.i+1][q.j] == 0){
            queue.add(Pos(q.i+1, q.j, q.d+1))
            box[q.i+1][q.j] = 1
        }
        if(q.i-1 >= 0 && q.i-1 < M && q.j >= 0 && q.j < N && box[q.i-1][q.j] == 0){
            queue.add(Pos(q.i-1, q.j, q.d+1))
            box[q.i-1][q.j] = 1
        }
        if(q.i >= 0 && q.i < M && q.j+1 >= 0 && q.j+1 < N && box[q.i][q.j+1] == 0){
            queue.add(Pos(q.i, q.j+1, q.d+1))
            box[q.i][q.j+1] = 1
        }
        if(q.i >= 0 && q.i < M && q.j-1 >= 0 && q.j-1 < N && box[q.i][q.j-1] == 0){
            queue.add(Pos(q.i, q.j-1, q.d+1))
            box[q.i][q.j-1] = 1
        }
    }

    if(box.flatMap{ it.toList() }.count{ it == 0 } == 0) println(q.d) else println(-1)

}


main()