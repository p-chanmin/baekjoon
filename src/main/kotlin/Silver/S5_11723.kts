import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))
    var bw = BufferedWriter(OutputStreamWriter(System.out))

    var M = br.readLine().toInt()

    var set: BooleanArray = BooleanArray(21){ false }

    repeat(M){

        var c = br.readLine().split(" ")
        when(c[0]){
            "add" -> {
                set[c[1].toInt()] = true
            }
            "remove" -> {
                set[c[1].toInt()] = false
            }
            "check" -> {
                bw.write(if(set[c[1].toInt()]) "${1}\n" else "${0}\n")
            }
            "toggle" -> {
                set[c[1].toInt()] = !set[c[1].toInt()]
            }
            "all" -> {
                set = BooleanArray(21){ true }
            }
            "empty" -> {
                set = BooleanArray(21){ false }
            }
        }
    }
    bw.flush()
    bw.close()
}


main()