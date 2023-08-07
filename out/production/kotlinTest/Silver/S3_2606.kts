import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))
    var bw = BufferedWriter(OutputStreamWriter(System.out))

    var N = br.readLine().toInt()
    var M = br.readLine().toInt()

    var g: Array<IntArray> = Array(N+1){ IntArray(N+1){ 0 } }
    var bfsVisit: BooleanArray = BooleanArray(N+1){ false }

    repeat(M){
        br.readLine().split(" ").let {
            g[it[0].toInt()][it[1].toInt()] = 1
            g[it[1].toInt()][it[0].toInt()] = 1
        }
    }

    var answer = -1

    fun bfs(v: Int){
        var queue: Deque<Int> = LinkedList()
        queue.add(v)
        bfsVisit[v] = true

        while(queue.isNotEmpty()){
            var q = queue.poll()
            answer++

            for (i in 1 .. N){
                if(g[q][i] == 1 && !bfsVisit[i]){
                    bfsVisit[i] = true
                    queue.add(i)
                }
            }
        }

    }

    bfs(1)
    bw.write("$answer")
    bw.flush()
    bw.close()

}


main()