import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (C, R) = br.readLine().split(" ").map { it.toInt() }
    val K = br.readLine().toInt()

    val maxCount = C * R
    val visited = Array(R) { BooleanArray(C) { false } }

    var state = "D"
    var count = 1
    var x = 0
    var y = 0
    visited[y][x] = true

    if (K !in 1..maxCount) {
        bw.write("0")
    } else {

        while (K != count) {
            count++
            when (state) {
                "D" -> {
                    if (y + 1 in 0 until R && !visited[y + 1][x]) {
                        y += 1
                    } else {
                        state = "R"
                        x += 1
                    }
                }

                "U" -> {
                    if (y - 1 in 0 until R && !visited[y - 1][x]) {
                        y -= 1
                    } else {
                        state = "L"
                        x -= 1
                    }
                }

                "L" -> {
                    if (x - 1 in 0 until C && !visited[y][x - 1]) {
                        x -= 1
                    } else {
                        state = "D"
                        y += 1
                    }
                }

                "R" -> {
                    if (x + 1 in 0 until C && !visited[y][x + 1]) {
                        x += 1
                    } else {
                        state = "U"
                        y -= 1
                    }
                }
            }
            visited[y][x] = true
        }
        bw.write("${x + 1} ${y + 1}")
    }

    bw.flush()
    bw.close()
}

main()