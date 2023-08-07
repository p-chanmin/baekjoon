import java.io.*
import java.util.*
import kotlin.math.max

data class Pos(var i: Int, var j: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var N = br.readLine().toInt()

    var pictureRGB: Array<Array<Char>> = Array(N){ Array(N){ '.' } }
    var pictureRB: Array<Array<Char>> = Array(N){ Array(N){ '.' } }
    var visit: Array<BooleanArray>

    (0 until N).forEach { i ->
        br.readLine().forEachIndexed { j, c ->
            pictureRGB[i][j] = c
            if(c == 'G') pictureRB[i][j] = 'R'
            else pictureRB[i][j] = c
        }
    }

    fun bfs(p: Array<Array<Char>>): Int{
        visit = Array(N){ BooleanArray(N){ false } }
        var area = 0
        var queue: Deque<Pos> = LinkedList()
        for (i in 0 until N){
            for(j in 0 until N){
                if(!visit[i][j]){
                    area++
                    queue.add(Pos(i, j))
                    visit[i][j] = true
                    while(queue.isNotEmpty()){
                        var q = queue.poll()
                        var now = p[q.i][q.j]
                        if(q.i+1 >= 0 && q.i+1 < N && q.j >= 0 && q.j < N && !visit[q.i+1][q.j] && p[q.i+1][q.j] == now){
                            visit[q.i+1][q.j] = true
                            queue.add(Pos(q.i+1, q.j))
                        }
                        if(q.i-1 >= 0 && q.i-1 < N && q.j >= 0 && q.j < N && !visit[q.i-1][q.j] && p[q.i-1][q.j] == now){
                            visit[q.i-1][q.j] = true
                            queue.add(Pos(q.i-1, q.j))
                        }
                        if(q.i >= 0 && q.i < N && q.j+1 >= 0 && q.j+1 < N && !visit[q.i][q.j+1] && p[q.i][q.j+1] == now){
                            visit[q.i][q.j+1] = true
                            queue.add(Pos(q.i, q.j+1))
                        }
                        if(q.i >= 0 && q.i < N && q.j-1 >= 0 && q.j-1 < N && !visit[q.i][q.j-1] && p[q.i][q.j-1] == now){
                            visit[q.i][q.j-1] = true
                            queue.add(Pos(q.i, q.j-1))
                        }
                    }
                }
            }
        }

        return area
    }

    bw.write("${bfs(pictureRGB)} ${bfs(pictureRB)}")

    bw.flush()
    bw.close()
}


main()