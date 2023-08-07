import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))

    var (N, M, inven) = br.readLine().split(" ").map{ it.toInt() }

    var land: IntArray = IntArray(N*M){ 0 }
    var time: IntArray = IntArray(257){ 0 }

    (0 until N).forEach{ i ->
        br.readLine().split(" ").forEachIndexed{ j, v ->
            land[i*M+j] = v.toInt()
        }
    }

    land.sortDescending()

    for(h in 0 .. 256){
        var items = inven
        for(l in land){

            if( l > h ){
                time[h] += (l-h) * 2
                items += l-h
            }
            if( l < h ){
                if(items >= h-l){
                    time[h] += h-l
                    items -= h-l
                }
                else{
                    time[h] = 999999999
                    break
                }
            }
        }

    }
    var minTime = time.minOf { it }
    var high = time.indexOfLast { it == minTime }
    println("${minTime} ${high}")
}


main()