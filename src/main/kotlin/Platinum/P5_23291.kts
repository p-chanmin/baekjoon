import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    var (n, k) = br.readLine().split(" ").map { it.toInt() }

    var fish = br.readLine().split(" ").map { it.toInt() }

    var glass: Array<IntArray> = Array(n){ IntArray(n){ 0 } }
    var tmp: Array<IntArray>

    var answer: Int = 0

    fun divideFish(){
        tmp = Array(n){ IntArray(n){ 0 } }
        for(i in 0 until n){
            for(j in 0 until n){
                if(j+1 < n && glass[i][j+1] != 0){
                    if(glass[i][j] > glass[i][j+1]){
                        tmp[i][j] -= (glass[i][j] - glass[i][j+1]) / 5
                        tmp[i][j+1] += (glass[i][j] - glass[i][j+1]) / 5
                    }
                    else{
                        tmp[i][j] += (glass[i][j+1] - glass[i][j]) / 5
                        tmp[i][j+1] -= (glass[i][j+1] - glass[i][j]) / 5
                    }
                }
            }
        }
        for(i in 0 until n){
            for(j in 0 until n){
                if(i+1 < n && glass[i+1][j] != 0){
                    if(glass[i][j] > glass[i+1][j]){
                        tmp[i][j] -= (glass[i][j] - glass[i+1][j]) / 5
                        tmp[i+1][j] += (glass[i][j] - glass[i+1][j]) / 5
                    }
                    else{
                        tmp[i][j] += (glass[i+1][j] - glass[i][j]) / 5
                        tmp[i+1][j] -= (glass[i+1][j] - glass[i][j]) / 5
                    }
                }
            }
        }

        tmp.forEachIndexed{ i , v ->
            v.forEachIndexed { j, f ->
                glass[i][j] += f
            }
        }
    }

    while(fish.maxOf { it } - fish.minOf { it } > k) {
        // 최소 물고기 보충
        var minFish: Int = fish.minOf { it }
        fish = fish.map { if(it == minFish) it+1 else it }

        // 어항 변형
        glass = Array(n){ IntArray(n){ 0 } }
        fish.forEachIndexed{ i, v -> glass[0][i] = v }

        var level = 1
        var h = 1
        var w = 1
        var left = n

        while(left-h >= w){
            tmp = Array(n){ IntArray(n){ 0 } }
            for(i in (w-1 downTo 0)){
                for(j in (0 until h)){
                    tmp[w-i][j] = glass[j][i]
                }
            }
            for(i in (w until left)){
                tmp[0][i-w] = glass[0][i]
            }
            glass = tmp

            left -= w
            if(level % 2 == 1){
                var t = w
                w = h
                h = t + 1
            }
            else{
                w = h
            }
            level++
        }
        // 물고기 분배
        divideFish()

        var f = arrayListOf<Int>()
        for(j in 0 until n){
            for(i in 0 until n){
                if(glass[i][j] != 0) f.add(glass[i][j])
                else break
            }
        }
        // 어항 일렬 정렬
        fish = f.toList()

        // 어항 중앙 분열
        var chunkedFish = fish.chunked(n / 4)
        chunkedFish = listOf(chunkedFish[3], chunkedFish[0].reversed(), chunkedFish[1], chunkedFish[2].reversed())

        glass = Array(n){ IntArray(n){ 0 } }
        chunkedFish.forEachIndexed{ i, list ->
            list.forEachIndexed { j, v ->
                glass[i][j] = v
            }
        }

        // 물고기 분배
        divideFish()

        f = arrayListOf<Int>()
        for(j in 0 until n){
            for(i in 0 until n){
                if(glass[i][j] != 0) f.add(glass[i][j])
                else break
            }
        }
        // 어항 일렬 정렬
        fish = f.toList()
        answer++
    }
    println(answer)

}


main()