import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (n, m) = br.readLine().split(" ").map { it.toInt() }

    var parents: IntArray = IntArray(n){ it }

    fun find(x: Int): Int{
        if(x == parents[x]) return x
        else{
            parents[x] = find(parents[x])
            return parents[x]
        }
    }
    fun union(x: Int, y: Int): Boolean{
        var nx = find(x)
        var ny = find(y)

        if(nx != ny){
            parents[nx] = ny
            return true
        }
        else return false
    }

    var result = ""

    for(i in 1 .. m){
        var lines = br.readLine().split(" ").map { it.toInt() }

        if(union(lines[0], lines[1])){
        }
        else{
            result += "${i}"
            break
        }
    }

    if(result.length == 0) result += "0"
    bw.write(result)

    bw.flush()
    bw.close()
}



main()