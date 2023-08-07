import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))
    var N = br.readLine().toInt()

    repeat(N){

        var ps = br.readLine()
        var stack: Stack<Char> = Stack()

        for(p in ps){
            if(p == '(') stack.push(p)
            else{
                if(stack.isNotEmpty() && stack.peek() == '(') stack.pop()
                else {
                    stack.push(p)
                    break
                }
            }
        }
        println(if(stack.isEmpty()) "YES" else "NO")
    }
}


main()