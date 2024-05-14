import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (N, L, R, X) = br.readLine().split(" ").map { it.toInt() }

    val problems = br.readLine().split(" ").map { it.toInt() }

    fun check(l: List<Int>): Boolean {
        val diffValue = l.max() - l.min()
        val sumValue = l.sum()
        return sumValue in L..R && diffValue >= X
    }

    var answer = 0

    fun dfs(i: Int, l: List<Int>) {
        if (l.size >= 2) {
            if (check(l)) {
                answer++
            }
        }

        for (k in i + 1 until N) {
            dfs(k, l + listOf(problems[k]))
        }
    }

    for (i in 0 until N) {
        dfs(i, listOf(problems[i]))
    }

    bw.write("$answer")

    bw.flush()
    bw.close()
}


main()