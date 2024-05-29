import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    val dp = mutableListOf<Int>()
    val dpIndex = mutableListOf<Int>()

    val arr = br.readLine().split(" ").map { it.toInt() }.apply {
        dp.add(this[0])
    }

    arr.forEach {
        if (dp.last() < it) {
            dpIndex.add(dp.size)
            dp.add(it)
        } else {
            val i = binarySearch(dp, it)
            dp[i] = it
            dpIndex.add(i)
        }
    }

    var size = dp.size - 1

    val answer = mutableListOf<Int>()

    for (i in arr.size - 1 downTo 0) {
        if (size == -1) {
            break
        }
        if (size == dpIndex[i]) {
            answer.add(arr[i])
            size--
        }
    }

    println(dp)
    println(dpIndex)

    bw.write("${dp.size}\n")
    bw.write(answer.reversed().joinToString(" "))

    bw.flush()
    bw.close()
}

fun binarySearch(list: MutableList<Int>, target: Int): Int {
    var l = 0
    var r = list.size - 1

    while (l <= r) {
        val mid = (l + r) / 2
        if (list[mid] < target) {
            l = mid + 1
        } else {
            r = mid - 1
        }
    }
    return l
}


main()