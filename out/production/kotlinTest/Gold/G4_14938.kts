import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (n, m, r) = br.readLine().split(" ").map { it.toInt() }

    val items: IntArray = IntArray(n+1)
    br.readLine().split(" ").forEachIndexed { i, v -> items[i+1] = v.toInt() }

    var g: Array<IntArray> = Array(n+1){ IntArray(n+1) }
    repeat(r){
        br.readLine().split(" ").let{
            g[it[0].toInt()][it[1].toInt()] = it[2].toInt()
            g[it[1].toInt()][it[0].toInt()] = it[2].toInt()
        }
    }

    fun dijkstra(start: Int): Int{

        var c: IntArray = IntArray(n+1){ Int.MAX_VALUE }
        var queue = PriorityQueue<Pair<Int, Int>>(){ a, b -> a.second - b.second }

        c[start] = 0
        queue.add(Pair(start, 0))
        while(queue.isNotEmpty()){
            var q = queue.poll()
            if(q.second > c[q.first]) continue

            for(i in 1 .. n){
                if(g[q.first][i] != 0){
                    if(c[i] > c[q.first] + g[q.first][i]){
                        c[i] = c[q.first] + g[q.first][i]
                        queue.add(Pair(i, c[q.first] + g[q.first][i]))
                    }
                }
            }
        }
        return c.mapIndexed{ i, v -> if(v <= m) items[i] else 0 }.sumOf { it }
    }

    bw.write("${(1 .. n).maxOf{ dijkstra(it) }}")

    bw.flush()
    bw.close()
}


main()