import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

data class Egg(var s: Int, val w: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    val eggs = mutableListOf<Egg>()

    repeat(N) {
        br.readLine().split(" ").let {
            eggs.add(Egg(it[0].toInt(), it[1].toInt()))
        }
    }

    var answer = 0

    fun dfs(start: Int) {
        if (start == N) {
            val broken = eggs.count { it.s <= 0 }
            answer = maxOf(answer, broken)
            return
        }
        if (eggs[start].s <= 0) {
            dfs(start + 1)
            return
        }

        var cnt = 0
        for (i in 0 until N) {
            if (i == start || eggs[i].s <= 0) {
                continue
            } else {
                cnt++
                eggs[i].s -= eggs[start].w
                eggs[start].s -= eggs[i].w
                dfs(start + 1)
                eggs[i].s += eggs[start].w
                eggs[start].s += eggs[i].w
            }
        }
        if (cnt == 0) {
            dfs(start + 1)
        }
    }

    dfs(0)

    bw.write("$answer")

    bw.flush()
    bw.close()
}


main()