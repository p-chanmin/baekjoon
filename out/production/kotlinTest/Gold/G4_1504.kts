import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

data class Vertex(var v: Int, var cost: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, E) = br.readLine().split(" ").map{ it.toInt() }

    var g: Array<IntArray> = Array(N+1){ IntArray(N+1){ 0 } }

    repeat(E){
        br.readLine().split(" ").let {
            g[it[0].toInt()][it[1].toInt()] = it[2].toInt()
            g[it[1].toInt()][it[0].toInt()] = it[2].toInt()
        }
    }

    var (v1, v2) = br.readLine().split(" ").map { it.toInt() }

    fun dijkstra(start: Int): IntArray{

        var costs: IntArray = IntArray(N+1){ Int.MAX_VALUE }
        var queue = PriorityQueue<Vertex>(){ a, b -> a.cost - b.cost }
        costs[start] = 0
        queue.add(Vertex(start, 0))

        while(queue.isNotEmpty()){
            var q = queue.poll()
            if(costs[q.v] < q.cost) continue

            for(v in 1 .. N){
                if(g[q.v][v] != 0){
                    if(costs[v] > q.cost + g[q.v][v]){
                        costs[v] = q.cost + g[q.v][v]
                        queue.add(Vertex(v, costs[v]))
                    }
                }
            }
        }
        return costs
    }

    var startTo = dijkstra(1)
    var v1To = dijkstra(v1)
    var v2To = dijkstra(v2)

    var result: ArrayList<Int> = arrayListOf()
    if(listOf(startTo[v1], v1To[v2], v2To[N]).all { it != Int.MAX_VALUE }){
        result.add(startTo[v1] + v1To[v2] + v2To[N])
    }
    if(listOf(startTo[v2], v2To[v1], v1To[N]).all { it != Int.MAX_VALUE }){
        result.add(startTo[v2] + v2To[v1] + v1To[N])
    }

    bw.write("${if(result.isEmpty()) -1 else result.minOf { it }}")

    bw.flush()
    bw.close()
}



main()