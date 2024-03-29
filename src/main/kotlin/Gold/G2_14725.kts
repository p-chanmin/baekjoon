import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Node(val name: String, val next: PriorityQueue<Node> = PriorityQueue { a, b -> a.name.compareTo(b.name) }) {
    fun addNode(name: String): Node {
        val find = next.filter { it.name == name }
        if (find.isNotEmpty()) {
            return find[0]
        }
        val node = Node(name)
        next.add(node)
        return node
    }

    fun print(bw: BufferedWriter, depth: Int = 0) {
        while (next.isNotEmpty()) {
            val next = next.poll()
            val str = "--".repeat(depth) + next.name + "\n"
            bw.write(str)
            next.print(bw, depth + 1)
        }
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val N = br.readLine().toInt()

    val root = Node("root")

    repeat(N) {
        val data = br.readLine().split(" ")
        val k = data[0].toInt()
        var currentNode = root
        (1..k).forEach { i ->
            currentNode = currentNode.addNode(data[i])
        }
    }

    root.print(bw)

    bw.flush()
    bw.close()
}


main()