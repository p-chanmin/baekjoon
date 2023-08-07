import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, M) = br.readLine().split(" ").map{ it.toInt() }

    var hash: MutableMap<String, String> = mutableMapOf()

    repeat(N){
        var d = br.readLine().split(" ")
        hash.put(d[0], d[1])
    }
    repeat(M){
        bw.write("${hash.get(br.readLine())!!}\n")
    }

    bw.flush()
    bw.close()
}


main()