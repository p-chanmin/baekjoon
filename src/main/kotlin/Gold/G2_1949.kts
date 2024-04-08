import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()
    val people = IntArray(N + 1)
    br.readLine().split(" ").map { it.toInt() }.forEachIndexed { i, v ->
        people[i + 1] = v
    }

    val graph = Array(N + 1) { mutableListOf<Int>() }

    repeat(N - 1) {
        br.readLine().split(" ").map { it.toInt() }.let {
            graph[it[0]].add(it[1])
            graph[it[1]].add(it[0])
        }
    }

    val visited = BooleanArray(N + 1) { false }
    val dp = Array(N + 1) { IntArray(2) { 0 } }

    fun dfs(i: Int) {

        for (j in graph[i]) {
            if (!visited[j]) {
                visited[j] = true
                dfs(j)
                dp[i][0] += dp[j][1]
                dp[i][1] += dp[j].max()
            }
        }

        dp[i][0] += people[i]
    }

    visited[1] = true
    dfs(1)

    bw.write("${dp[1].max()}")

    bw.flush()
    bw.close()
}


main()