import java.io.*
import java.util.*

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    bw.write(
        (br.readLine().split(" ").sumOf { it.toInt() * it.toInt() } % 10).toString()
    )

    bw.flush()
    bw.close()
}

main()