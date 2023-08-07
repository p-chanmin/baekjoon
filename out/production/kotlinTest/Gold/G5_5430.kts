import java.io.*
import java.util.*
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    var t = br.readLine().toInt()

    for(i in 0 until t){
        var command = br.readLine()
        var n = br.readLine().toInt()
        var data: List<String> = br.readLine().let { it.substring( 1 until it.length-1) }.split(",")
        if(n == 0) data = listOf()
        var queue: Deque<String> = LinkedList(data)

        var cntD = command.count{ it == 'D' }
        if(cntD > n){
            println("error")
            continue
        }
        else if(cntD == n){
            println("[]")
            continue
        }

        var reversed: Boolean = false
        for(c in command){
            when(c){
                'R' -> {
                    reversed = !reversed
                }
                'D' -> {
                    if(reversed) queue.pollLast()
                    else queue.pollFirst()
                }
            }
        }
        if(reversed) println(queue.reversed().toString().replace(" ","")) else println(queue.toString().replace(" ",""))


    }

}


main()