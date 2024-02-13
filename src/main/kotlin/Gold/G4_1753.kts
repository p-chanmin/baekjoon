import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Vertex(val v: Int, val cost: Int)

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (v, e) = br.readLine().split(" ").map { it.toInt() }

    val start = br.readLine().toInt()

    val g = Array<MutableList<Vertex>>(v + 1) { mutableListOf() }
    val dist = IntArray(v + 1) { Int.MAX_VALUE }

    repeat(e) {
        br.readLine().split(" ").let {
            g[it[0].toInt()].add(Vertex(it[1].toInt(), it[2].toInt()))
        }
    }

    dist[start] = 0
    val queue = PriorityQueue<Vertex> { a, b -> a.cost - b.cost }
    queue.add(Vertex(start, 0))

    while (queue.isNotEmpty()) {
        val q = queue.poll()
        if (dist[q.v] < q.cost) continue

        for (next in g[q.v]) {
            if (dist[next.v] > q.cost + next.cost) {
                dist[next.v] = q.cost + next.cost
                queue.add(Vertex(next.v, dist[next.v]))
            }
        }
    }

    (1..v).forEach {
        bw.write("${if (dist[it] == Int.MAX_VALUE) "INF" else dist[it]}\n")
    }

    bw.flush()
    bw.close()
}


main()