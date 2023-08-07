import java.io.*
import java.util.*
import kotlin.math.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var N = br.readLine().toInt()

    var dp: IntArray = IntArray((N+1)*3){ Int.MAX_VALUE }
    dp[1] = 0

    for(i in 1 until N){
        if(dp[i] != -1){
            dp[i+1] = min(dp[i+1], dp[i] + 1)
            dp[i*2] = min(dp[i*2], dp[i] + 1)
            dp[i*3] = min(dp[i*3], dp[i] + 1)
        }
    }

    bw.write("${dp[N]}")

    bw.flush()
    bw.close()
}


main()