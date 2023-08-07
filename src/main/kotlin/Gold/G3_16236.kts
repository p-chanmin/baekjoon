import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.*

data class Shark(var i: Int, var j: Int, var level: Int, var move: Int, var eat: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()

    var fish: MutableMap<Int, Int> = (1 .. 6).map{ Pair(it, 0) }.toMap().toMutableMap()
    var shark: Shark = Shark(0, 0, 2, 0, 0)

    var map = Array<IntArray>(n){ IntArray(n){ 0 } }
    (0 until n).forEach{ i ->
        br.readLine().split(" ").forEachIndexed{ j, v ->
            if(v.toInt() == 9){
                shark.i = i
                shark.j = j
            }
            else if(v.toInt() != 0){
                fish[v.toInt()] = fish[v.toInt()]!! + 1
                map[i][j] = v.toInt()
            }
        }
    }


    while(true){
        var s = MoveShark(n, map, shark)
        if(s == shark) break
        else shark = s
    }
    println(shark.move)
}

fun MoveShark(n: Int, map:Array<IntArray>, shark: Shark): Shark{

    var visit: Array<Array<Boolean>> = Array(n){ Array(n){ false } }
    var answer: MutableList<Shark> = mutableListOf()
    var q: Deque<Shark> = LinkedList()
    q.add(shark)
    visit[shark.i][shark.j] = true
    while(q.isNotEmpty()){
        var s = q.poll()
        if(map[s.i][s.j] != 0 && map[s.i][s.j] < s.level){
            if(s.eat+1 == s.level) answer.add(Shark(s.i, s.j, s.level+1, s.move, 0))
            else answer.add(Shark(s.i, s.j, s.level, s.move, s.eat+1))
        }

        if(s.i-1 >= 0 && s.i-1 < n && s.j >= 0 && s.j < n && map[s.i-1][s.j] <= s.level && !visit[s.i-1][s.j]){
            visit[s.i-1][s.j] = true
            q.add(Shark(s.i-1, s.j, s.level, s.move+1, s.eat))
        }
        if(s.i >= 0 && s.i < n && s.j-1 >= 0 && s.j-1 < n && map[s.i][s.j-1] <= s.level && !visit[s.i][s.j-1]){
            visit[s.i][s.j-1] = true
            q.add(Shark(s.i, s.j-1, s.level, s.move+1, s.eat))
        }
        if(s.i >= 0 && s.i < n && s.j+1 >= 0 && s.j+1 < n && map[s.i][s.j+1] <= s.level && !visit[s.i][s.j+1]){
            visit[s.i][s.j+1] = true
            q.add(Shark(s.i, s.j+1, s.level, s.move+1, s.eat))
        }
        if(s.i+1 >= 0 && s.i+1 < n && s.j >= 0 && s.j < n && map[s.i+1][s.j] <= s.level && !visit[s.i+1][s.j]){
            visit[s.i+1][s.j] = true
            q.add(Shark(s.i+1, s.j, s.level, s.move+1, s.eat))
        }

    }
    if(answer.isNotEmpty()){
        answer = answer.sortedWith( compareBy( {it.move}, {it.i}, {it.j} ) ).toMutableList()
        map[answer[0].i][answer[0].j] = 0
        return answer[0]
    }
    return shark
}


main()