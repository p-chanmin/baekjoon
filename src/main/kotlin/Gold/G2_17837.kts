import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

data class Pos(val i: Int, val j: Int, val direction: Int)

val di = listOf(0, 0, 0, -1, 1)
val dj = listOf(0, 1, -1, 0, 0)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val (N, K) = br.readLine().split(" ").map { it.toInt() }

    val world = Array(N) { IntArray(N) { 0 } }

    repeat(N) { i ->
        br.readLine().split(" ").map { it.toInt() }.forEachIndexed { j, v ->
            world[i][j] = v
        }
    }

    val pieceInfo = Array(K) { Pos(-1, -1, -1) }
    val pieceMap = Array(N) { Array<List<Int>>(N) { listOf() } }

    repeat(K) { p ->
        br.readLine().split(" ").map { it.toInt() }.let {
            val i = it[0] - 1
            val j = it[1] - 1
            val direction = it[2]
            pieceInfo[p] = Pos(i, j, direction)
            pieceMap[i][j] = listOf(p)
        }
    }

    var count = 0

    while (count <= 1000) {
        count++

        if (playTurn(world, pieceInfo, pieceMap)) {
            break
        }
    }

    if (count > 1000) {
        bw.write("-1")
    } else {
        bw.write("$count")
    }

    bw.flush()
    bw.close()
}

fun playTurn(world: Array<IntArray>, pieceInfo: Array<Pos>, pieceMap: Array<Array<List<Int>>>): Boolean {

    for (p in pieceInfo.indices) {
        val ni = di[pieceInfo[p].direction] + pieceInfo[p].i
        val nj = dj[pieceInfo[p].direction] + pieceInfo[p].j

        if (ni in world.indices && nj in world.indices) {
            when (world[ni][nj]) {
                0 -> {
                    if (white(pieceInfo, pieceMap, p, ni, nj)) {
                        return true
                    }
                }

                1 -> {
                    if (red(pieceInfo, pieceMap, p, ni, nj)) {
                        return true
                    }
                }

                2 -> {
                    if (blue(world, pieceInfo, pieceMap, p)) {
                        return true
                    }
                }
            }
        } else {
            if (blue(world, pieceInfo, pieceMap, p)) {
                return true
            }
        }
    }
    return false
}

fun white(pieceInfo: Array<Pos>, pieceMap: Array<Array<List<Int>>>, p: Int, ni: Int, nj: Int): Boolean {

    val i = pieceInfo[p].i
    val j = pieceInfo[p].j

    val pIndex = pieceMap[i][j].indexOf(p)
    val movePiece = pieceMap[i][j].subList(pIndex, pieceMap[i][j].size)
    val leftPiece = pieceMap[i][j].subList(0, pIndex)

    for (s in movePiece) {
        pieceInfo[s] = pieceInfo[s].copy(i = ni, j = nj)
    }
    pieceMap[ni][nj] += movePiece.also { pieceMap[i][j] = leftPiece }

    return pieceMap[ni][nj].size >= 4
}

fun red(pieceInfo: Array<Pos>, pieceMap: Array<Array<List<Int>>>, p: Int, ni: Int, nj: Int): Boolean {
    val i = pieceInfo[p].i
    val j = pieceInfo[p].j

    val pIndex = pieceMap[i][j].indexOf(p)
    val movePiece = pieceMap[i][j].subList(pIndex, pieceMap[i][j].size)
    val leftPiece = pieceMap[i][j].subList(0, pIndex)

    for (s in movePiece) {
        pieceInfo[s] = pieceInfo[s].copy(i = ni, j = nj)
    }
    pieceMap[ni][nj] += movePiece.reversed().also { pieceMap[i][j] = leftPiece }

    return pieceMap[ni][nj].size >= 4
}

fun blue(world: Array<IntArray>, pieceInfo: Array<Pos>, pieceMap: Array<Array<List<Int>>>, p: Int): Boolean {
    val newDir = when (pieceInfo[p].direction) {
        1 -> 2
        2 -> 1
        3 -> 4
        else -> 3
    }
    pieceInfo[p] = pieceInfo[p].copy(direction = newDir)

    val ni = di[pieceInfo[p].direction] + pieceInfo[p].i
    val nj = dj[pieceInfo[p].direction] + pieceInfo[p].j

    if (ni in world.indices && nj in world.indices) {
        when (world[ni][nj]) {
            0 -> {
                if (white(pieceInfo, pieceMap, p, ni, nj)) {
                    return true
                }
            }

            1 -> {
                if (red(pieceInfo, pieceMap, p, ni, nj)) {
                    return true
                }
            }
        }
    }
    return false
}

main()