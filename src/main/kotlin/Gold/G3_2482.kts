import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

data class Pos(val i: Int, val j: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()
    val K = br.readLine().toInt()

    val dp = Array(N + 1) { IntArray(K + 1) { 0 } }
    for (i in 1..N) {
        dp[i][1] = i
    }

    for (i in 2..K) {
        for (j in i..N) {
            val include = if (j == N) {
                dp[j - 3][i - 1]
            } else {
                dp[j - 2][i - 1]
            }
            val notInclude = dp[j - 1][i]
            dp[j][i] = (notInclude + include) % 1000000003
        }
    }

    bw.write("${dp[N][K]}")

    bw.flush()
    bw.close()
}

main()