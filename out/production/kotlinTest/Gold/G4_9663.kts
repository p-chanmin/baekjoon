import java.io.*
import java.util.*
import kotlin.math.abs


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    var N = br.readLine().toInt()

    var vx: IntArray = IntArray(N){ 0 }
    var vy: IntArray = IntArray(N){ 0 }

    fun go(y: Int, x: Int): Int{

        for(i in 0 until y){
            if(y == vy[i]) return 0
            if(x == vx[i]) return 0
            if(abs(x-vx[i]) == abs(y-vy[i])) return 0
        }

        if(y == N-1){
            return 1
        }

        vy[y] = y
        vx[y] = x

        var answer = 0;
        for(i in 0 until N) answer += go(y+1, i)
        return answer
    }


    var answer = 0
    for(i in 0 until N) answer += go(0, i)
    println(answer)

}


main()