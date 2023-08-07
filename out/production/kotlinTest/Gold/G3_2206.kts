import java.io.*
import java.util.*

data class Pos(var i: Int, var j: Int, var move: Int, var broken: Boolean)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, M) = br.readLine().split(" ").map { it.toInt() }

    var m: Array<String> = Array(N){ "" }
    var visit: Array<Array<BooleanArray>> = Array(2){ Array(N){ BooleanArray(M){ false } } }

    (0 until N).forEach { m[it] = br.readLine() }

    var queue: Deque<Pos> = LinkedList()
    var q: Pos = Pos(0, 0, 1, false)
    visit[0][0][0] = true
    queue.add(q)

    var di = listOf(-1, 1, 0, 0)
    var dj = listOf(0, 0, -1, 1)

    while(queue.isNotEmpty()){
        q = queue.poll()

        if(q.i == N-1 && q.j == M-1) break

        for(i in 0 .. 3){
            var ni = q.i + di[i]
            var nj = q.j + dj[i]
            var nv = if(q.broken) 1 else 0

            if(ni in 0 until N && nj in 0 until M && !visit[nv][ni][nj]){
                if(m[ni][nj] == '0'){
                    visit[nv][ni][nj] = true
                    queue.add(Pos(ni, nj, q.move+1, q.broken))
                }
                else if(!q.broken && m[ni][nj] == '1'){
                    visit[1][ni][nj] = true
                    queue.add(Pos(ni, nj, q.move+1, true))
                }
            }
        }

    }

    if(q.i == N-1 && q.j == M-1) bw.write("${q.move}")
    else bw.write("-1")

    bw.flush()
    bw.close()
}


main()