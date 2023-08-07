import java.io.*
import java.util.*

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val T = br.readLine().toInt()

    repeat(T){

        val (H, W, N) = br.readLine().split(" ").map { it.toInt() }

        var n = 0

        for(w in 1..W) {
            for(h in 1..H) {
                if(++n == N) {
                    bw.write(((h * 100) + w).toString() + "\n")
                    break
                }
            }
            if(n >= N) break
        }

    }

    bw.flush()
    bw.close()

}

main()