import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Work(val date: Int, val score: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    val works = PriorityQueue<Work> { a, b -> b.score - a.score }

    var dead = 0

    repeat(N) {
        br.readLine().split(" ").map { it.toInt() }.let {
            dead = maxOf(dead, it[0])
            works.add(Work(it[0], it[1]))
        }
    }

    val schedule = IntArray(dead + 1) { 0 }

    while (works.isNotEmpty()) {
        val work = works.poll()

        if (schedule[work.date] == 0) {
            schedule[work.date] = work.score
        } else {
            var d = work.date - 1
            while (d >= 1) {
                if (schedule[d] == 0) {
                    schedule[d] = work.score
                    break
                } else {
                    d--
                }
            }
        }
    }
    bw.write("${schedule.sum()}")

    bw.flush()
    bw.close()
}


main()