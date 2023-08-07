import java.io.*
import java.util.*
import kotlin.collections.ArrayList

data class Point(var i: Int, var j: Int)
data class Board(var b: Array<Array<Char>>, var d: Int, var before: String)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    var (N, M) = br.readLine().split(" ").map{ it.toInt() }

    var board: Array<Array<Char>> = Array(N){ Array(M){ '#' } }

    for(i in 0 until N){
        br.readLine().forEachIndexed { j, c -> board[i][j] = c }
    }

    var answer: Board = Board(board, 0, "N")

    var q: Deque<Board> = LinkedList()
    q.add(answer)

    while(q.isNotEmpty()){
        answer = q.poll()

        var r = answer.b.map{ it.joinToString("") }.joinToString("").indexOf('R')
        var b = answer.b.map{ it.joinToString("") }.joinToString("").indexOf('B')
        if(r == -1 && b != -1) break
        else if(b == -1) continue
        else if(answer.d == 11) break

        if(answer.before == "U" || answer.before == "D"){
            q.add(Board(Move(answer.b,"L"), answer.d+1, "L"))
            q.add(Board(Move(answer.b,"R"), answer.d+1, "R"))
        }
        else if(answer.before == "L" || answer.before == "R"){
            q.add(Board(Move(answer.b,"U"), answer.d+1, "U"))
            q.add(Board(Move(answer.b,"D"), answer.d+1, "D"))
        }
        else{
            q.add(Board(Move(answer.b,"U"), answer.d+1, "U"))
            q.add(Board(Move(answer.b,"D"), answer.d+1, "D"))
            q.add(Board(Move(answer.b,"L"), answer.d+1, "L"))
            q.add(Board(Move(answer.b,"R"), answer.d+1, "R"))
        }

    }

    println(if(answer.d == 11 || answer.d == 0) -1 else answer.d)

}
fun Move(b: Array<Array<Char>>, side: String): Array<Array<Char>>{

    var board = b.map { it.clone() }.toTypedArray()
    var ball1 = board.map{ it.joinToString("") }.joinToString("").indexOf('R').let{ Point(it/board[0].size, it % board[0].size)}
    var ball2 = board.map{ it.joinToString("") }.joinToString("").indexOf('B').let{ Point(it/board[0].size, it % board[0].size)}

    when(side){
        "U" -> {
            if(ball1.i > ball2.i) ball1 = ball2.also { ball2 = ball1 }
            while(board[ball1.i-1][ball1.j] == '.'){
                board[ball1.i][ball1.j] = board[ball1.i-1][ball1.j].also { board[ball1.i-1][ball1.j] = board[ball1.i][ball1.j] }
                ball1.i--
            }
            if(board[ball1.i-1][ball1.j] == 'O') board[ball1.i][ball1.j] = '.'
            while(board[ball2.i-1][ball2.j] == '.'){
                board[ball2.i][ball2.j] = board[ball2.i-1][ball2.j].also { board[ball2.i-1][ball2.j] = board[ball2.i][ball2.j] }
                ball2.i--
            }
            if(board[ball2.i-1][ball2.j] == 'O') board[ball2.i][ball2.j] = '.'
        }
        "D" -> {
            if(ball1.i < ball2.i) ball1 = ball2.also { ball2 = ball1 }
            while(board[ball1.i+1][ball1.j] == '.'){
                board[ball1.i][ball1.j] = board[ball1.i+1][ball1.j].also { board[ball1.i+1][ball1.j] = board[ball1.i][ball1.j] }
                ball1.i++
            }
            if(board[ball1.i+1][ball1.j] == 'O') board[ball1.i][ball1.j] = '.'
            while(board[ball2.i+1][ball2.j] == '.'){
                board[ball2.i][ball2.j] = board[ball2.i+1][ball2.j].also { board[ball2.i+1][ball2.j] = board[ball2.i][ball2.j] }
                ball2.i++
            }
            if(board[ball2.i+1][ball2.j] == 'O') board[ball2.i][ball2.j] = '.'
        }
        "L" -> {
            if(ball1.j > ball2.j) ball1 = ball2.also { ball2 = ball1 }
            while(board[ball1.i][ball1.j-1] == '.'){
                board[ball1.i][ball1.j] = board[ball1.i][ball1.j-1].also { board[ball1.i][ball1.j-1] = board[ball1.i][ball1.j] }
                ball1.j--
            }
            if(board[ball1.i][ball1.j-1] == 'O') board[ball1.i][ball1.j] = '.'
            while(board[ball2.i][ball2.j-1] == '.'){
                board[ball2.i][ball2.j] = board[ball2.i][ball2.j-1].also { board[ball2.i][ball2.j-1] = board[ball2.i][ball2.j] }
                ball2.j--
            }
            if(board[ball2.i][ball2.j-1] == 'O') board[ball2.i][ball2.j] = '.'
        }
        "R" -> {
            if(ball1.j < ball2.j) ball1 = ball2.also { ball2 = ball1 }
            while(board[ball1.i][ball1.j+1] == '.'){
                board[ball1.i][ball1.j] = board[ball1.i][ball1.j+1].also { board[ball1.i][ball1.j+1] = board[ball1.i][ball1.j] }
                ball1.j++
            }
            if(board[ball1.i][ball1.j+1] == 'O') board[ball1.i][ball1.j] = '.'
            while(board[ball2.i][ball2.j+1] == '.'){
                board[ball2.i][ball2.j] = board[ball2.i][ball2.j+1].also { board[ball2.i][ball2.j+1] = board[ball2.i][ball2.j] }
                ball2.j++
            }
            if(board[ball2.i][ball2.j+1] == 'O') board[ball2.i][ball2.j] = '.'
        }
    }
    return board
}

main()