import java.io.*
import java.util.*

data class Vertex(var v: Int, var cost: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, M, X) = br.readLine().split(" ").map { it.toInt() }

    var g: Array<IntArray> = Array(N+1){ IntArray(N+1){ 0 } }

    repeat(M){
        br.readLine().split(" ").let {
            g[it[0].toInt()][it[1].toInt()] = it[2].toInt()
        }
    }

    fun dijkstra(start: Int): IntArray{

        var costs: IntArray = IntArray(N+1){ Int.MAX_VALUE }
        var queue = PriorityQueue<Vertex>(){ a, b -> a.cost - b.cost }
        costs[start] = 0
        queue.add(Vertex(start, 0))

        while(queue.isNotEmpty()){
            var q = queue.poll()
            if(q.cost > costs[q.v]) continue

            for(i in 1 .. N){
                if(g[q.v][i] != 0){
                    if(costs[i] > q.cost + g[q.v][i]){
                        costs[i] = q.cost + g[q.v][i]
                        queue.add(Vertex(i, q.cost + g[q.v][i]))
                    }
                }
            }
        }
        return costs
    }

    var costX = dijkstra(X)

    bw.write("${(1..N).map{ dijkstra(it)[X] + costX[it] }.maxOf { it }}")

    bw.flush()
    bw.close()
}


main()