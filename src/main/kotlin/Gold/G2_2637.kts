import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()
    val M = br.readLine().toInt()

    val recipe = Array(N + 1) { IntArray(N + 1) { 0 } }

    val degree = IntArray(N + 1) { 0 }

    val products = mutableSetOf<Int>()

    repeat(M) {
        br.readLine().split(" ").let {
            recipe[it[1].toInt()][it[0].toInt()] = it[2].toInt()
            degree[it[0].toInt()] += 1
            products.add(it[0].toInt())
        }
    }

    val dp = Array(N + 1) { IntArray(N + 1) { 0 } }

    val queue: Deque<Int> = LinkedList()
    for (i in 1..N) {
        if (degree[i] == 0) {
            queue.add(i)
        }
    }

    while (queue.isNotEmpty()) {
        val q = queue.poll()

        for (i in 1..N) {
            if (recipe[q][i] != 0) {
                if (q !in products) {
                    dp[i][q] += recipe[q][i]
                } else {
                    dp[q].forEachIndexed { j, v ->
                        dp[i][j] += v * recipe[q][i]
                    }
                }
                degree[i] -= 1

                if (degree[i] == 0) {
                    queue.add(i)
                }
            }
        }
    }

    (1..N).forEach {
        if (it !in products) {
            bw.write("$it ${dp[N][it]}\n")
        }
    }

    bw.flush()
    bw.close()
}


main()