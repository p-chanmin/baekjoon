import java.io.*
import java.util.*

data class Pos(var i: Int, var j: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    var N = br.readLine().toInt()

    var board: Array<Array<Char>> = Array(N){ Array(N){ '.' } }

    var k = br.readLine().toInt()
    repeat(k){
        br.readLine().split(" ").let{ board[it[0].toInt()-1][it[1].toInt()-1] = 'A' }
    }
    var l = br.readLine().toInt()
    var command: MutableMap<Int, String> = mutableMapOf()
    repeat(l){
        br.readLine().split(" ").let { command.put(it[0].toInt(), it[1]) }
    }

    var snake: Deque<Pos> = LinkedList()
    snake.add(Pos(0,0))
    board[0][0] = '#'
    var side: String = "R"
    var time: Int = 0

    while(true){
        time++
        var head = snake.peek()
        when(side){
            "U" -> {
                snake.addFirst(Pos(head.i-1, head.j))
            }
            "D" -> {
                snake.addFirst(Pos(head.i+1, head.j))
            }
            "L" -> {
                snake.addFirst(Pos(head.i, head.j-1))
            }
            "R" -> {
                snake.addFirst(Pos(head.i, head.j+1))
            }
        }
        head = snake.peek()
        if(head.i < 0 || head.i >= N || head.j < 0 || head.j >= N || board[head.i][head.j] == '#'){
            break
        }

        if(board[head.i][head.j] != 'A'){
            var tail = snake.pollLast()
            board[tail.i][tail.j] = '.'
        }
        board[head.i][head.j] = '#'

        if(command.getOrDefault(time, "X") != "X"){
            side = Turn(side, command[time]!!)
        }

    }
    println(time)

}
fun Turn(now: String, side: String): String{
    when(now){
        "U" -> {
            if(side == "L") return "L"
            else if(side == "D") return "R"
        }
        "D" -> {
            if(side == "L") return "R"
            else if(side == "D") return "L"
        }
        "L" -> {
            if(side == "L") return "D"
            else if(side == "D") return "U"
        }
        "R" -> {
            if(side == "L") return "U"
            else if(side == "D") return "D"
        }
    }
    return "R"
}


main()