import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))

    var n = br.readLine().toInt()

    var words: ArrayList<String> = arrayListOf()

    repeat(n){
        words.add(br.readLine())
    }

    words.toSet().sortedWith(compareBy( {it.length}, {it} )).forEach {
        println(it)
    }
}


main()