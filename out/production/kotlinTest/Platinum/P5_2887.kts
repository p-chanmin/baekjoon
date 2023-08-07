import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

data class Edge(var i: Int, var j: Int, var d: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()
    var planets: ArrayList<List<Int>> = arrayListOf()
    var parents: IntArray = IntArray(N){ it }

    repeat(N){ i ->
        planets.add(br.readLine().split(" ").let { listOf(i, it[0].toInt(), it[1].toInt(), it[2].toInt()) })
    }

    var pq = PriorityQueue<Edge>{ a, b -> a.d - b.d }

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

    (1 .. 3).forEach { k ->
        var planet = planets.sortedBy { it[k] }
        for(i in 1 until N){
            pq.add(Edge(planet[i-1][0], planet[i][0], abs(planet[i-1][k] - planet[i][k])))
        }
    }

    var result = 0

    while(pq.isNotEmpty()){
        var q = pq.poll()

        if(isCycle(q.i, q.j)) continue

        union(q.i, q.j)
        result += q.d
    }

    bw.write("${result}")

    bw.flush()
    bw.close()
}


main()