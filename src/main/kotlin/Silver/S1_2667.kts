import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Pos(val i: Int, val j: Int)

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()
    val map: Array<IntArray> = Array(N) { IntArray(N) { 0 } }
    val visit: Array<BooleanArray> = Array(N) { BooleanArray(N) { false } }

    val deque: Deque<Pos> = LinkedList()

    repeat(N) { i ->
        br.readLine().forEachIndexed { j, v ->
            val value = Character.getNumericValue(v)
            map[i][j] = value
            if (value == 0) {
                visit[i][j] = true
            }
        }
    }

    val di = listOf(0, 0, 1, -1)
    val dj = listOf(1, -1, 0, 0)

    fun bfs(i: Int, j: Int): Int {
        var result = 0
        visit[i][j] = true
        deque.add(Pos(i, j))
        result += 1

        while (deque.isNotEmpty()) {
            val p = deque.poll()
            (0..3).forEach {
                val ni = p.i + di[it]
                val nj = p.j + dj[it]
                if (ni in 0 until N && nj in 0 until N && !visit[ni][nj]) {
                    result += 1
                    deque.add(Pos(ni, nj))
                    visit[ni][nj] = true
                }
            }
        }
        return result
    }

    val answer: MutableList<Int> = mutableListOf()

    for (i in 0 until N) for (j in 0 until N) {
        if (map[i][j] == 1 && !visit[i][j]) {
            answer.add(bfs(i, j))
        }
    }

    answer.sortBy { it }
    bw.write("${answer.size}\n")
    answer.forEach {
        bw.write("$it\n")
    }

    bw.flush()
    bw.close()
}


main()