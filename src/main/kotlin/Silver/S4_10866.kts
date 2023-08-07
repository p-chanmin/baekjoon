import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))

    var N = br.readLine().toInt()

    var queue: Deque<Int> = LinkedList()

    repeat(N){
        var command = br.readLine().split(" ")
        when(command[0]){
            "push_front" -> {
                queue.addFirst(command[1].toInt())
            }
            "push_back" -> {
                queue.addLast(command[1].toInt())
            }
            "pop_front" -> {
                println(if(queue.isEmpty()) -1 else queue.pollFirst())
            }
            "pop_back" -> {
                println(if(queue.isEmpty()) -1 else queue.pollLast())
            }
            "size" -> {
                println(queue.size)
            }
            "empty" -> {
                println(if(queue.isEmpty()) 1 else 0)
            }
            "front" -> {
                println(if(queue.isEmpty()) -1 else queue.peekFirst())
            }
            "back" -> {
                println(if(queue.isEmpty()) -1 else queue.peekLast())
            }
        }
    }
}


main()