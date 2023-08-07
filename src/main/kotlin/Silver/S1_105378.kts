import java.io.*
import java.util.*
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val T = br.readLine().toInt()

    repeat(T){

        var N = br.readLine().toInt()
        var sticker: Array<IntArray> = Array(2){ IntArray(N+2){ 0 } }
        (0 .. 1).forEach{ i -> br.readLine().split(" ").forEachIndexed { j, v -> sticker[i][j+2] = v.toInt() } }

        var dp: Array<IntArray> = Array(2){ IntArray(N+2){ 0 } }

        for(i in 2 until N+2){
            dp[0][i] = sticker[0][i] + max(dp[1][i-1], dp[1][i-2])
            dp[1][i] = sticker[1][i] + max(dp[0][i-1], dp[0][i-2])
        }
        bw.write("${max(dp[0].last(), dp[1].last())}\n")
    }


    bw.flush()
    bw.close()
}



main()