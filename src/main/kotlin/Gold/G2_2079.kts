import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val str = br.readLine()

    val dp = IntArray(str.length) { Int.MAX_VALUE }
    dp[0] = 1
    for (i in 1 until str.length) {
        for (j in 0..i) {
            if (isPalindrome(str, j, i)) {
                if (j == 0) {
                    dp[i] = 1
                } else {
                    dp[i] = minOf(dp[i], dp[j - 1] + 1)
                }
            }
        }
    }

    bw.write("${dp.last()}")

    bw.flush()
    bw.close()
}

fun isPalindrome(str: String, j: Int, i: Int): Boolean {
    var l = j
    var r = i
    while (l <= r) {
        if (str[l] == str[r]) {
            l++
            r--
        } else {
            return false
        }
    }
    return true
}

main()