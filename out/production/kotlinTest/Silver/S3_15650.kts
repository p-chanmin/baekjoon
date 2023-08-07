import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))
    var bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, M) = br.readLine().split(" ").map { it.toInt() }

    var list: List<Int> = (1 .. N).toList()

    fun combi(l: List<Int>, n:Int, result: List<Int> = listOf(), index:Int = 0): List<List<Int>> {
        return if (result.size >= n){
            listOf(result)
        }else{
            l.slice(index until l.size).flatMapIndexed { i, v -> combi(l, n, result + v, index + i + 1)}
        }
    }

    combi(list, M).forEach{
        it.forEach{
            bw.write("${it} ")
        }
        bw.write("\n")
    }

    bw.flush()
    bw.close()
}


main()