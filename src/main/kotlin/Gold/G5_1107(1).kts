import java.io.*
import java.util.*
import kotlin.math.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    var desti = br.readLine().toInt()

    var n = br.readLine().toInt()
    var broken: BooleanArray = BooleanArray(10){ true }

    if(n != 0) br.readLine().split(" ").forEach{ broken[it.toInt()] = false }

    var answer = abs(desti - 100)

    for(c in 0 .. 999999){

        if(c.toString().all{ broken[it.toString().toInt()] }){
            answer = min(answer, c.toString().length + abs(desti - c))
        }

    }
    println(answer)
}



main()