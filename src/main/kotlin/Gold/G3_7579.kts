import java.io.*
import java.util.*
import kotlin.math.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, M) = br.readLine().split(" ").map { it.toInt() }

    var memory = br.readLine().split(" ").map { it.toInt() }
    var costs = br.readLine().split(" ").map { it.toInt() }

    var res = costs.sum()
    var dp: Array<IntArray> = Array(N+1){ IntArray(res+1) }

    for(i in 1 .. N){
        var cost = costs[i-1]
        var mem = memory[i-1]
        for(j in 1 .. dp[0].lastIndex){
            if(j < cost){
                dp[i][j] = dp[i-1][j]
            }
            else{
                dp[i][j] = max(dp[i-1][j], mem + dp[i-1][j - cost])
            }
            if(dp[i][j] >= M && res > j) res = j
        }
    }

    bw.write("${res}")

    bw.flush()
    bw.close()
}


main()