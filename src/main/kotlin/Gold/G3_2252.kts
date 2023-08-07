import java.io.*
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, M) = br.readLine().split(" ").map { it.toInt() }

    var g: Array<ArrayList<Int>> = Array(N+1){ arrayListOf() }
    var degree: IntArray = IntArray(N+1)

    repeat(M){
        br.readLine().split(" ").let {
            g[it[0].toInt()].add(it[1].toInt())
            degree[it[1].toInt()]++
        }
    }

    var queue: Deque<Int> = LinkedList()

    for(i in 1 .. N){
        if(degree[i] == 0) queue.add(i)
    }

    while(queue.isNotEmpty()){
        var q = queue.poll()
        bw.write("${q} ")

        for(j in g[q]){
            if(--degree[j] == 0) queue.add(j)
        }
    }

    bw.flush()
    bw.close()
}


main()