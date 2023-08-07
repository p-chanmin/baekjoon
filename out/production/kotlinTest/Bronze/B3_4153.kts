import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))

    while(true){
        var t = br.readLine()
        if(t == "0 0 0") break

        var lines = t.split(" ").map{ it.toInt() }

        lines = lines.sortedDescending().map{ it*it }

        if(lines[0] == lines[1] + lines[2]) println("right")
        else println("wrong")
    }

}


main()