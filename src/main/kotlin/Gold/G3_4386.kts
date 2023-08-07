import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.*

data class Line(var i: Int, var j: Int, var length: Double)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var N = br.readLine().toInt()

    var stars: ArrayList<Pair<Double, Double>> = arrayListOf()
    var parents: IntArray = IntArray(N+1){ it }

    repeat(N){
        br.readLine().split(" ").let {
            stars.add(Pair(it[0].toDouble(), it[1].toDouble()))
        }
    }

    var pq = PriorityQueue<Line>(){ a, b -> if(a.length < b.length) -1 else 1 }

    for(i in 0 until stars.size-1){
        for(j in i+1 until stars.size){
            var si = stars[i]
            var sj = stars[j]
            var length = sqrt((si.first - sj.first).pow(2) + (si.second - sj.second).pow(2))
            pq.add(Line(i, j, length))
        }
    }

    fun find(x: Int): Int{
        return if(x == parents[x]) x
        else{
            parents[x] = find(parents[x])
            parents[x]
        }
    }
    fun union(x: Int, y: Int): Boolean{
        var nx = find(x)
        var ny = find(y)

        return if(nx != ny){
            parents[nx] = ny
            true
        }
        else false
    }

    var result = 0.0

    while(pq.isNotEmpty()){
        var q = pq.poll()

        if(union(q.i, q.j)) result += q.length
    }

    bw.write("${round(result*100)/100}")

    bw.flush()
    bw.close()
}


main()