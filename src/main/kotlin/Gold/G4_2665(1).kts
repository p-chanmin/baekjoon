import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Pos(val i: Int, val j: Int)
data class Check(val visited: Boolean, val change: Int)

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()
    val map: Array<IntArray> = Array(N) { IntArray(N) { 0 } }
    val visit: Array<Array<Check>> = Array(N) { Array(N) { Check(false, 0) } }

    val deque: Deque<Pos> = LinkedList()

    repeat(N) { i ->
        br.readLine().forEachIndexed { j, v ->
            val value = Character.getNumericValue(v)
            map[i][j] = value
        }
    }

    val di = listOf(0, 1, -1, 0)
    val dj = listOf(1, 0, 0, -1)

    deque.add(Pos(0, 0))
    visit[0][0] = Check(true, 0)

    while (deque.isNotEmpty()) {
        val p = deque.poll()

        (0..3).forEach {
            val ni = p.i + di[it]
            val nj = p.j + dj[it]

            if (ni in map.indices && nj in map.indices) {
                when (visit[ni][nj].visited) {
                    false -> {
                        if (map[ni][nj] == 1) {
                            deque.add(Pos(ni, nj))
                            visit[ni][nj] = Check(true, visit[p.i][p.j].change)
                        } else {
                            deque.add(Pos(ni, nj))
                            visit[ni][nj] = Check(true, visit[p.i][p.j].change + 1)
                        }
                    }

                    true -> {
                        if (map[ni][nj] == 1 && visit[ni][nj].change > visit[p.i][p.j].change) {
                            deque.add(Pos(ni, nj))
                            visit[ni][nj] = Check(true, visit[p.i][p.j].change)
                        } else if (map[ni][nj] == 0 && visit[ni][nj].change > visit[p.i][p.j].change + 1) {
                            deque.add(Pos(ni, nj))
                            visit[ni][nj] = Check(true, visit[p.i][p.j].change + 1)
                        }
                    }
                }
            }
        }
    }

    bw.write("${visit[N - 1][N - 1].change}")
    bw.flush()
    bw.close()
}


main()