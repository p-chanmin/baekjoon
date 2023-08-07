import java.io.*
import java.util.*
import kotlin.collections.ArrayList

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var N = br.readLine().toInt()

    var data: PriorityQueue<Int> = PriorityQueue<Int>{ a, b -> a - b }

    repeat(N){
        data.add(br.readLine().toInt())
    }

    while(data.isNotEmpty()){
        bw.write("${data.poll()}\n")
    }
    bw.flush()
    bw.close()
}


main()