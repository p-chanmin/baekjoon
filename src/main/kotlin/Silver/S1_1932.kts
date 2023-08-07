import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var N = br.readLine().toInt()

    var dp: Array<IntArray> = Array(N+1){ IntArray(N+1) }

    (1 .. N).forEach { i ->
        br.readLine().split(" ").forEachIndexed { j, v ->
            dp[i][j+1] = v.toInt() + maxOf(dp[i-1][j], dp[i-1][j+1])
        }
    }

    bw.write("${dp.last().maxOf { it }}")

    bw.flush()
    bw.close()
}



main()