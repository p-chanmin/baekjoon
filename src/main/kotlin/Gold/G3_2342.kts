import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

val maxCost = 500000

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val cost = listOf(
        listOf(0, 2, 2, 2, 2),
        listOf(0, 1, 3, 4, 3),
        listOf(0, 3, 1, 3, 4),
        listOf(0, 4, 3, 1, 3),
        listOf(0, 3, 4, 3, 1),
    )


    val ddr = br.readLine().split(" ").map { it.toInt() }

    val dp = Array(ddr.size) { Array(5) { IntArray(5) { maxCost } } }

    dp[0][0][0] = 0

    for (i in 0 until ddr.size - 1) {

        val nextPoint = ddr[i]
        for (l in 0..4) {
            for (r in 0..4) {
                if (dp[i][l][r] != maxCost) {
                    dp[i + 1][nextPoint][r] = min(dp[i + 1][nextPoint][r], (dp[i][l][r] + cost[l][nextPoint]))
                    dp[i + 1][l][nextPoint] = min(dp[i + 1][l][nextPoint], (dp[i][l][r] + cost[r][nextPoint]))
                }
            }
        }
    }

    var answer = maxCost
    dp.last().forEach {
        it.forEach {
            if (it != maxCost) {
                answer = min(answer, it)
            }
        }
    }
    bw.write("$answer")

    bw.flush()
    bw.close()
}


main()