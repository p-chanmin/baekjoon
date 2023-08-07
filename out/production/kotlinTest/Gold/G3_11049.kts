import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.*

data class Mat(val a: Int, val b: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var N = br.readLine().toInt()

    var dp: Array<IntArray> = Array(N){ IntArray(N) }
    var mat: ArrayList<Mat> = arrayListOf()

    repeat(N){ br.readLine().split(" ").map { it.toInt() }.let { mat.add(Mat(it[0], it[1])) } }

    for(c in 0 until N-1){
        for(i in 0 until N-1-c){
            var j = i+1+c
            dp[i][j] = Int.MAX_VALUE
            for(m in i until j){
                dp[i][j] = min(dp[i][j], dp[i][m] + dp[m+1][j] + mat[i].a * mat[m].b * mat[j].b)
            }
        }
    }

    bw.write("${dp.first().last()}")

    bw.flush()
    bw.close()
}


main()