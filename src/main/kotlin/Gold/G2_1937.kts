import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine().toInt()

    val world = Array(n) { IntArray(n) }
    val dp = Array(n) { IntArray(n) }
    val visited = Array(n) { BooleanArray(n) { false } }

    repeat(n) { i ->
        br.readLine().split(" ").map { it.toInt() }.forEachIndexed { j, v ->
            world[i][j] = v
        }
    }

    val di = listOf(0, 0, 1, -1)
    val dj = listOf(1, -1, 0, 0)

    fun dfs(i: Int, j: Int): Int {
        if (visited[i][j]) {
            return dp[i][j]
        }
        visited[i][j] = true
        var count = 1

        for (k in 0..3) {
            val ni = i + di[k]
            val nj = j + dj[k]
            if (ni in 0 until n && nj in 0 until n && world[i][j] < world[ni][nj]) {
                count = maxOf(count, dfs(ni, nj) + 1)
            }
        }
        dp[i][j] = count
        return dp[i][j]
    }

    var answer = 1

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (!visited[i][j]) {
                answer = maxOf(answer, dfs(i, j))
            }
        }
    }

    bw.write("$answer")

    bw.flush()
    bw.close()
}


main()