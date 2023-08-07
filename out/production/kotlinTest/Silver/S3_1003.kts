import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))

    var N = br.readLine().toInt()

    var data: ArrayList<Int> = arrayListOf()

    repeat(N){
        data.add(br.readLine().toInt())
    }

    var m = data.maxOf { it }
    var dp_0: Array<Long> = Array(m+1){ 0 }
    var dp_1: Array<Long> = Array(m+1){ 0 }
    dp_0[0] = 1
    if(dp_1.size >= 2) dp_1[1] = 1

    (2 .. m).forEach {
        dp_0[it] = dp_0[it-1] + dp_0[it-2]
        dp_1[it] = dp_1[it-1] + dp_1[it-2]
    }

    data.forEach{
        println("${dp_0[it]} ${dp_1[it]}")
    }
}


main()