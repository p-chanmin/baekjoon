import java.io.*
import java.util.*

data class Edge(var i: Int, var j: Int, var w: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, M) = br.readLine().split(" ").map { it.toInt() }

    var pq = PriorityQueue<Edge>{ a, b -> a.w - b.w }
    var parents: IntArray = IntArray(N+1){ it }

    repeat(M){
        br.readLine().split(" ").let {
            pq.add(Edge(it[0].toInt(), it[1].toInt(), it[2].toInt()))
        }
    }

    fun find(x: Int): Int{
        if(x == parents[x]) return x
        else{
            parents[x] = find(parents[x])
            return parents[x]
        }
    }
    fun union(x: Int, y: Int){
        var nx = find(x)
        var ny = find(y)

        if(nx > ny) parents[nx] = ny
        else parents[ny] = nx
    }

    var result = 0
    var cnt = 0
    while(pq.isNotEmpty()){
        var q = pq.poll()
        if(cnt == N-2) break
        if(find(q.i) == find(q.j)) continue

        union(q.i, q.j)
        result += q.w
        cnt++
    }

    bw.write("${result}")

    bw.flush()
    bw.close()
}


main()