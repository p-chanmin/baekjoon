import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))

    var n = br.readLine().toInt()

    var pos: ArrayList<List<Int>> = arrayListOf()

    var minx: Int = 101
    var miny: Int = 101
    var maxx: Int = 0
    var maxy: Int = 0

    repeat(n){
        pos.add(br.readLine().split(" ").map { it.toInt() })
    }
    pos.forEach{
        minx = min(minx, it[0])
        maxx = max(maxx, it[0]+10)
        miny = min(miny, it[1])
        maxy = max(maxy, it[1]+10)
    }

    var paper: Array<IntArray> = Array(maxy - miny){ IntArray(maxx - minx) { 0 } }

    pos.forEach {
        (it[1]-miny until it[1]+10-miny).forEach{ y ->
            (it[0]-minx until it[0]+10-minx).forEach{ x ->
                paper[y][x] = 1
            }
        }
    }

    println(
        paper.sumOf{ it.sumOf { it } }
    )

}


main()