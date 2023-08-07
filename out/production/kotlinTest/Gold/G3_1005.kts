import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var T = br.readLine().toInt()

    repeat(T){

        var (N, K) = br.readLine().split(" ").map { it.toInt() }
        var costs: ArrayList<Int> = arrayListOf(0)
        costs.addAll(br.readLine().split(" ").map { it.toInt() })

        var g: Array<ArrayList<Int>> = Array(N+1){ arrayListOf() }
        var degree: IntArray = IntArray(N+1){ 0 }
        repeat(K){
            br.readLine().split(" ").map { it.toInt() }.let {
                g[it[0]].add(it[1])
                degree[it[1]]++
            }
        }
        var e = br.readLine().toInt()

        var queue: Deque<Int> = LinkedList()
        var dp: IntArray = IntArray(N+1)
        for(i in 1 .. N){
            if(degree[i] == 0){
                queue.add(i)
                dp[i] = costs[i]
            }
        }

        while (queue.isNotEmpty()){
            var q = queue.poll()
            if(q == e) break

            for(i in g[q]){
                dp[i] = max(dp[i], dp[q] + costs[i])
                if(--degree[i] == 0) queue.add(i)
            }
        }
        bw.write("${dp[e]}\n")
    }

    bw.flush()
    bw.close()
}


main()