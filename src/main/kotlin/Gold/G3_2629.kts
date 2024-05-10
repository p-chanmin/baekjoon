import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    val weight = br.readLine().split(" ").map { it.toInt() }

    val ball = br.readLine().toInt()

    val balls = br.readLine().split(" ").map { it.toInt() }

    val maxWeight = weight.sum()

    val dp = Array(N) { BooleanArray(maxWeight + 1) { false } }

    dp[0][weight[0]] = true

    for (i in 1 until N) {
        for (j in 1 until maxWeight) {
            if (dp[i - 1][j]) {
                dp[i][j] = true
                dp[i][j + weight[i]] = true
                dp[i][abs(j - weight[i])] = true
                dp[i][weight[i]] = true
            }
        }
    }

    balls.forEach {
        if (it > maxWeight) {
            bw.write("N ")
        } else {
            bw.write(if (dp[N - 1][it]) "Y " else "N ")
        }
        bw.flush()
    }

    bw.close()
}



main()