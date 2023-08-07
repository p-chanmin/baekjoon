import java.io.*
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (N, M) = br.readLine().split(" ").map{ it.toInt() }

    var trues: BooleanArray = BooleanArray(N+1){ false }
    var g: Array<IntArray> = Array(N+1){ IntArray(N+1){ 0 } }

    br.readLine().split(" ").map{ it.toInt() }.let { it.slice( 1 until it.size) }.forEach {
        trues[it] = true
    }

    var parties: ArrayList<List<Int>> = arrayListOf()

    repeat(M){
        parties.add(br.readLine().split(" ").map{ it.toInt() }.let { it.slice( 1 until it.size) })
        if(parties.last().size > 1){
            for(i in 1 until parties.last().size){
                g[parties.last()[i]][parties.last()[i-1]] = 1
                g[parties.last()[i-1]][parties.last()[i]] = 1
            }
        }
    }

    fun bfs(p: List<Int>): Boolean{

        var queue: Deque<Int> = LinkedList()
        var visit: BooleanArray = BooleanArray(N+1){ false }
        for( i in p ) visit[i] = true
        queue.addAll(p)

        var answer = true

        while(queue.isNotEmpty()){
            var q = queue.poll()

            if(trues[q]){
                answer = false
                break
            }

            for(i in 1 .. N){
                if( g[q][i] == 1 && !visit[i] ){
                    visit[i] = true
                    queue.add(i)
                }
            }

        }

        return answer
    }

    bw.write("${parties.map { bfs(it) }.count { it }}")

    bw.flush()
    bw.close()
}



main()