import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var N = br.readLine().toInt()

    var dp: IntArray = IntArray(11){ 0 }
    dp[1] = 1
    dp[2] = 2
    dp[3] = 4

    for(i in 4 .. 10){
        dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
    }

    repeat(N){
        bw.write("${dp[br.readLine().toInt()]}\n")
    }

    bw.flush()
    bw.close()
}


main()