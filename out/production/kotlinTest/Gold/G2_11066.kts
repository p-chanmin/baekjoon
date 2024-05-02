import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val tc = br.readLine().toInt()

    repeat(tc) {

        val K = br.readLine().toInt()

        val files = IntArray(K)
        br.readLine().split(" ").map { it.toInt() }.forEachIndexed { i, v ->
            files[i] = v
        }

        val sum = IntArray(K + 1)
        val dp = Array(K) { IntArray(K) }

        (1..K).forEach {
            sum[it] = sum[it - 1] + files[it - 1]
        }

        for (i in 1 until K) {
            for (j in 0 until K - i) {
                val k = i + j
                dp[j][k] = Int.MAX_VALUE
                for (m in j until k) {
                    dp[j][k] = minOf(dp[j][k], dp[j][m] + dp[m + 1][k] + sum[k + 1] - sum[j])
                }
            }
        }
        bw.write("${dp[0].last()}\n")
        bw.flush()
    }

    bw.close()
}


main()