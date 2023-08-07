import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var G = br.readLine().toInt()
    var P = br.readLine().toInt()

    var gates: IntArray = IntArray(G+1){ it }

    fun find(x: Int): Int{
        return if(x == gates[x]) x
        else{
            gates[x] = find(gates[x])
            gates[x]
        }
    }

    var answer = 0

    for(i in 0 until P){
        var g = find(br.readLine().toInt())
        if(g == 0) break

        gates[g] = g - 1
        answer++
    }

    bw.write("${answer}")

    bw.flush()
    bw.close()
}


main()