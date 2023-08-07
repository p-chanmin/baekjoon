import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    var (N, L, R) = br.readLine().split(" ").map{ it.toInt() }

    var countries:Array<IntArray> = Array(N){ IntArray(N){ 0 } }

    for(i in 0 until N){
        br.readLine().split(" ").forEachIndexed { j, p ->
            countries[i][j] = p.toInt()
        }
    }

    fun Move(): Boolean{
        var uni: MutableMap<Pair<Int, Int>, ArrayList<Pair<Int, Int>>> = mutableMapOf()
        var check: Array<Array<Pair<Int, Int>>> = Array(N){ i -> Array(N){ j -> Pair(i, j) } }

        fun find(x: Pair<Int, Int>): Pair<Int, Int>{
            if(check[x.first][x.second] == x) return x
            else return find(check[x.first][x.second])
        }
        fun union(inputX: Pair<Int, Int>, inputY: Pair<Int, Int>){
            var x = find(inputX)
            var y = find(inputY)
            check[y.first][y.second] = x
        }

        for(i in 0 until N-1){
            for(j in 0 until N){
                if(abs(countries[i][j] - countries[i+1][j]) in L .. R){
                    var x = find(Pair(i, j))
                    var y = find(Pair(i+1, j))
                    union(x, y)
                }
            }
        }
        for(i in 0 until N){
            for(j in 0 until N-1){
                if(abs(countries[i][j] - countries[i][j+1]) in L .. R){
                    var x = find(Pair(i, j))
                    var y = find(Pair(i, j+1))
                    union(x, y)
                }
            }
        }

        for(i in 0 until N){
            for(j in 0 until N){
                var root = find(check[i][j])
                if(uni.containsKey(root)){
                    uni[root]!!.add(Pair(i, j))
                }
                else uni[root] = arrayListOf(Pair(i, j))
            }
        }
        if(uni.keys.size == N*N) return false

        for(v in uni.values){
            var total = v.sumOf{ countries[it.first][it.second] }
            v.forEach {
                countries[it.first][it.second] = total / v.size
            }
        }

        return true
    }

    var answer = 0
    while (Move()){
        answer++
    }
    println(answer)

}


main()