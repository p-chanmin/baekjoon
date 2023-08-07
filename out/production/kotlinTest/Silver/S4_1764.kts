import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, M) = br.readLine().split(" ").map{ it.toInt() }

    var noListen: MutableSet<String> = mutableSetOf()
    var noSeen: MutableSet<String> = mutableSetOf()

    repeat(N){
        noListen.add(br.readLine())
    }
    repeat(M){
        noSeen.add(br.readLine())
    }

    var noListenSeen = noListen.intersect(noSeen).sorted()

    bw.write("${noListenSeen.size}\n")
    noListenSeen.forEach {
        bw.write("${it}\n")
        bw.flush()
    }

    bw.close()
}


main()