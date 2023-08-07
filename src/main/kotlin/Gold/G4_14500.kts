import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (n, m) = br.readLine().split(" ").map{ it.toInt() }

    var map: Array<IntArray> = Array(n){ IntArray(m){ 0 } }

    for(i in 0 until n){
        br.readLine().split(" ").map{ it.toInt() }.forEachIndexed{ j, v ->
            map[i][j] = v
        }
    }

    var answer: ArrayList<Int> = arrayListOf()

    var index_14 = getIndex(n, m, 1, 4)
    var index_41 = getIndex(n, m, 4, 1)
    var index_23 = getIndex(n, m, 2, 3)
    var index_32 = getIndex(n, m, 3, 2)
    var index_22 = getIndex(n, m, 2, 2)

    index_14.forEach{
        answer.add(map[it.y][it.x] + map[it.y+1][it.x] + map[it.y+2][it.x] + map[it.y+3][it.x])
    }
    index_41.forEach{
        answer.add(map[it.y][it.x] + map[it.y][it.x+1] + map[it.y][it.x+2] + map[it.y][it.x+3])
    }
    index_22.forEach{
        answer.add(map[it.y][it.x] + map[it.y][it.x+1] + map[it.y+1][it.x] + map[it.y+1][it.x+1])
    }
    index_23.forEach{
        answer.add(map[it.y][it.x] + map[it.y+1][it.x] + map[it.y+2][it.x] + map[it.y+2][it.x+1])
        answer.add(map[it.y][it.x+1] + map[it.y+1][it.x+1] + map[it.y+2][it.x+1] + map[it.y+2][it.x])

        answer.add(map[it.y][it.x] + map[it.y][it.x+1] + map[it.y+1][it.x] + map[it.y+2][it.x])
        answer.add(map[it.y][it.x] + map[it.y][it.x+1] + map[it.y+1][it.x+1] + map[it.y+2][it.x+1])

        answer.add(map[it.y][it.x] + map[it.y+1][it.x] + map[it.y+1][it.x+1] + map[it.y+2][it.x+1])
        answer.add(map[it.y][it.x+1] + map[it.y+1][it.x] + map[it.y+1][it.x+1] + map[it.y+2][it.x])

        answer.add(map[it.y+1][it.x] + map[it.y][it.x+1] + map[it.y+1][it.x+1] + map[it.y+2][it.x+1])
        answer.add(map[it.y][it.x] + map[it.y+1][it.x] + map[it.y+2][it.x] + map[it.y+1][it.x+1])
    }
    index_32.forEach{
        answer.add(map[it.y][it.x] + map[it.y][it.x+1] + map[it.y][it.x+2] + map[it.y+1][it.x])
        answer.add(map[it.y][it.x] + map[it.y][it.x+1] + map[it.y][it.x+2] + map[it.y+1][it.x+2])

        answer.add(map[it.y][it.x] + map[it.y+1][it.x] + map[it.y+1][it.x+1] + map[it.y+1][it.x+2])
        answer.add(map[it.y+1][it.x] + map[it.y+1][it.x+1] + map[it.y+1][it.x+2] + map[it.y][it.x+2])

        answer.add(map[it.y][it.x+1] + map[it.y][it.x+2] + map[it.y+1][it.x] + map[it.y+1][it.x+1])
        answer.add(map[it.y][it.x] + map[it.y][it.x+1] + map[it.y+1][it.x+1] + map[it.y+1][it.x+2])

        answer.add(map[it.y][it.x] + map[it.y][it.x+1] + map[it.y][it.x+2] + map[it.y+1][it.x+1])
        answer.add(map[it.y][it.x+1] + map[it.y+1][it.x] + map[it.y+1][it.x+1] + map[it.y+1][it.x+2])
    }

    println(answer.maxOf{ it })

}

data class Point(var x: Int, var y: Int)

fun getIndex(n: Int, m: Int, x: Int, y: Int): ArrayList<Point>{
    var answer: ArrayList<Point> = arrayListOf()
    for( i in (0 .. n - y) ){
        for( j in (0 .. m - x) ){
            answer.add(Point(j, i))
        }
    }
    return answer
}


main()