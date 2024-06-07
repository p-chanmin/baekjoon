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

    val board = Array(5) { CharArray(5) { ' ' } }

    repeat(5) { i ->
        br.readLine().forEachIndexed { j, v ->
            board[i][j] = v
        }
    }

    var answer = 0
    val visited = BooleanArray(25) { false }

    fun checkCombination(l: List<Int>): Boolean {
        val v = Array(5) { BooleanArray(5) { false } }
        val visit = Array(5) { BooleanArray(5) { false } }
        val queue: Deque<Pos> = LinkedList()
        queue.add(Pos(l.first() / 5, l.first() % 5))
        visit[l.first() / 5][l.first() % 5] = true
        l.forEach {
            v[it / 5][it % 5] = true
        }

        var sz = 1

        while (queue.isNotEmpty()) {
            val q = queue.poll()

            for (k in 0..3) {
                val ni = q.i + di[k]
                val nj = q.j + dj[k]
                if (ni in 0..4 && nj in 0..4 && v[ni][nj] && !visit[ni][nj]) {
                    sz++
                    visit[ni][nj] = true
                    queue.add(Pos(ni, nj))
                }
            }
        }

        if (sz != 7) {
            return false
        }

        var s = 0

        for (k in l) {
            if (board[k / 5][k % 5] == 'S') {
                s++
            }
        }

        return s >= 4
    }

    fun dfs(i: Int, l: List<Int>) {
        if (l.size == 7 && checkCombination(l)) {
            answer++
        }


        for (j in i + 1..24) {
            if (!visited[j]) {
                visited[j] = true
                dfs(j, l + listOf(j))
                visited[j] = false
            }
        }
    }

    for (i in 0..24) {
        visited[i] = true
        dfs(i, listOf(i))
        visited[i] = false
    }

    bw.write("$answer")

    bw.flush()
    bw.close()
}

main()
