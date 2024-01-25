import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Vertex(val v: Int, val cost: Int)

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine().toInt()
    val m = br.readLine().toInt()

    val g = Array(n + 1) { IntArray(n + 1) { Int.MAX_VALUE } }

    repeat(m) {
        br.readLine().split(" ").let {
            if(g[it[0].toInt()][it[1].toInt()] > it[2].toInt()){
                g[it[0].toInt()][it[1].toInt()] = it[2].toInt()
            }
        }
    }

    val (start, end) = br.readLine().split(" ").map { it.toInt() }

    val dist = IntArray(n + 1) { Int.MAX_VALUE }
    val queue = PriorityQueue<Vertex> { a, b -> a.cost - b.cost }
    dist[start] = 0
    queue.add(Vertex(start, 0))

    while (queue.isNotEmpty()) {
        val q = queue.poll()
        if (dist[q.v] < q.cost) continue

        for (v in 1..n) {
            if (g[q.v][v] != Int.MAX_VALUE) {
                if (dist[v] > g[q.v][v] + q.cost) {
                    dist[v] = g[q.v][v] + q.cost
                    queue.add(Vertex(v, dist[v]))
                }
            }
        }
    }

    bw.write("${dist[end]}")
    bw.flush()
    bw.close()
}


main()