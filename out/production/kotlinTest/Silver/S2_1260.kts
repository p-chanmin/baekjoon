import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))
    var bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, M, V) = br.readLine().split(" ").map { it.toInt() }

    var g: Array<IntArray> = Array(N+1){ IntArray(N+1){ 0 } }
    var dfsVisit: BooleanArray = BooleanArray(N+1){ false }
    var bfsVisit: BooleanArray = BooleanArray(N+1){ false }

    repeat(M){
        br.readLine().split(" ").let {
            g[it[0].toInt()][it[1].toInt()] = 1
            g[it[1].toInt()][it[0].toInt()] = 1
        }
    }

    fun dfs(v: Int){
        dfsVisit[v] = true
        bw.write("${v} ")
        for (i in 1 .. N){
            if(g[v][i] == 1 && !dfsVisit[i]) dfs(i)
        }
    }
    fun bfs(v: Int){
        var queue: Deque<Int> = LinkedList()
        queue.add(v)
        bfsVisit[v] = true

        while(queue.isNotEmpty()){
            var q = queue.poll()
            bw.write("${q} ")

            for (i in 1 .. N){
                if(g[q][i] == 1 && !bfsVisit[i]){
                    bfsVisit[i] = true
                    queue.add(i)
                }
            }
        }

    }
    dfs(V)
    bw.write("\n")
    bw.flush()
    bfs(V)
    bw.flush()
    bw.close()

}


main()