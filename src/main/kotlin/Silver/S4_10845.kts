import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))

    var N = br.readLine().toInt()

    var queue: Deque<Int> = LinkedList()

    repeat(N){
        var command = br.readLine()

        when(command[1]){
            'u' -> { // push
                queue.add(command.split(" ")[1].toInt())
            }
            'o' -> { // pop
                println(if(queue.isEmpty()) -1 else queue.poll())
            }
            'i' -> { // size
                println(queue.size)
            }
            'm' -> { // empty
                println(if(queue.isEmpty()) 1 else 0)
            }
            'r' -> { // front
                println(if(queue.isEmpty()) -1 else queue.peekFirst())
            }
            'a' -> { // back
                println(if(queue.isEmpty()) -1 else queue.peekLast())
            }
        }
    }
}


main()