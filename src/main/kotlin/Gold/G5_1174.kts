import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    val nums = listOf("9", "8", "7", "6", "5", "4", "3", "2", "1", "0")

    val arr = mutableListOf<Long>()

    fun dfs(i: Int, l: List<String>) {
        if (l.isNotEmpty()) {
            arr.add(l.joinToString("").toLong())
        }
        for (k in i + 1 until 10) {
            dfs(k, l + listOf(nums[k]))
        }
    }

    for (i in 0 until 10) {
        dfs(i, listOf(nums[i]))
    }

    arr.sort()

    if (N - 1 < arr.size) {
        bw.write("${arr[N - 1]}")
    } else {
        bw.write("-1")
    }

    bw.flush()
    bw.close()
}


main()