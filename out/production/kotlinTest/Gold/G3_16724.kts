import java.io.*
import java.util.*
import kotlin.collections.ArrayList

data class Pos(var i: Int, var j: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, M) = br.readLine().split(" ").map{ it.toInt() }

    var m: Array<CharArray> = Array(N){ CharArray(M){ '.' } }
    var visit: Array<BooleanArray> = Array(N){ BooleanArray(M){ false } }

    repeat(N){ i ->
        br.readLine().forEachIndexed{ j, c -> m[i][j] = c }
    }

    var c = 0
    var tmp: ArrayList<Pos> = arrayListOf()
    fun dfs(i: Int, j: Int){
        if(i in 0 until N && j in 0 until M && !visit[i][j]){
            tmp.add(Pos(i, j))
            visit[i][j] = true
            when(m[i][j]){
                'D' -> {
                    dfs(i + 1, j)
                }
                'U' -> {
                    dfs(i - 1, j)
                }
                'L' -> {
                    dfs(i, j - 1)
                }
                'R' -> {
                    dfs(i, j + 1)
                }
            }
        }
        else{
            if(Pos(i, j) in tmp) c += 1
        }
    }
    for(i in 0 until N) for(j in 0 until M){
        if(!visit[i][j]){
            dfs(i, j)
            tmp = arrayListOf()
        }
    }

    bw.write("${c}")

    bw.flush()
    bw.close()
}


main()