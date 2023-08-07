import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

var line1 = IntArray(8){ n -> if(n % 2 == 0) 0 else 1 }
var line2 = IntArray(8){ n -> if(n % 2 == 0) 1 else 0 }

var answer1 = Array(8){ n -> if(n % 2 == 0) line1 else line2 }.flatMap { it.toList() }
var answer2 = Array(8){ n -> if(n % 2 == 0) line2 else line1 }.flatMap { it.toList() }

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))

    var (N, M) = br.readLine().split(" ").map{ it.toInt() }

    var board: Array<IntArray> = Array(N){ IntArray(M){ 0 } }

    (0 until N).forEach { i ->
        br.readLine().forEachIndexed{ j, c ->
            if(c == 'B') board[i][j] = 0
            else board[i][j] = 1
        }
    }

    var result: ArrayList<Int> = arrayListOf()

    for(i in 0 .. N-8){
        for(j in 0 .. M-8){
            result.add(Paint((i until i+8).map{ board[it].slice( j until j+8) }))
        }
    }
    println(result.minOf { it })
}
fun Paint(board: List<List<Int>>): Int{

    var b = board.flatMap { it.toList() }

    var result1 = 0
    var result2 = 0

    for(i in 0 until 64){
        if(b[i] != answer1[i]) result1++
        if(b[i] != answer2[i]) result2++
    }
    return min(result1, result2)
}


main()