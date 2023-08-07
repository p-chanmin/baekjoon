import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.*

data class Mix(var ph: Int, var s1: Int, var s2: Int)


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var N = br.readLine().toInt()

    var data: IntArray = IntArray(N)

    br.readLine().split(" ").forEachIndexed { i, s ->
        data[i] = s.toInt()
    }

    var l = 0
    var r = data.size-1

    var mixed: MutableList<Mix> = mutableListOf()

    while(l != r){
        if(data[l] + data[r] > 0){
            mixed.add(Mix(data[l] + data[r], data[l], data[r]))
            r--
        }
        else{
            mixed.add(Mix(data[l] + data[r], data[l], data[r]))
            l++
        }
    }

    mixed = mixed.sortedBy { abs(it.ph) }.toMutableList()
    bw.write("${mixed[0].s1} ${mixed[0].s2}")

    bw.flush()
    bw.close()
}

main()