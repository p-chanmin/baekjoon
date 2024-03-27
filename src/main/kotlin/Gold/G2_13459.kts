import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Ball(
    val ri: Int,
    val rj: Int,
    val bi: Int,
    val bj: Int,
    val isRedGoal: Boolean,
    val isBlueGoal: Boolean,
    val d: Int = 0
)

enum class Side(val di: Int, val dj: Int) {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1)
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (N, M) = br.readLine().split(" ").map { it.toInt() }

    val board = Array(N) { CharArray(M) { '#' } }

    var ri = 0
    var rj = 0
    var bi = 0
    var bj = 0

    repeat(N) { i ->
        br.readLine().forEachIndexed { j, c ->
            when (c) {
                'R' -> {
                    ri = i
                    rj = j
                    board[i][j] = '.'
                }

                'B' -> {
                    bi = i
                    bj = j
                    board[i][j] = '.'
                }

                else -> {
                    board[i][j] = c
                }
            }
        }
    }
    // ri, rj, bi, bj
    val visited = Array(N) { Array(M) { Array(N) { BooleanArray(M) { false } } } }

    visited[ri][rj][bi][bj] = true

    val startBall = Ball(ri, rj, bi, bj, false, false, 0)

    var answer = 0

    val queue: Deque<Ball> = LinkedList()

    queue.add(startBall)

    while (queue.isNotEmpty()) {
        val q = queue.poll()

        if (q.d > 10) {
            continue
        }
        if (q.isBlueGoal) {
            continue
        } else if (q.isRedGoal) {
            answer = 1
            break
        }

        for (side in Side.values()) {
            val result = playGame(q, board, side)

            if (!visited[result.ri][result.rj][result.bi][result.bj]) {
                visited[result.ri][result.rj][result.bi][result.bj] = true
                queue.add(result.copy(d = q.d + 1))
            }
        }
    }

    bw.write("$answer")

    bw.flush()
    bw.close()
}

fun playGame(ball: Ball, board: Array<CharArray>, side: Side): Ball {
    var ri = ball.ri
    var rj = ball.rj
    var bi = ball.bi
    var bj = ball.bj
    var isRedGoal = false
    var isBlueGoal = false

    while (true) {
        if (board[ri + side.di][rj + side.dj] == '#') {
            break
        } else if (board[ri + side.di][rj + side.dj] == 'O') {
            ri += side.di
            rj += side.dj
            isRedGoal = true
            break
        } else {
            ri += side.di
            rj += side.dj
        }
    }
    while (true) {
        if (board[bi + side.di][bj + side.dj] == '#') {
            break
        } else if (board[bi + side.di][bj + side.dj] == 'O') {
            bi += side.di
            bj += side.dj
            isBlueGoal = true
            break
        } else {
            bi += side.di
            bj += side.dj
        }
    }
    if (isBlueGoal) {
        return Ball(ri, rj, bi, bj, isRedGoal, isBlueGoal)
    } else if (ri == bi && rj == bj) {
        when (side) {
            Side.LEFT -> {
                if (ball.rj < ball.bj) {
                    bj += 1
                } else {
                    rj += 1
                }
            }

            Side.RIGHT -> {
                if (ball.rj < ball.bj) {
                    rj -= 1
                } else {
                    bj -= 1
                }
            }

            Side.UP -> {
                if (ball.ri < ball.bi) {
                    bi += 1
                } else {
                    ri += 1
                }
            }

            Side.DOWN -> {
                if (ball.ri < ball.bi) {
                    ri -= 1
                } else {
                    bi -= 1
                }
            }
        }
    }
    return Ball(ri, rj, bi, bj, isRedGoal, isBlueGoal)
}


main()