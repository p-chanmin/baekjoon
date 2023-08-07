import java.io.*
import java.util.*
import kotlin.math.*


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (n, m) = br.readLine().split(" ").map{ it.toInt() }

    var map = Array<IntArray>(n){ IntArray(n){ 0 } }
    var chicken: ArrayList<Pair<Int, Int>> = arrayListOf()
    var home: ArrayList<Pair<Int, Int>> = arrayListOf()

    for(i in 0 until n){
        br.readLine().split(" ").forEachIndexed{ j, v ->
            map[i][j] = v.toInt()
            if(v.toInt() == 2) chicken.add(Pair(i, j))
            else if(v.toInt() == 1) home.add(Pair(i, j))
        }
    }

    var com = combi(chicken, m)

    println(com.map{
        distance(home, it)
    }.minOf { it })

}
fun combi(l: List<Pair<Int, Int>>, n:Int, result: List<Pair<Int, Int>> = listOf(), index:Int = 0): List<List<Pair<Int, Int>>> {
    return if (result.size >= n){
        listOf(result)
    }else{
        l.slice(index until l.size).flatMapIndexed { i, v -> combi(l, n, result + v, index + i + 1)}
    }
}

fun distance(home: ArrayList<Pair<Int, Int>>, chicken: List<Pair<Int, Int>>): Int{
    return home.map{ h ->
        chicken.map{ abs(h.first - it.first) + abs(h.second - it.second) }.minOf { it }
    }.sumOf { it }
}


main()