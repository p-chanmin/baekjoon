import java.io.*
import java.util.*
import kotlin.math.max


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    var arr = br.readLine().split(" ").map{ it.toInt() }
    var dp: IntArray = IntArray(N){ 0 }

    for(i in arr.indices){
        dp[i] = 1
        for(j in i-1 downTo 0){
            if(arr[j] < arr[i]) dp[i] = max(dp[i], dp[j]+1)
        }
    }

    bw.write("${dp.maxOf { it }}")

    bw.flush()
    bw.close()
}



main()