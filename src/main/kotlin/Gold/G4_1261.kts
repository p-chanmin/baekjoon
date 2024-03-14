import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Pos(val i: Int, val j: Int, val broken: Int)

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (N, M) = br.readLine().split(" ").map { it.toInt() }
    val mat = Array(M) { "" }
    val visit = Array(M) { BooleanArray(N) { false } }

    repeat(M) { i ->
        mat[i] = br.readLine()
    }

    val queue = PriorityQueue<Pos> { a, b -> a.broken - b.broken }
    visit[0][0] = true
    queue.add(Pos(0, 0, 0))

    val di = listOf(0, 0, 1, -1)
    val dj = listOf(1, -1, 0, 0)

    var answer = 0

    while (queue.isNotEmpty()) {
        val q = queue.poll()
        if (q.i == M - 1 && q.j == N - 1) {
            answer = q.broken
        }

        (0..3).forEach {
            val ni = q.i + di[it]
            val nj = q.j + dj[it]

            if (ni in 0 until M && nj in 0 until N) {

                if (!visit[ni][nj] && mat[ni][nj] == '0') {
                    visit[ni][nj] = true
                    queue.add(Pos(ni, nj, q.broken))
                } else if (!visit[ni][nj] && mat[ni][nj] == '1') {
                    visit[ni][nj] = true
                    queue.add(Pos(ni, nj, q.broken + 1))
                }
            }
        }
    }
    bw.write("$answer")
    bw.flush()
    bw.close()
}


main()