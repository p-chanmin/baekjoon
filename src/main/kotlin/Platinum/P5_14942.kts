import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

data class Road(val to: Int, val cost: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine().toInt()

    val power = IntArray(n + 1) { 0 }
    val current = IntArray(n + 1) { it }
    repeat(n) { i ->
        power[i + 1] = br.readLine().toInt()
    }

    val world = Array(n + 1) { mutableListOf<Road>() }
    val visited = BooleanArray(n + 1) { false }

    repeat(n - 1) {
        br.readLine().split(" ").map { it.toInt() }.let {
            world[it[0]].add(Road(it[1], it[2]))
            world[it[1]].add(Road(it[0], it[2]))
        }
    }

    fun dfs(now: Int): List<Int> {

        val ants = mutableListOf(now)

        for (next in world[now]) {
            if (!visited[next.to]) {
                visited[next.to] = true
                for (ant in dfs(next.to)) {
                    if (power[ant] >= next.cost) {
                        power[ant] -= next.cost
                        current[ant] = now
                        ants.add(ant)
                    }
                }
            }
        }

        return ants
    }

    visited[1] = true
    dfs(1)
    (1..n).forEach {
        bw.write("${current[it]}\n")
    }

    bw.flush()
    bw.close()
}


main()