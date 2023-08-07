import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    var dp: Array<IntArray> = Array(N+1){ IntArray(N+1) }

    var nums = arrayListOf<Int>(0)
    nums.addAll(br.readLine().split(" ").map { it.toInt() })

    for(i in 1 .. N){
        dp[i][i] = 1
    }
    for(i in 1 .. N-1){
        dp[i][i+1] = if(nums[i] == nums[i+1]) 1 else 0
    }

    for(j in 3 .. N) for(i in 1 .. j-2){
        dp[i][j] = dp[i+1][j-1] * if(nums[i] == nums[j]) 1 else 0
    }

    repeat(br.readLine().toInt()){
        br.readLine().split(" ").map { it.toInt() }.let {
            bw.write("${dp[it[0]][it[1]]}\n")
        }
    }

    bw.flush()
    bw.close()
}



main()