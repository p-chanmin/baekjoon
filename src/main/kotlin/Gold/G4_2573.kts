import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Pos(val i: Int, val j: Int)

val di = listOf(0, 0, 1, -1)
val dj = listOf(1, -1, 0, 0)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (N, M) = br.readLine().split(" ").map { it.toInt() }

    val board = Array(N) { IntArray(M) { 0 } }

    var ices = mutableListOf<Pos>()

    repeat(N) { i ->
        br.readLine().split(" ").map { it.toInt() }.forEachIndexed { j, v ->
            board[i][j] = v
            if (v != 0) {
                ices.add(Pos(i, j))
            }
        }
    }

    fun year() {
        val newIces = mutableListOf<Pos>()
        val addBoard = Array(N) { IntArray(M) { 0 } }
        for (ice in ices) {
            for (k in 0..3) {
                val ni = ice.i + di[k]
                val nj = ice.j + dj[k]
                if (board[ni][nj] == 0) {
                    addBoard[ice.i][ice.j] += 1
                }
            }
        }
        for (ice in ices) {
            if (board[ice.i][ice.j] - addBoard[ice.i][ice.j] <= 0) {
                board[ice.i][ice.j] = 0
            } else {
                board[ice.i][ice.j] -= addBoard[ice.i][ice.j]
                newIces.add(ice)
            }
        }
        ices = newIces
    }


    fun bfs(): Int {
        if (ices.isEmpty()) {
            return 0
        }
        val visited = Array(N) { BooleanArray(M) { false } }
        val queue: Deque<Pos> = LinkedList()

        var cnt = 0

        for (ice in ices) {
            if (!visited[ice.i][ice.j]) {
                visited[ice.i][ice.j] = true
                queue.add(ice)
                cnt += 1
                while (queue.isNotEmpty()) {
                    val q = queue.poll()
                    for (k in 0..3) {
                        val ni = q.i + di[k]
                        val nj = q.j + dj[k]
                        if (!visited[ni][nj] && board[ni][nj] != 0) {
                            visited[ni][nj] = true
                            queue.add(Pos(ni, nj))
                        }
                    }
                }
            }
        }

        return cnt
    }

    var answer = 0
    while (true) {
        year()
        answer += 1
        val pieces = bfs()
        if (pieces == 0) {
            answer = 0
            break
        } else if (pieces >= 2) {
            break
        }
    }

    bw.write("$answer")

    bw.flush()
    bw.close()
}



main()