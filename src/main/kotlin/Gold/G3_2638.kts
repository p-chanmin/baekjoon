import java.io.*
import java.util.*
import kotlin.collections.ArrayList

data class Pos(var i: Int, var j: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (N, M) = br.readLine().split(" ").map { it.toInt() }

    var cheese: Array<IntArray> = Array(N){ IntArray(M){ 0 } }

    (0 until N).forEach { i ->
        br.readLine().split(" ").forEachIndexed { j, v ->
            cheese[i][j] = v.toInt()
        }
    }

    fun spreadAir(p: Pos){
        var queue: Deque<Pos> = LinkedList()
        cheese[p.i][p.j] = 2
        queue.add(p)

        while(queue.isNotEmpty()){
            var q = queue.poll()

            if(q.i-1 >= 0 && q.i-1 < N && q.j >= 0 && q.j < M && cheese[q.i-1][q.j] == 0){
                queue.add(Pos(q.i-1, q.j))
                cheese[q.i-1][q.j] = 2
            }
            if(q.i+1 >= 0 && q.i+1 < N && q.j >= 0 && q.j < M && cheese[q.i+1][q.j] == 0){
                queue.add(Pos(q.i+1, q.j))
                cheese[q.i+1][q.j] = 2
            }
            if(q.i >= 0 && q.i < N && q.j-1 >= 0 && q.j-1 < M && cheese[q.i][q.j-1] == 0){
                queue.add(Pos(q.i, q.j-1))
                cheese[q.i][q.j-1] = 2
            }
            if(q.i >= 0 && q.i < N && q.j+1 >= 0 && q.j+1 < M && cheese[q.i][q.j+1] == 0){
                queue.add(Pos(q.i, q.j+1))
                cheese[q.i][q.j+1] = 2
            }
        }

    }

    var answer = 0
    var disappear: ArrayList<Pos> = arrayListOf(Pos(0,0))

    while(disappear.isNotEmpty()){

        for(d in disappear){
            spreadAir(d)
        }
        disappear = arrayListOf()

        for(i in 0 until N){
            for(j in 0 until M){
                if(cheese[i][j] == 1){
                    if(listOf(cheese[i-1][j], cheese[i+1][j], cheese[i][j-1], cheese[i][j+1]).count{ it == 2 } >= 2) disappear.add(Pos(i, j))
                }
            }
        }
        answer++
    }

    bw.write("${answer-1}")

    bw.flush()
    bw.close()
}



main()