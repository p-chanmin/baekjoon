import java.io.*
import java.util.*

fun main(){
    var br = BufferedReader(InputStreamReader(System.`in`))

    var (n ,m, start_i, start_j) = br.readLine().split(" ").map{ it.toInt() }

    var board: Array<IntArray> = Array(n){ IntArray(m){ 0 } }

    (0 until n).forEach{ i->
        br.readLine().split(" ").forEachIndexed{ j, v ->
            board[i][j] = v.toInt()
        }
    }

    var command = br.readLine().split(" ").map { it.toInt() }

    var dice: Array<IntArray> = Array(4){ IntArray(3){ 0 } }

    fun changeAndPrint(){
        if(board[start_i][start_j] == 0){
            board[start_i][start_j] = dice[1][1]
        }
        else{
            dice[1][1] = board[start_i][start_j]
            board[start_i][start_j] = 0
        }
        println(dice[3][1])
    }

    fun diceRolling(d: Int){
        var tmp: Int
        // 동 : 1, 서 : 2, 북 : 3, 남 : 4
        when(d){
            1 -> {
                if(start_j+1 < m) {
                    tmp = dice[3][1]
                    dice[3][1] = dice[1][0]
                    dice[1][0] = dice[1][1]
                    dice[1][1] = dice[1][2]
                    dice[1][2] = tmp
                    start_j++
                    changeAndPrint()
                }
            }
            2 -> {
                if(start_j-1 >= 0){
                    tmp = dice[3][1]
                    dice[3][1] = dice[1][2]
                    dice[1][2] = dice[1][1]
                    dice[1][1] = dice[1][0]
                    dice[1][0] = tmp
                    start_j--
                    changeAndPrint()
                }
            }
            3 -> {
                if(start_i-1 >= 0){
                    tmp = dice[3][1]
                    dice[3][1] = dice[2][1]
                    dice[2][1] = dice[1][1]
                    dice[1][1] = dice[0][1]
                    dice[0][1] = tmp
                    start_i--
                    changeAndPrint()
                }
            }
            4 -> {
                if(start_i+1 < n){
                    tmp = dice[0][1]
                    dice[0][1] = dice[1][1]
                    dice[1][1] = dice[2][1]
                    dice[2][1] = dice[3][1]
                    dice[3][1] = tmp
                    start_i++
                    changeAndPrint()
                }
            }
        }
    }
    command.forEach {
        diceRolling(it)
    }
}


main()