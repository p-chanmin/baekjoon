import java.io.*
import java.util.*


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))


    var s = br.readLine()
    var boom = br.readLine()

    var stack: Stack<Char> = Stack()

    for(i in s.indices){

        stack.add(s[i])

        if(s[i] == boom.last() && boom.length <= stack.size){
            if(stack.slice(stack.size - boom.length until stack.size).joinToString("") == boom){
                repeat(boom.length){ stack.pop() }
            }
        }

    }

    if(stack.isEmpty()) bw.write("FRULA")
    else bw.write(stack.joinToString(""))


    bw.flush()
    bw.close()
}



main()