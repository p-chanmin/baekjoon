import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Pos(val i: Int, val j: Int, val cost: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var tc = 0

    while (true) {
        tc++
        val N = br.readLine().toInt()
        if (N == 0) break

        val world = Array(N) { IntArray(N) { 0 } }
        val cost = Array(N) { IntArray(N) { Int.MAX_VALUE } }

        repeat(N) { i ->
            br.readLine().split(" ").map { it.toInt() }.forEachIndexed { j, v ->
                world[i][j] = v
            }
        }

        cost[0][0] = world[0][0]
        val queue = PriorityQueue<Pos> { a, b -> a.cost - b.cost }
        queue.add(Pos(0, 0, cost[0][0]))

        val di = listOf(0, 0, 1, -1)
        val dj = listOf(1, -1, 0, 0)

        while (queue.isNotEmpty()) {
            val q = queue.poll()

            if (q.cost > cost[q.i][q.j]) {
                continue
            }

            (0..3).forEach { k ->
                val ni = q.i + di[k]
                val nj = q.j + dj[k]

                if (ni in world.indices && nj in world.indices) {
                    if (cost[ni][nj] > cost[q.i][q.j] + world[ni][nj]) {
                        cost[ni][nj] = cost[q.i][q.j] + world[ni][nj]
                        queue.add(Pos(ni, nj, cost[ni][nj]))
                    }
                }
            }
        }

        bw.write("Problem $tc: ${cost[N - 1][N - 1]}\n")
        bw.flush()

    }
    bw.close()
}


main()