import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Pos(val change: Int, val i: Int, val j: Int)

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()
    val map: Array<IntArray> = Array(N) { IntArray(N) { 0 } }
    val visit: Array<Array<BooleanArray>> = Array(N * N) { Array(N) { BooleanArray(N) { false } } }

    val deque = PriorityQueue<Pos> { a, b -> a.change - b.change }

    repeat(N) { i ->
        br.readLine().forEachIndexed { j, v ->
            val value = Character.getNumericValue(v)
            map[i][j] = value
        }
    }

    val di = listOf(0, 1, -1, 0)
    val dj = listOf(1, 0, 0, -1)

    deque.add(Pos(0, 0, 0))
    visit[0][0][0] = true

    while (deque.isNotEmpty()) {
        val p = deque.poll()
        if (p.i == N - 1 && p.j == N - 1) {
            bw.write("${p.change}")
            break
        }

        (0..3).forEach {
            val ni = p.i + di[it]
            val nj = p.j + dj[it]

            if (ni in 0 until N && nj in 0 until N && !visit[p.change][ni][nj]) {
                if (map[ni][nj] == 1) {
                    deque.add(Pos(p.change, ni, nj))
                    visit[p.change][ni][nj] = true
                } else {
                    if (p.change + 1 < N * N) {
                        deque.add(Pos(p.change + 1, ni, nj))
                        visit[p.change + 1][ni][nj] = true
                    }
                }
            }
        }
    }

    bw.flush()
    bw.close()
}


main()