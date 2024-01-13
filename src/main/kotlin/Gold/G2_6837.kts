import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (N, M) = br.readLine().split(" ").map { it.toInt() }

    val m = Array(N) { CharArray(M) { ' ' } }
    val visited = Array(N) { BooleanArray(M) { false } }

    repeat(N) { i ->
        br.readLine().forEachIndexed { j, c ->
            m[i][j] = c
        }
    }

    var result = 0

    data class Pos(val i: Int, val j: Int)

    var tmp: ArrayList<Pos> = arrayListOf()
    fun dfs(i: Int, j: Int) {
        if (i !in 0 until N || j !in 0 until M || visited[i][j]) {
            if (Pos(i, j) in tmp) {
                result++
            }
        } else {
            visited[i][j] = true
            tmp.add(Pos(i, j))
            when (m[i][j]) {
                'S' -> dfs(i + 1, j)
                'E' -> dfs(i, j + 1)
                'W' -> dfs(i, j - 1)
                'N' -> dfs(i - 1, j)
            }
        }
    }

    for (i in 0 until N) {
        for (j in 0 until M) {
            if (!visited[i][j]) {
                dfs(i, j)
                tmp = arrayListOf()
            }
        }
    }

    bw.write(result.toString())

    bw.flush()
    bw.close()
}

main()