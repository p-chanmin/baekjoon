import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var str1 = br.readLine()
    var str2 = br.readLine()

    var dp: Array<IntArray> = Array(str1.length+1){ IntArray(str2.length+1) }

    for(i in 1 .. str1.length) for(j in 1 .. str2.length){
        if(str1[i-1] == str2[j-1]) dp[i][j] = dp[i-1][j-1] + 1
        else dp[i][j] = max(dp[i-1][j], dp[i][j-1])
    }

    var l = dp[str1.length][str2.length]
    bw.write("${l}\n")

    if(l != 0){

        var answer = ""

        var y = str1.length
        var x = str2.length

        while(x != 0 && y != 0){
            if(dp[y-1][x] == dp[y][x]) y--
            else if(dp[y][x-1] == dp[y][x]) x--
            else{
                answer += str1[y-1]
                x--
                y--
            }
        }

        bw.write(answer.reversed())
    }

    bw.flush()
    bw.close()
}



main()