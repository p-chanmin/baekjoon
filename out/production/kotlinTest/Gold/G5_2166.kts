import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

data class Pos(val x: Long, val y: Long)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var N = br.readLine().toInt()

    var p: ArrayList<Pos> = arrayListOf()

    repeat(N){
        br.readLine().split(" ").let {
            p.add(Pos(it[0].toLong(), it[1].toLong()))
        }
    }

    p.add(p.first())

    var tmp: Long = 0

    for(i in 0 until N){
        tmp += (p[i].x * p[i+1].y)
        tmp -= (p[i+1].x * p[i].y)
    }
    var answer: Double = abs(tmp).toDouble() / 2.0

    bw.write( String.format("%.1f", answer) )

    bw.flush()
    bw.close()
}


main()