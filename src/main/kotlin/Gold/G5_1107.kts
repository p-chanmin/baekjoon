import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    var desti = br.readLine().toInt()
    var now = 100

    var n = br.readLine().toInt()
    var button: List<Int>
    var breaked: List<Int> = listOf()

    if(n != 0) breaked = br.readLine().split(" ").map{ it.toInt() }
    button = (0 .. 9).mapNotNull { if(it in breaked) null else it }

    var move: List<List<Int>> = listOf(listOf(now))

    fun combi(n: Int, l: List<Int> = listOf()): List<List<Int>>{
        return if(l.size >= n){
            listOf(l)
        }else{
            button.flatMap{ combi(n, l + listOf(it)) }
        }
    }

    if(now == desti) println(0)
    else{
        if(button.isNotEmpty()){
            if(button.last() != 0){
                move += listOf((0 until desti.toString().length-1).map{ button.last() })
            }
        }
        move += combi(desti.toString().length)
        if(button.isNotEmpty()){
            if(button.first() != 0){
                move += listOf((0 until desti.toString().length+1).map{ button.first() })
            }
            else if(button.size >= 2){
                move += listOf((0 until desti.toString().length+1).map{ if(it == 0) button[1] else button[0] })
            }
        }


        println(
            move.mapNotNull{
                var ns = it.joinToString("")
                if(ns != ""){
                    var num = ns.toInt()
                    var t = if(num != 100) num.toString().length + abs(desti - num) else abs(desti - num)
                    t
                }
                else null
            }.minOf { it }
        )
    }
}



main()