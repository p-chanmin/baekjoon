import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))

    var N = br.readLine().toInt()

    var pq = PriorityQueue<Int>{ a, b -> b - a }

    repeat(N){

        var i = br.readLine().toInt()

        if(i == 0) println(if(pq.isEmpty()) 0 else pq.poll())
        else pq.add(i)

    }

}


main()