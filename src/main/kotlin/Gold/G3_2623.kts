import java.io.*
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, M) = br.readLine().split(" ").map{ it.toInt() }

    var degree: IntArray = IntArray(N+1)
    var g: Array<IntArray> = Array(N+1){ IntArray(N+1) }

    repeat(M){

        br.readLine().split(" ").map { it.toInt() }.let {
            for(i in 2 .. it[0]){
                if(g[it[i-1]][it[i]] != 1){
                    g[it[i-1]][it[i]] = 1
                    degree[it[i]]++
                }
            }
        }

    }

    var queue: Deque<Int> = LinkedList()
    var result: ArrayList<Int> = arrayListOf()

    for(i in 1 .. N){
        if(degree[i] == 0) queue.add(i)
    }

    while(queue.isNotEmpty()){
        var q = queue.poll()
        result.add(q)

        for(i in 1 .. N){
            if(g[q][i] == 1){
                if(--degree[i] == 0) queue.add(i)
            }
        }
    }

    if(result.size == N){
        for(n in result) bw.write("${n}\n")
    }
    else bw.write("0")

    bw.flush()
    bw.close()
}


main()