import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))

    var N = br.readLine().toInt()

    var stack: Stack<Int> = Stack()

    repeat(N){
        var command = br.readLine()
        when(command.slice(0 .. 2)){
            "pus" -> { // push
                stack.push(command.split(" ")[1].toInt())
            }
            "pop" -> { // pop
                println(if(stack.isEmpty()) -1 else stack.pop())
            }
            "siz" -> { // size
                println(stack.size)
            }
            "emp" -> { // empty
                println(if(stack.isEmpty()) 1 else 0)
            }
            "top" -> { // top
                println(if(stack.isEmpty()) -1 else stack.peek())
            }
        }
    }
}


main()