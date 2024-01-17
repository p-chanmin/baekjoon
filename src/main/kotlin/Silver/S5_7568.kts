import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

data class User(val w: Int, val h: Int)

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    val answer = IntArray(N) { 1 }

    val users = mutableListOf<User>()

    repeat(N) {
        br.readLine().split(" ").let {
            users.add(User(it[0].toInt(), it[1].toInt()))
        }
    }

    for (i in 0 until N - 1) {
        for (j in i + 1 until N) {
            if (users[i].w > users[j].w && users[i].h > users[j].h) {
                answer[j] += 1
            } else if (users[j].w > users[i].w && users[j].h > users[i].h) {
                answer[i] += 1
            }
        }
    }

    answer.forEach { bw.write("$it ") }

    bw.flush()
    bw.close()
}


main()