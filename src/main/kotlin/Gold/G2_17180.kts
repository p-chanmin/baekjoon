import java.io.*
import kotlin.math.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (N, M) = br.readLine().split(" ").map { it.toInt() }

    val text1 = br.readLine()
    val text2 = br.readLine()

    val dp = Array(N) { IntArray(M) { 0 } }

    dp[0][0] = abs(text1[0] - text2[0])

    (1 until N).forEach {
        dp[it][0] = dp[it - 1][0] + abs(text1[it] - text2[0])
    }
    (1 until M).forEach {
        dp[0][it] = dp[0][it - 1] + abs(text1[0] - text2[it])
    }

    for (i in 1 until N) {
        for (j in 1 until M) {
            dp[i][j] = minOf(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + abs(text1[i] - text2[j])
        }
    }

    val lst: List<Int>
    val s: Set<Int>

    bw.write("${dp[N - 1][M - 1]}")

    bw.flush()
    bw.close()
}


main()