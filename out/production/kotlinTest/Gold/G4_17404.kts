import java.io.*
import java.util.*
import kotlin.math.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var N = br.readLine().toInt()

    var rgb: Array<IntArray> = Array(N+1){ IntArray(3) }
    var dp: Array<IntArray> = Array(N+1){ IntArray(3) }

    repeat(N){ i ->
        br.readLine().split(" ").forEachIndexed { j, s ->
            rgb[i+1][j] = s.toInt()
        }
    }

    var result = 1000001

    fun dpRGB(code: Int){
        (0 .. 2).forEach { if(it == code) dp[1][it] = rgb[1][it] else dp[1][it] = 1000001 }
        for(i in 2 .. N){
            dp[i][0] = rgb[i][0] + min(dp[i-1][1], dp[i-1][2])
            dp[i][1] = rgb[i][1] + min(dp[i-1][0], dp[i-1][2])
            dp[i][2] = rgb[i][2] + min(dp[i-1][0], dp[i-1][1])
        }
    }

    dpRGB(0)
    result = minOf(result, dp.last()[1], dp.last()[2])
    dpRGB(1)
    result = minOf(result, dp.last()[0], dp.last()[2])
    dpRGB(2)
    result = minOf(result, dp.last()[0], dp.last()[1])

    bw.write("${result}")

    bw.flush()
    bw.close()
}


main()