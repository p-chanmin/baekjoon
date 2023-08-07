import java.io.*
import java.util.*

data class Pos(var r: Int, var c: Int, var d: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var N = br.readLine().toInt()

    var (r1, c1, r2, c2) = br.readLine().split(" ").map { it.toInt() }

    var visit: Array<BooleanArray> = Array(N){ BooleanArray(N){ false } }
    var queue: Deque<Pos> = LinkedList()
    var q: Pos = Pos(r1, c1, 0)
    visit[r1][c1] = true
    queue.add(q)

    var dr = listOf<Int>(-2, -2, 0, 0, 2, 2)
    var dc = listOf<Int>(-1, 1, -2, 2, -1, 1)

    while(queue.isNotEmpty()){
        q = queue.poll()

        if(q.r == r2 && q.c == c2) break

        for(i in 0 .. 5){
            var nr = q.r + dr[i]
            var nc = q.c + dc[i]
            if(nr in 0 until N && nc in 0 until N && !visit[nr][nc]){
                visit[nr][nc] = true
                queue.add(Pos(nr, nc, q.d+1))
            }
        }
    }

    bw.write("${if(q.r == r2 && q.c == c2) q.d else -1}")

    bw.flush()
    bw.close()
}



main()