import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var N = br.readLine().toInt()

    var dp: Array<Array<LongArray>> = Array(N+1){ Array(10){ LongArray(1024) } }

    for(i in 1 .. 9){
        dp[1][i][1 shl i] = 1
    }
    for(i in 2 .. N){
        for(j in 0 .. 9){
            for(b in 0 until 1024){
                var bit = b or (1 shl j)
                if(j > 0) dp[i][j][bit] += dp[i-1][j-1][b] % 1000000000
                if(j < 9) dp[i][j][bit] += dp[i-1][j+1][b] % 1000000000

            }
        }
    }

    var answer: Long = 0
    for(i in 0 .. 9){
        answer += dp[N][i][1023]
    }

    bw.write("${answer % 1000000000}")

    bw.flush()
    bw.close()
}



main()