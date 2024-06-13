import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    val graph = Array(N) { IntArray(N) { 0 } }
    val dp = Array(N) { IntArray(1 shl N) { -1 } }

    repeat(N) { i ->
        br.readLine().split(" ").map { it.toInt() }.forEachIndexed { j, v ->
            graph[i][j] = v
        }
    }

    fun dfs(start: Int, visited: Int): Int {
        if (visited == (1 shl N) - 1) {
            if (graph[start][0] != 0) {
                return graph[start][0]
            } else {
                return Int.MAX_VALUE
            }
        }

        if (dp[start][visited] != -1) {
            return dp[start][visited]
        }

        var cost = Int.MAX_VALUE
        for (n in 0 until N) {
            if (graph[start][n] == 0 || visited and (1 shl n) == 1 shl n) {
                continue
            }
            val c = dfs(n, visited or (1 shl n)).let {
                if (it != Int.MAX_VALUE) {
                    it + graph[start][n]
                } else {
                    it
                }
            }
            cost = min(cost, c)
        }
        dp[start][visited] = cost
        return cost

    }

    bw.write("${dfs(0, 1)}")

    bw.flush()
    bw.close()
}



main()