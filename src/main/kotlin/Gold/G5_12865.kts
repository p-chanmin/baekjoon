import java.io.*
import java.util.*
import kotlin.math.max


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val nk = br.readLine().split(" ").map(String::toInt)
    val n = nk[0]
    val k = nk[1]

    val weightArr = IntArray(n)
    val valueArr = IntArray(n)

    for (i in 0 until n){
        val line = br.readLine().split(" ").map(String::toInt)
        weightArr[i] = line[0]
        valueArr[i] = line[1]
    }

    val dp = Array(n+1) { IntArray(k+1) }

    for (i in 1..n){
        for (j in 1..k){
            dp[i][j] = if (weightArr[i-1] <= j)
                maxOf(valueArr[i-1] + dp[i-1][j-weightArr[i-1]], dp[i-1][j])
            else dp[i-1][j]
        }
    }

    println(dp[n][k])
}


main()