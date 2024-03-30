import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Edge(val u: Int, val v: Int, val w: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (N, M) = br.readLine().split(" ").map { it.toInt() }

    val gender = br.readLine().split(" ")
    val parent = IntArray(N) { it }

    val pq = PriorityQueue<Edge> { a, b -> a.w.compareTo(b.w) }

    repeat(M) {
        val road = br.readLine().split(" ").map { it.toInt() }
        pq.add(Edge(road[0] - 1, road[1] - 1, road[2]))
    }

    var lines = 0
    var answer = 0

    while (pq.isNotEmpty()) {
        val q = pq.poll()
        if (gender[q.u] != gender[q.v] && union(q.u, q.v, parent)) {
            lines++
            answer += q.w
        }
    }

    if (N - 1 == lines) {
        bw.write("$answer")
    } else {
        bw.write("-1")
    }

    bw.flush()
    bw.close()
}

fun find(i: Int, parent: IntArray): Int {
    return if (i == parent[i]) {
        i
    } else {
        parent[i] = find(parent[i], parent)
        parent[i]
    }
}

fun union(i: Int, j: Int, parent: IntArray): Boolean {
    val ni = find(i, parent)
    val nj = find(j, parent)
    return if (ni != nj) {
        parent[ni] = nj
        true
    } else {
        false
    }
}


main()