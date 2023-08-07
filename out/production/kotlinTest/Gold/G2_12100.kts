import java.io.*
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    var N = br.readLine().toInt()

    var board: Array<IntArray> = Array(N){ IntArray(N){ 0 } }

    for(i in 0 until N){
        br.readLine().split(" ").forEachIndexed{ j, v ->
            board[i][j] = v.toInt()
        }
    }
    var answer: ArrayList<Int> = arrayListOf()

    fun dfs(b: Array<IntArray>, d: Int = 0){
        if(d == 5){
            answer.add(b.flatMap { it.toList() }.maxOf { it })
            return
        }
        dfs(Move(b, 'U'), d+1)
        dfs(Move(b, 'D'), d+1)
        dfs(Move(b, 'L'), d+1)
        dfs(Move(b, 'R'), d+1)
    }
    dfs(board)

    println(answer.maxOf { it })
}
fun Move(board: Array<IntArray>, side: Char): Array<IntArray>{
    var tmp: Array<IntArray> = Array(board.size){ IntArray(board[0].size){ 0 } }
    var q: Int
    when(side){
        'U' -> {
            board.mapIndexed{ i, _ -> board.mapNotNull{ if(it[i] != 0) it[i] else null } }.map{
                var merge: ArrayList<Int> = arrayListOf()
                q = 0
                for(v in it){
                    if(q == 0) q = v
                    else if(q == v){
                        merge.add(v*2)
                        q = 0
                    }
                    else {
                        merge.add(q)
                        q = v
                    }
                }
                if(q != 0) merge.add(q)
                merge.toList()
            }.forEachIndexed { j, it ->
                it.forEachIndexed { i, v -> tmp[i][j] = v }
            }
        }
        'D' -> {
            board.mapIndexed{ i, _ -> board.mapNotNull{ if(it[i] != 0) it[i] else null } }.map{
                var merge: ArrayList<Int> = arrayListOf()
                q = 0
                for(v in it.reversed()){
                    if(q == 0) q = v
                    else if(q == v){
                        merge.add(v*2)
                        q = 0
                    }
                    else {
                        merge.add(q)
                        q = v
                    }
                }
                if(q != 0) merge.add(q)
                merge.toList()
            }.forEachIndexed { j, it ->
                it.forEachIndexed { i, v -> tmp[board.size-i-1][j] = v }
            }

        }
        'L' -> {
            board.map{ it.toList().mapNotNull{ if (it != 0) it else null } }.map{
                var merge: ArrayList<Int> = arrayListOf()
                q = 0
                for(v in it){
                    if(q == 0) q = v
                    else if(q == v){
                        merge.add(v*2)
                        q = 0
                    }
                    else {
                        merge.add(q)
                        q = v
                    }
                }
                if(q != 0) merge.add(q)
                merge.toList()
            }.forEachIndexed { i, it ->
                it.forEachIndexed { j, v -> tmp[i][j] = v }
            }
        }
        'R' -> {
            board.map{ it.toList().mapNotNull{ if (it != 0) it else null } }.map{
                var merge: ArrayList<Int> = arrayListOf()
                q = 0
                for(v in it.reversed()){
                    if(q == 0) q = v
                    else if(q == v){
                        merge.add(v*2)
                        q = 0
                    }
                    else {
                        merge.add(q)
                        q = v
                    }
                }
                if(q != 0) merge.add(q)
                merge.toList()
            }.forEachIndexed { i, it ->
                it.forEachIndexed { j, v -> tmp[i][board.size-j-1] = v }
            }
        }
    }
    return tmp
}

main()