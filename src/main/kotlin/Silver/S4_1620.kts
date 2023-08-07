import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, M) = br.readLine().split(" ").map{ it.toInt() }

    var nameMap: MutableMap<String, Int> = mutableMapOf()
    var numberMap: MutableMap<Int, String> = mutableMapOf()


    (1 .. N).forEach{ i ->
        var name = br.readLine()
        nameMap.put(name, i)
        numberMap.put(i, name)
    }

    repeat(M){
        var q = br.readLine()

        if(q[0].isDigit()) bw.write("${numberMap[q.toInt()]!!}\n")
        else bw.write("${nameMap[q]!!}\n")
    }

    bw.flush()
    bw.close()
}


main()