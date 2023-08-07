import java.io.*
import java.util.*

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val music = br.readLine()

    when{
        music == "1 2 3 4 5 6 7 8" -> bw.write("ascending")
        music == "8 7 6 5 4 3 2 1" -> bw.write("descending")
        else -> bw.write("mixed")
    }

    bw.flush()
    bw.close()
}

main()