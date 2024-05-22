import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (N, M, money) = br.readLine().split(" ").map { it.toInt() }

    val price = br.readLine().split(" ").map { it.toInt() }
    val parent = IntArray(N) { it }

    fun find(x: Int): Int {
        return if (parent[x] == x) {
            x
        } else {
            parent[x] = find(parent[x])
            parent[x]
        }
    }

    fun union(x: Int, y: Int) {
        val nx = find(x)
        val ny = find(y)

        if (price[nx] > price[ny]) {
            parent[nx] = ny
        } else {
            parent[ny] = nx
        }
    }

    repeat(M) {
        br.readLine().split(" ").map { it.toInt() }.let {
            union(it[0] - 1, it[1] - 1)
        }
    }

    val answer = (0 until N).map { find(it) }.toSet().map { price[it] }.sum()

    if (money >= answer) {
        bw.write("$answer")
    } else {
        bw.write("Oh no")
    }

    bw.flush()
    bw.close()
}




main()