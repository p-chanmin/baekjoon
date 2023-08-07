import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (n, m) = br.readLine().split(" ").map{ it.toInt() }

    var parents: IntArray = IntArray(n+1){ it }

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

    repeat(m){
        br.readLine().split(" ").let {
            if(it[0] == "0"){
                union(it[1].toInt(), it[2].toInt())
            }
            else{
                if(isCycle(it[1].toInt(), it[2].toInt())) bw.write("YES\n")
                else bw.write("NO\n")
            }
        }
    }

    bw.flush()
    bw.close()
}


main()