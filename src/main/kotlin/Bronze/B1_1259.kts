import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))

    var cases: ArrayList<String> = arrayListOf()

    while(true){
        var s = br.readLine()
        if(s == "0") break
        cases.add(s)
    }
    cases.forEach { if(it == it.reversed()) println("yes") else println("no") }
}


main()