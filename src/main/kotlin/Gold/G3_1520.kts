import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

data class Pos(val i: Int, val j: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (M, N) = br.readLine().split(" ").map { it.toInt() }

    val world = Array(M) { IntArray(N) { 0 } }
    val dp = Array(M) { IntArray(N) { 0 } }

    repeat(M) { i ->
        br.readLine().split(" ").map { it.toInt() }.forEachIndexed { j, v ->
            world[i][j] = v
        }
    }

    val visited = Array(M) { BooleanArray(N) { false } }

    val di = listOf(0, 0, 1, -1)
    val dj = listOf(1, -1, 0, 0)

    fun dfs(i: Int, j: Int): Int {
        if (i == M - 1 && j == N - 1) {
            return 1
        } else if (visited[i][j]) {
            return dp[i][j]
        }

        for (k in 0..3) {
            val ni = i + di[k]
            val nj = j + dj[k]

            if (ni >= 0 && ni < M && nj >= 0 && nj < N && world[i][j] > world[ni][nj]) {
                dp[i][j] += dfs(ni, nj)
            }
        }
        visited[i][j] = true
        return dp[i][j]
    }

    bw.write("${dfs(0, 0)}")

    bw.flush()
    bw.close()
}

main()