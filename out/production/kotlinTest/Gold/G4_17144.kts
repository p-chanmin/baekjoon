import java.io.*
import java.util.*
import kotlin.math.*

data class Air(var y1: Int, var y2: Int)

fun main(){
    var br = BufferedReader(InputStreamReader(System.`in`))

    var (r, c, t) = br.readLine().split(" ").map{ it.toInt() }

    var map: Array<IntArray> = Array(r){ IntArray(c){ 0 } }
    var air: Air = Air(0, 0)

    for(i in 0 until r){
        br.readLine().split(" ").forEachIndexed{ j, v ->
            map[i][j] = v.toInt()
            if(map[i][j] == -1 && air.y1 == 0) air.y1 = i
            else if(map[i][j] == -1 && air.y2 == 0) air.y2 = i
        }
    }

    repeat(t){
        spread(map)
        airClear(map, air)
    }
    println(
        map.sumOf { it.sumOf { it } } + 2
    )

}
fun spread(map: Array<IntArray>){

    var tmap: Array<IntArray> = Array(map.size){ IntArray(map[0].size){ 0 } }

    for(i in 0 until map.size){
        for(j in 0 until map[0].size){
            if(map[i][j] != -1 && map[i][j] != 0){
                var cnt = 0
                if(i+1 >= 0 && i+1 < map.size && j >= 0 && j < map[0].size && map[i+1][j] != -1){
                    tmap[i+1][j] += (map[i][j] / 5)
                    cnt++
                }
                if(i-1 >= 0 && i-1 < map.size && j >= 0 && j < map[0].size && map[i-1][j] != -1){
                    tmap[i-1][j] += (map[i][j] / 5)
                    cnt++
                }
                if(i >= 0 && i < map.size && j+1 >= 0 && j+1 < map[0].size && map[i][j+1] != -1){
                    tmap[i][j+1] += (map[i][j] / 5)
                    cnt++
                }
                if(i >= 0 && i < map.size && j-1 >= 0 && j-1 < map[0].size && map[i][j-1] != -1){
                    tmap[i][j-1] += (map[i][j] / 5)
                    cnt++
                }
                map[i][j] = map[i][j] - ((map[i][j] / 5) * cnt)
            }
        }
    }
    for(i in 0 until map.size){
        for(j in 0 until map[0].size){
            map[i][j] += tmap[i][j]
        }
    }
}
fun airClear(map: Array<IntArray>, air: Air){

    var tmp = 0
    for(i in 1 until map[0].size){
        map[air.y1][i] = tmp.also { tmp = map[air.y1][i] }
    }
    for(i in air.y1-1 downTo 0){
        map[i][map[0].size-1] = tmp.also { tmp = map[i][map[0].size-1] }
    }
    for(i in map[0].size-2 downTo 0){
        map[0][i] = tmp.also { tmp = map[0][i] }
    }
    for(i in 1 until air.y1){
        map[i][0] = tmp.also { tmp = map[i][0] }
    }
    tmp = 0
    for(i in 1 until map[0].size){
        map[air.y2][i] = tmp.also { tmp = map[air.y2][i] }
    }
    for(i in air.y2+1 until map.size){
        map[i][map[0].size-1] = tmp.also { tmp = map[i][map[0].size-1] }
    }
    for(i in map[0].size-2 downTo 0){
        map[map.size-1][i] = tmp.also { tmp = map[map.size-1][i] }
    }
    for(i in map.size-2 downTo  air.y2+1){
        map[i][0] = tmp.also { tmp = map[i][0] }
    }


}



main()