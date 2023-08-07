import java.io.*
import java.util.*

data class Edge(var i: Int, var j: Int, var weight: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (V, E) = br.readLine().split(" ").map { it.toInt() }

    var parent: IntArray = IntArray(V+1){ it }
    var pq = PriorityQueue<Edge>(){ a, b -> a.weight - b.weight }

    repeat(E){
        br.readLine().split(" ").let {
            pq.add(Edge(it[0].toInt(), it[1].toInt(), it[2].toInt()))
        }
    }

    fun find(x: Int): Int{
        return if (x == parent[x]) x
        else{
            parent[x] = find(parent[x])
            return parent[x]
        }
    }
    fun union(x: Int, y: Int): Boolean{
        var nx = find(x)
        var ny = find(y)
        return if(nx != ny){
            parent[nx] = ny
            true
        }
        else false
    }

    var result = 0

    while(pq.isNotEmpty()){
        var q = pq.poll()
        if(union(q.i, q.j)) result += q.weight
    }

    bw.write("${result}")

    bw.flush()
    bw.close()
}




main()