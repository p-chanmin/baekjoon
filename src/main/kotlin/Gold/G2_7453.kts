import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine().toInt()

    val input = Array(n) { LongArray(4) }

    repeat(n) { i ->
        br.readLine().split(" ").map { it.toLong() }.forEachIndexed { j, v ->
            input[i][j] = v
        }
    }

    val ab = LongArray(n * n)
    val cd = LongArray(n * n)

    for (i in 0 until n) {
        for (j in 0 until n) {
            ab[i * n + j] = input[i][0] + input[j][1]
            cd[i * n + j] = input[i][2] + input[j][3]
        }
    }
    ab.sort()
    cd.sort()

    var left = 0
    var right = cd.size - 1

    var answer = 0L

    while (left < ab.size && right >= 0) {
        if (ab[left] + cd[right] == 0L) {

            var l = left + 1
            var r = right - 1

            while (l < ab.size && ab[left] == ab[l]) {
                l++
            }
            while (r >= 0 && cd[right] == cd[r]) {
                r--
            }
            answer += (l - left).toLong() * (right - r).toLong()
            left = l
            right = r
        } else if (ab[left] + cd[right] > 0) {
            right--
        } else {
            left++
        }
    }
    bw.write("$answer")
    bw.flush()
    bw.close()
}


main()