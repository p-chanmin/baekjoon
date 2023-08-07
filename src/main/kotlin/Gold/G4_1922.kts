import java.io.*
import java.util.*

data class Edge(var x: Int, var y: Int, var v: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var N = br.readLine().toInt()
    var M = br.readLine().toInt()

    var parents: IntArray = IntArray(N+1){ it }

    fun find(x: Int): Int{
        return if(x == parents[x]) x
        else{
            parents[x] = find(parents[x])
            parents[x]
        }
    }
    fun union(x: Int, y: Int){
        var nx = find(x)
        var ny = find(y)

        if(nx > ny) parents[nx] = ny
        else parents[ny] = nx
    }

    fun isCycle(x: Int, y: Int): Boolean{
        return if(find(x) == find(y)) true
        else false
    }

    var pq = PriorityQueue<Edge>{ a, b -> a.v - b.v }

    repeat(M){
        br.readLine().split(" ").let {
            pq.add( Edge(it[0].toInt(), it[1].toInt(), it[2].toInt()) )
        }
    }

    var answer = 0
    while(pq.isNotEmpty()){
        var q = pq.poll()
        if(isCycle(q.x, q.y)) continue
        union(q.x, q.y)
        answer += q.v
    }
    bw.write("${answer}")

    bw.flush()
    bw.close()
}


main()