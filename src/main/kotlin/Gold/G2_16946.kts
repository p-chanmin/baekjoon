import java.io.*
import java.util.*
import kotlin.collections.ArrayList

data class Pos(var i: Int, var j: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, M) = br.readLine().split(" ").map { it.toInt() }

    var m: Array<IntArray> = Array(N){ IntArray(M) }

    repeat(N){ i ->
        br.readLine().forEachIndexed { j, v ->
            m[i][j] = v.toString().toInt()
        }
    }

    var code: Array<IntArray> = Array(N){ IntArray(M) }
    var move: MutableMap<Int, Int> = mutableMapOf()
    var visit: Array<BooleanArray> = Array(N){ BooleanArray(M){ false } }
    var di = listOf(-1, 1, 0, 0)
    var dj = listOf(0, 0, -1, 1)

    var queue: Deque<Pos> = LinkedList()
    var c = 1

    for(i in 0 until N) for(j in 0 until M){
        if(m[i][j] == 0 && !visit[i][j]){
            var mv = 1
            queue.add(Pos(i, j))
            visit[i][j] = true
            code[i][j] = c

            while(queue.isNotEmpty()){
                var q = queue.poll()

                for(k in 0 .. 3){
                    var ni = q.i + di[k]
                    var nj = q.j + dj[k]

                    if(ni in 0 until N && nj in 0 until M && !visit[ni][nj] && m[ni][nj] == 0){
                        queue.add(Pos(ni, nj))
                        visit[ni][nj] = true
                        code[ni][nj] = c
                        mv++
                    }
                }
            }
            move.put(c, mv)
            c += 1
        }
    }

    for(i in 0 until N) for(j in 0 until M){
        var s: MutableSet<Int> = mutableSetOf()
        if(m[i][j] == 1){
            for(k in 0 .. 3){
                var ni = i + di[k]
                var nj = j + dj[k]
                if(ni in 0 until N && nj in 0 until M){
                    s.add(code[ni][nj])
                }
            }
            m[i][j] = (s.sumOf { move.getOrDefault(it, 0) } + 1) % 10
        }
    }

    m.forEach { bw.write("${it.joinToString("")}\n") }

    bw.flush()
    bw.close()
}


main()