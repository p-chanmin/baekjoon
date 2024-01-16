import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var N = br.readLine().toInt()

    if (N % 5 == 0) {
        bw.write("${N / 5}")
    } else {
        var tmp = 0

        while (N > 0) {
            tmp += 1
            N -= 3
            if (N % 5 == 0) {
                bw.write("${tmp + N / 5}")
                break
            } else if (N == 1 || N == 2) {
                bw.write("-1")
                break
            } else if (N == 0) {
                bw.write("$tmp")
                break
            }
        }
    }

    bw.flush()
    bw.close()
}


main()