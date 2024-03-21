import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    val array = br.readLine().split(" ").map { it.toInt() }

    val dp = IntArray(N) { 0 }

    dp[0] = array[0]
    var len = 0

    for (i in 1 until array.size) {
        if (dp[len] < array[i]) {
            len++
            dp[len] = array[i]
        } else {
            val idx = binarySearch(0, len, dp, array[i])
            dp[idx] = array[i]
        }
    }
    bw.write("${len + 1}")

    bw.flush()
    bw.close()
}

fun binarySearch(left: Int, right: Int, dp: IntArray, target: Int): Int {
    var l = left
    var r = right
    while (l <= r) {
        val mid = (l + r) / 2
        if (dp[mid] < target) {
            l = mid + 1
        } else {
            r = mid - 1
        }
    }
    return l
}

main()