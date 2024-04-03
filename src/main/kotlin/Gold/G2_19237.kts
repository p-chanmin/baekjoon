import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

data class Pos(val owner: Int, val count: Int, val sharks: List<Int>)

data class Shark(val i: Int, val j: Int, val dir: Int, val priority: Array<List<Int>>)

val di = listOf(0, -1, 1, 0, 0)
val dj = listOf(0, 0, 0, -1, 1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (N, M, k) = br.readLine().split(" ").map { it.toInt() }

    val world = Array(N) { Array(N) { Pos(0, 0, listOf()) } }
    val sharks: MutableMap<Int, Shark> = mutableMapOf()

    repeat(N) { i ->
        br.readLine().split(" ").map { it.toInt() }.forEachIndexed { j, v ->
            if (v != 0) {
                world[i][j] = Pos(v, k, listOf(v))
                sharks[v] = Shark(i, j, 0, arrayOf())
            }
        }
    }

    br.readLine().split(" ").map { it.toInt() }.forEachIndexed { i, v ->
        sharks[i + 1] = sharks.getValue(i + 1).copy(dir = v)
    }

    repeat(M) { i ->
        val arr = Array(4 + 1) { listOf<Int>() }
        repeat(4) { j ->
            arr[j + 1] = br.readLine().split(" ").map { it.toInt() }
        }
        sharks[i + 1] = sharks.getValue(i + 1).copy(priority = arr)
    }

    var answer = 0

    while (answer <= 1000) {
        answer++
        move(sharks, world, k)
        spread(sharks, world, k)
        if (sharks.keys.size == 1) {
            break
        }
    }
    if (answer > 1000) {
        answer = -1
    }
    bw.write("$answer")

    bw.flush()
    bw.close()
}

fun move(sharks: MutableMap<Int, Shark>, world: Array<Array<Pos>>, k: Int) {
    for (s in sharks.keys) {
        val shark = sharks.getValue(s)
        var nextDir = 0
        for (dir in shark.priority[shark.dir]) {
            val ni = shark.i + di[dir]
            val nj = shark.j + dj[dir]

            if (ni in world.indices && nj in world.indices) {
                if (world[ni][nj].owner == 0) {
                    nextDir = dir
                    break
                }
            }
        }
        if (nextDir == 0) {
            for (dir in shark.priority[shark.dir]) {
                val ni = shark.i + di[dir]
                val nj = shark.j + dj[dir]

                if (ni in world.indices && nj in world.indices) {
                    if (world[ni][nj].owner == s) {
                        nextDir = dir
                        break
                    }
                }
            }
        }
        val ni = shark.i + di[nextDir]
        val nj = shark.j + dj[nextDir]
        world[shark.i][shark.j] = world[shark.i][shark.j].copy(sharks = listOf())
        world[ni][nj] = world[ni][nj].copy(sharks = world[ni][nj].sharks + listOf(s))
        sharks[s] = shark.copy(i = ni, j = nj, dir = nextDir)
    }
}

fun spread(sharks: MutableMap<Int, Shark>, world: Array<Array<Pos>>, k: Int) {
    for (i in world.indices) {
        for (j in world.indices) {
            if (world[i][j].owner != 0 && world[i][j].sharks.isEmpty()) {
                val cnt = world[i][j].count
                if (cnt - 1 != 0) {
                    world[i][j] = world[i][j].copy(count = cnt - 1)
                } else {
                    world[i][j] = Pos(0, 0, listOf())
                }
            } else if (world[i][j].sharks.isNotEmpty()) {
                if (world[i][j].sharks.size > 1) {
                    val survive = world[i][j].sharks.min()
                    world[i][j].sharks.filter { it != survive }.forEach {
                        sharks.remove(it)
                    }
                    world[i][j] = Pos(survive, k, listOf(survive))
                } else {
                    val shark = world[i][j].sharks.first()
                    world[i][j] = world[i][j].copy(owner = shark, count = k)
                }
            }
        }
    }
}


main()