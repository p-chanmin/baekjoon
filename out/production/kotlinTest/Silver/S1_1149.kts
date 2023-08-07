import java.io.*
import java.util.*
import kotlin.math.min

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    var cost: Array<IntArray> = Array(N){ IntArray(3){ 0 } }

    (0 until N).forEach { i ->
        var c = br.readLine().split(" ").map { it.toInt() }
        if(i == 0) cost[i] = c.toIntArray()
        else{
            (0 .. 2).forEach{
                var t: Int
                when(it){
                    0 -> {
                        t = min(cost[i-1][1], cost[i-1][2])
                    }
                    1 -> {
                        t = min(cost[i-1][0], cost[i-1][2])
                    }
                    else -> {
                        t = min(cost[i-1][0], cost[i-1][1])
                    }
                }
                cost[i][it] = c[it] + t
            }
        }
    }

    bw.write("${cost.last().minOf { it }}")

    bw.flush()
    bw.close()
}



main()