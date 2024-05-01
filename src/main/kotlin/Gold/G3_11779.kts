import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Bus(val to: Int, val cost: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine().toInt()
    val m = br.readLine().toInt()

    val graph = Array(n + 1) { mutableListOf<Bus>() }

    repeat(m) {
        br.readLine().split(" ").map { it.toInt() }.let {
            graph[it[0]].add(Bus(it[1], it[2]))
        }
    }

    val (start, end) = br.readLine().split(" ").map { it.toInt() }

    val cost = IntArray(n + 1) { Int.MAX_VALUE }.apply { this[start] = 0 }
    val parent = IntArray(n + 1) { 0 }

    val queue = PriorityQueue<Bus> { a, b -> a.cost - b.cost }
    queue.add(Bus(start, 0))

    while (queue.isNotEmpty()) {

        val bus = queue.poll()

        if (bus.cost > cost[bus.to]) {
            continue
        }

        for (b in graph[bus.to]) {
            if (cost[b.to] > cost[bus.to] + b.cost) {
                cost[b.to] = cost[bus.to] + b.cost
                parent[b.to] = bus.to
                queue.add(Bus(b.to, cost[b.to]))
            }
        }

    }

    val path = mutableListOf<Int>().apply {
        var v = end
        while (0 < v) {
            add(v)
            v = parent[v]
        }
        reverse()
    }

    bw.write("${cost[end]}\n")
    bw.write("${path.size}\n")
    bw.write(path.joinToString(" "))

    bw.flush()
    bw.close()
}


main()