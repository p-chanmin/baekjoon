import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

data class Pos(val i: Int, val j: Int)

val di = listOf(0, 0, 0, 1, -1)
val dj = listOf(0, 1, -1, 0, 0)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    val pos = Array(N + 1) { Pos(0, 0) }
    val dp = Array(N + 1) { LongArray(5) { Long.MAX_VALUE } }

    br.readLine().split(" ").map { it.toInt() }.let {
        pos[0] = Pos(it[0], it[1])
    }

    repeat(N) { n ->
        br.readLine().split(" ").map { it.toInt() }.let {
            pos[n + 1] = Pos(it[0], it[1])
        }
    }

    dp[0][0] = 0
    (1..4).forEach {
        dp[0][it] = 1
    }

    for (i in 0 until N) {
        for (j in 0..4) {
            val pi = pos[i].i + di[j]
            val pj = pos[i].j + dj[j]
            for (k in 0..4) {
                val di = pos[i + 1].i + di[k]
                val dj = pos[i + 1].j + dj[k]

                dp[i + 1][k] = minOf(dp[i + 1][k], dp[i][j] + calPos(pi, pj, di, dj))
            }
        }
    }

    bw.write("${dp.last().min()}")

    bw.flush()
    bw.close()
}

fun calPos(pi: Int, pj: Int, di: Int, dj: Int): Long {
    return abs(pi - di).toLong() + abs(pj - dj).toLong()
}


main()