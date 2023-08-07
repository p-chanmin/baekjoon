import java.io.*
import java.util.*

data class Pos(var i: Int, var time: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var m: IntArray = IntArray(100001)
    var visit: BooleanArray = BooleanArray(100001){ false }

    var (N, M) = br.readLine().split(" ").map { it.toInt() }

    m[M] = 1

    var queue: Deque<Pos> = LinkedList()
    visit[N] = true
    queue.add(Pos(N, 0))

    var cost = Int.MAX_VALUE
    var cases = 0

    while(queue.isNotEmpty()){
        var q = queue.poll()

        visit[q.i] = true

        if(q.i !in 0 until 100001) continue
        if(q.time > cost) break

        if(m[q.i] == 1){
            if(cost == Int.MAX_VALUE) cost = q.time
            cases++
        }

        if(q.i+1 in 0 until 100001 && !visit[q.i + 1]){
            queue.add(Pos(q.i + 1, q.time+1))
        }
        if(q.i-1 in 0 until 100001 && !visit[q.i - 1]){
            queue.add(Pos(q.i - 1, q.time+1))
        }
        if(q.i*2 in 0 until 100001 && !visit[2 * q.i]){
            queue.add(Pos(2 * q.i, q.time+1))
        }
    }

    bw.write("${cost}\n")
    bw.write("${cases}")

    bw.flush()
    bw.close()
}


main()