import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    val station = Array(N + 1) { mutableListOf<Int>() }
    val parent = IntArray(N + 1) { it }
    val rotate = BooleanArray(N + 1) { false }
    val visited = BooleanArray(N + 1) { false }

    var initStation = 0
    repeat(N) {
        val (s1, s2) = br.readLine().split(" ").map { it.toInt() }
        station[s1].add(s2)
        station[s2].add(s1)
        if (!union(parent, s1, s2)) {
            initStation = s1
            rotate[s1] = true
            visited[s1] = true
        }
    }

    findRotate(rotate, visited, station, initStation)

    val result = (1..N).map { start ->
        bfs(rotate, station, start)
    }

    bw.write(result.joinToString(" "))

    bw.flush()
    bw.close()
}

data class State(val st: Int, val d: Int)

fun bfs(rotate: BooleanArray, station: Array<MutableList<Int>>, start: Int): Int {
    val visited = BooleanArray(station.size) { false }
    visited[start] = true

    val queue: Deque<State> = LinkedList()
    queue.add(State(start, 0))

    while (queue.isNotEmpty()) {
        val q = queue.poll()

        if (rotate[q.st]) {
            return q.d
        } else {
            for (i in station[q.st]) {
                if (!visited[i]) {
                    visited[i] = true
                    queue.add(State(i, q.d + 1))
                }
            }
        }
    }
    return -1
}


fun findRotate(
    rotate: BooleanArray,
    visited: BooleanArray,
    station: Array<MutableList<Int>>,
    start: Int,
    d: Int = 0
): Boolean {
    for (i in station[start]) {
        if (!visited[i]) {
            visited[i] = true
            if (findRotate(rotate, visited, station, i, d + 1)) {
                rotate[start] = true
                return true
            }
        } else if (rotate[i] && d > 1) {
            rotate[start] = true
            return true
        }
    }
    return false
}

fun find(parent: IntArray, x: Int): Int {
    return if (parent[x] == x) {
        parent[x]
    } else {
        parent[x] = find(parent, parent[x])
        parent[x]
    }
}

fun union(parent: IntArray, x: Int, y: Int): Boolean {
    val px = find(parent, x)
    val py = find(parent, y)

    return if (px != py) {
        parent[px] = py
        true
    } else {
        false
    }
}

main()