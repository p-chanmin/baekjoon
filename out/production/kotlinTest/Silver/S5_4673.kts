import java.io.*
import java.util.*

fun main() {

    var selfNum = Array(10001){ true }

    selfNum[0] = false
    for(i in 1 .. 10000){
        if(selfNum[i]){
            var n = d(i)
            while(n <= 10000){
                selfNum[n] = false
                n = d(n)
            }
        }
    }
    selfNum.forEachIndexed { i, v ->
        if(v) println(i)
    }

}

fun d(n: Int): Int{
    return n + n.toString().map{ it.toString() }.sumOf { it.toInt() }
}

main()