import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (n, m) = br.readLine().split(" ").map{ it.toInt() }

    var mapData = Array<IntArray>(n){ IntArray(m){ 0 } }
    var space = arrayListOf<Pair<Int, Int>>()
    var virus = arrayListOf<Pair<Int, Int>>()

    for(i in 0 until n){
        br.readLine().split(" ").forEachIndexed{ j, v ->
            mapData[i][j] = v.toInt()
            if(mapData[i][j] == 0) space.add(Pair(i, j))
            else if(mapData[i][j] == 2) virus.add(Pair(i, j))
        }
    }

    var answer = combi(space, 3).map{
        val k = deepCopy(mapData)
        it.forEach{
            k[it.first][it.second] = 1
        }
        k
    }.map{
        virusSpread(it, virus)
    }.map{
        var safe = 0
        it.forEach{
            safe += it.count{ it == 0 }
        }
        safe
    }.maxOf{ it }

    println(answer)

}

fun deepCopy(array: Array<IntArray>): Array<IntArray> {
    return Array(array.size) { row ->
        array[row].clone()
    }
}

fun virusSpread(m: Array<IntArray>, virus: ArrayList<Pair<Int, Int>>): Array<IntArray>{

    virus.forEach{
        spread(m, it.first, it.second)
    }

    return m
}

fun spread(m: Array<IntArray>, i: Int, j: Int){
    if(i != 0 && m[i-1][j] == 0){
        m[i-1][j] = 2
        spread(m, i-1, j)
    }
    if(i != m.size-1 && m[i+1][j] == 0){
        m[i+1][j] = 2
        spread(m, i+1, j)
    }
    if(j != 0 && m[i][j-1] == 0){
        m[i][j-1] = 2
        spread(m, i, j-1)
    }
    if(j != m[0].size-1 && m[i][j+1] == 0){
        m[i][j+1] = 2
        spread(m, i, j+1)
    }
}

fun combi(l: List<Pair<Int, Int>>, n:Int, result: List<Pair<Int, Int>> = listOf(), index:Int = 0): List<List<Pair<Int, Int>>> {
    return if (result.size >= n){
        listOf(result)
    }else{
        l.slice(index until l.size).flatMapIndexed { i, v -> combi(l, n, result + v, index + i + 1)}
    }
}

main()