import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))

    var T = br.readLine().toInt()

    repeat(T){

        var k = br.readLine().toInt()
        var n = br.readLine().toInt()
        var result: List<Int> = (1 .. n).map{ it }
        repeat(k){
            result = (1 .. n).map{ result.slice(0 .. it-1).sumOf { it }  }
        }
        println(result[n-1])

    }
}


main()