import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

data class Fish(val x: Int, val y: Int, val head: Int)
data class Pos(val x: Int, val y: Int, val fishName: Int = 0)

val movement = listOf(
    Pos(0, 0),
    Pos(-1, 0),
    Pos(-1, -1),
    Pos(0, -1),
    Pos(1, -1),
    Pos(1, 0),
    Pos(1, 1),
    Pos(0, 1),
    Pos(-1, 1),
)

var total = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val fishes = Array(16 + 1) { Fish(-1, -1, -1) }
    var shark = Fish(-1, -1, -1)

    repeat(4) { x ->
        br.readLine().split(" ").chunked(2).forEachIndexed { y, fish ->
            val name = fish[0].toInt()
            val head = fish[1].toInt()
            if (x == 0 && y == 0) {
                shark = Fish(x, y, head)
                total = name
            } else {
                fishes[name] = Fish(x, y, head)
            }
        }
    }

    dfs(fishes, shark, total)

    bw.write("$total")

    bw.flush()
    bw.close()
}

fun dfs(fishes: Array<Fish>, shark: Fish, count: Int) {

    val afterMovementFishes = moveFish(fishes, shark)
    val sharkMovement = getSharkMovement(afterMovementFishes, shark)

    if (sharkMovement.isEmpty()) {
        total = max(total, count)
        return
    } else {
        for (pos in sharkMovement) {
            val shark = Fish(pos.x, pos.y, afterMovementFishes[pos.fishName].head)
            val fishes = afterMovementFishes.copyOf()
            fishes[pos.fishName] = Fish(-1, -1, -1)
            dfs(fishes, shark, count + pos.fishName)
        }
    }
}

fun getSharkMovement(fishes: Array<Fish>, shark: Fish): List<Pos> {
    val result = mutableListOf<Pos>()
    val world = Array(4) { IntArray(4) { 0 } }
    fishes.forEachIndexed { i, fish ->
        if (fish.x in 0 until 4 && fish.y in 0 until 4) {
            world[fish.x][fish.y] = i
        }
    }
    var x = shark.x
    var y = shark.y
    while (true) {
        x += movement[shark.head].x
        y += movement[shark.head].y

        if (x in 0 until 4 && y in 0 until 4) {
            if (world[x][y] != 0) {
                result.add(Pos(x, y, world[x][y]))
            }
        } else {
            break
        }
    }
    return result
}

fun moveFish(fishes: Array<Fish>, shark: Fish): Array<Fish> {
    val world = Array(4) { IntArray(4) { 0 } }
    fishes.forEachIndexed { i, fish ->
        if (fish.x in 0 until 4 && fish.y in 0 until 4) {
            world[fish.x][fish.y] = i
        }
    }

    val next = fishes.copyOf()

    for (i in 1..16) {
        val x = next[i].x
        val y = next[i].y
        val head = next[i].head

        if (head !in 1..8) {
            continue
        }

        for (d in 0..7) {
            val h = if (head + d <= 8) head + d else head + d - 8
            val nx = x + movement[h].x
            val ny = y + movement[h].y
            if (nx in 0 until 4 && ny in 0 until 4 && !(nx == shark.x && ny == shark.y)) {
                val to = world[nx][ny]

                if (to != 0) {
                    next[i] = Fish(nx, ny, h)
                    next[to] = next[to].copy(x = x, y = y)
                } else {
                    next[i] = Fish(nx, ny, h)
                }
                world[x][y] = world[nx][ny].also { world[nx][ny] = world[x][y] }

                break
            }
        }
    }
    return next
}


main()