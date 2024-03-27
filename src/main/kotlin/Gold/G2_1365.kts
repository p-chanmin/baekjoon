import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    val lines = br.readLine().split(" ").map { it.toInt() }
    val dp = Array(N + 1) { 0 }

    var len = 0

    for (i in lines.indices) {
        if (dp[len] < lines[i]) {
            len++
            dp[len] = lines[i]
        } else {
            val idx = binarySearch(0, len, dp, lines[i])
            dp[idx] = lines[i]
        }
    }

    bw.write("${lines.size - len}")

    bw.flush()
    bw.close()
}

fun binarySearch(left: Int, right: Int, dp: Array<Int>, target: Int): Int {
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