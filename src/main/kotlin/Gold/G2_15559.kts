import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

var deep = 0
var answer = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (N, M) = br.readLine().split(" ").map { it.toInt() }

    val world = Array(N) { CharArray(M) { ' ' } }
    val visited = Array(N) { IntArray(M) { 0 } }

    repeat(N) { i ->
        br.readLine().forEachIndexed { j, c ->
            world[i][j] = c
        }
    }

    for (i in world.indices) {
        for (j in world.first().indices) {
            if (visited[i][j] == 0) {
                deep++
                dfs(i, j, world, visited)
            }
        }
    }

    bw.write("$answer")

    bw.flush()
    bw.close()
}

fun dfs(
    i: Int,
    j: Int,
    world: Array<CharArray>,
    visited: Array<IntArray>
) {

    visited[i][j] = deep

    var ni = i
    var nj = j

    when (world[i][j]) {
        'N' -> {
            ni -= 1
        }

        'S' -> {
            ni += 1
        }

        'W' -> {
            nj -= 1
        }

        else -> {
            nj += 1
        }
    }

    if (ni in world.indices && nj in world[0].indices) {
        if (visited[ni][nj] == 0) {
            dfs(ni, nj, world, visited)

        } else if (visited[ni][nj] == deep) {
            answer++
        }
    } else {
        answer++
    }
}


main()