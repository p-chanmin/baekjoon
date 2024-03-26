import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Edge(val u: Int, val v: Int, val w: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (N, M, K) = br.readLine().split(" ").map { it.toInt() }

    val power = br.readLine().split(" ").map { it.toInt() }

    val parent = IntArray(N + 1) { it }
    val queue = PriorityQueue<Edge> { a, b -> a.w - b.w }

    repeat(M) {
        val e = br.readLine().split(" ").map { it.toInt() }
        queue.add(Edge(e[0], e[1], e[2]))
    }

    power.forEach {
        parent[it] = power.first()
    }

    fun find(x: Int): Int {
        return if (x == parent[x]) x
        else {
            parent[x] = find(parent[x])
            return parent[x]
        }
    }

    fun union(x: Int, y: Int): Boolean {
        val nx = find(x)
        val ny = find(y)
        return if (nx != ny) {
            parent[nx] = ny
            true
        } else false
    }

    var answer = 0

    while (queue.isNotEmpty()) {
        val q = queue.poll()
        if (union(q.u, q.v)) {
            answer += q.w
        }
    }

    bw.write("$answer")

    bw.flush()
    bw.close()
}




main()