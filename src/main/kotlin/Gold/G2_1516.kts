import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    val cost = IntArray(N + 1) { 0 }
    val graph = Array(N + 1) { IntArray(N + 1) { -1 } }
    val degree = IntArray(N + 1) { 0 }

    repeat(N) { i ->
        val data = br.readLine().split(" ").map { it.toInt() }
        cost[i + 1] = data[0]
        for (j in 1 until data.size) {
            if (data[j] == -1) {
                break
            } else {
                graph[data[j]][i + 1] = 1
                degree[i + 1]++
            }
        }
    }

    val queue: Deque<Int> = LinkedList()
    val answer = IntArray(N + 1) { 0 }

    (1..N).forEach {
        if (degree[it] == 0) {
            queue.add(it)
            answer[it] = cost[it]
        }
    }

    while (queue.isNotEmpty()) {
        val q = queue.poll()
        for (i in 1..N) {
            if (graph[q][i] == 1) {
                answer[i] = maxOf(answer[i], cost[i] + answer[q])
                degree[i]--
                if (degree[i] == 0) {
                    queue.add(i)
                }
            }
        }
    }

    (1..N).forEach {
        bw.write("${answer[it]}\n")
    }

    bw.flush()
    bw.close()
}


main()