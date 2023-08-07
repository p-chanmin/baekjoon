import java.io.*
import java.util.*
import kotlin.math.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var N = br.readLine().toInt()

    var b: Array<IntArray> = Array(N){ IntArray(3) }

    var dpMax: Array<IntArray> = Array(N){ IntArray(3) }
    var dpMin: Array<IntArray> = Array(N){ IntArray(3) }

    (0 until N).forEach { i ->
        br.readLine().split(" ").forEachIndexed { j, v -> b[i][j] = v.toInt() }
    }

    dpMax[N-1] = b[N-1]
    dpMin[N-1] = b[N-1]
    for(i in N-2 downTo 0){
        dpMax[i][0] = b[i][0] + maxOf(dpMax[i+1][0], dpMax[i+1][1])
        dpMax[i][1] = b[i][1] + maxOf(dpMax[i+1][0], dpMax[i+1][1], dpMax[i+1][2])
        dpMax[i][2] = b[i][2] + maxOf(dpMax[i+1][1], dpMax[i+1][2])

        dpMin[i][0] = b[i][0] + minOf(dpMin[i+1][0], dpMin[i+1][1])
        dpMin[i][1] = b[i][1] + minOf(dpMin[i+1][0], dpMin[i+1][1], dpMin[i+1][2])
        dpMin[i][2] = b[i][2] + minOf(dpMin[i+1][1], dpMin[i+1][2])
    }

    bw.write("${dpMax[0].maxOf { it }} ${dpMin[0].minOf { it }}")

    bw.flush()
    bw.close()
}


main()