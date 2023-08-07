import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))
    var bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, M) = br.readLine().split(" ").map { it.toInt() }

    var acc: IntArray = IntArray(N+1){ 0 }

    br.readLine().split(" ").forEachIndexed { i, v ->
        acc[i+1] = acc[i] + v.toInt()
    }

    repeat(M){
        var r = br.readLine().split(" ").map { it.toInt() }
        bw.write("${acc[r[1]] - acc[r[0]-1]}\n")
    }

    bw.flush()
    bw.close()
}


main()