import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    val dp = IntArray(N + 1) { 0 }.apply {
        this[0] = 1
        this[1] = 0
        if (N >= 2) {
            this[2] = 3
        }
    }

    for (i in 3..N) {
        if (i % 2 == 1) {
            dp[i] = 0
        } else {
            for (j in 2..N step 2) {
                if (j == 2) {
                    dp[i] = dp[i - j] * dp[2];
                } else if (i - j >= 0) {
                    dp[i] += dp[i - j] * 2
                }
            }
        }
    }

    bw.write("${dp.last()}")

    bw.flush()
    bw.close()
}


main()