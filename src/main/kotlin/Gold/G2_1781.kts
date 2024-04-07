import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Homework(val deadLine: Int, val noodle: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    val works = PriorityQueue<Homework> { a, b -> a.deadLine.compareTo(b.deadLine) }

    repeat(N) {
        works.add(br.readLine().split(" ").map { it.toInt() }.let { Homework(it[0], it[1]) })
    }

    val schedule = PriorityQueue<Homework> { a, b -> a.noodle.compareTo(b.noodle) }


    while (works.isNotEmpty()) {
        val work = works.poll()

        if (work.deadLine > schedule.size) {
            schedule.add(work)
        } else if (work.noodle > schedule.peek().noodle) {
            schedule.poll()
            schedule.add(work)
        }

    }

    bw.write("${schedule.sumOf { it.noodle }}")

    bw.flush()
    bw.close()
}


main()