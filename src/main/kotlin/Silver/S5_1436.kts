import java.io.*
import java.util.*
import kotlin.collections.ArrayList

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))

    var n = br.readLine().toInt()

    var numbers: ArrayList<Int> = arrayListOf()

    for(s in 666 .. Int.MAX_VALUE){
        if("666" in s.toString()){
            numbers.add(s)
            if(numbers.size == n) break
        }
    }

    println(numbers.last())

}


main()