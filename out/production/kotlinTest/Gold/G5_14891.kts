import java.io.*
import java.util.*

data class Pos(var i: Int, var j: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var gears: Array<CharArray> = Array(4){ CharArray(8) }

    (0 .. 3).forEach{ i ->
        br.readLine().forEachIndexed { j, c -> gears[i][j] = c }
    }

    fun gearTurn(i: Int, d: Int){
        // d : 1 시계, -1 반시계
        if(d == 1){
            for(j in 7 downTo 1) gears[i][j-1] = gears[i][j].also { gears[i][j] = gears[i][j-1] }
        }
        else if(d == -1) for(j in 0 .. 6) gears[i][j+1] = gears[i][j].also { gears[i][j] = gears[i][j+1] }
    }

    val K = br.readLine().toInt()

    repeat(K){
        val (g, d) = br.readLine().split(" ").map { it.toInt() }
        var dlist: List<Int>
        if((g-1) % 2 == 0 && d == -1) dlist = listOf(-1, 1, -1, 1)
        else if((g-1) % 2 == 0 && d == 1) dlist = listOf(1, -1, 1, -1)
        else if((g-1) % 2 == 1 && d == -1) dlist = listOf(1, -1, 1, -1)
        else dlist = listOf(-1, 1, -1, 1)

        var m: IntArray = IntArray(4)
        m[g-1] = 1
        when(g-1){
            0 ->{
                if(gears[0][2] != gears[1][6]) m[1] = 1
                if(m[1] == 1 && gears[1][2] != gears[2][6]) m[2] = 1
                if(m[2] == 1 && gears[2][2] != gears[3][6]) m[3] = 1
            }
            1 ->{
                if(gears[1][6] != gears[0][2]) m[0] = 1
                if(gears[1][2] != gears[2][6]) m[2] = 1
                if(m[2] == 1 && gears[2][2] != gears[3][6]) m[3] = 1
            }
            2 ->{
                if(gears[2][2] != gears[3][6]) m[3] = 1
                if(gears[2][6] != gears[1][2]) m[1] = 1
                if(m[1] == 1 && gears[1][6] != gears[0][2]) m[0] = 1
            }
            3 ->{
                if(gears[3][6] != gears[2][2]) m[2] = 1
                if(m[2] == 1 && gears[2][6] != gears[1][2]) m[1] = 1
                if(m[1] == 1 && gears[1][6] != gears[0][2]) m[0] = 1
            }
        }

        for(i in 0 .. 3){
            if(dlist[i] * m[i] != 0) gearTurn(i, dlist[i] * m[i])
        }
    }

    bw.write((0 .. 3).map { gears[it][0] }.reversed().joinToString("").toInt(2).toString())

    bw.flush()
    bw.close()
}


main()