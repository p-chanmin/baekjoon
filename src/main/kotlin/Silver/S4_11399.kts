import java.io.*
import java.util.*
import kotlin.collections.ArrayList

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var N = br.readLine().toInt()

    var times = br.readLine().split(" ").map{ it.toInt() }

    var total: IntArray = IntArray(N){ 0 }

    times = times.sorted()

    for(i in 0 until times.size){
        if(i == 0) total[i] = times[i]
        else total[i] = total[i-1] + times[i]
    }

    bw.write("${total.sum()}")

    bw.flush()
    bw.close()
}


main()