import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, S) = br.readLine().split(" ").map { it.toInt() }

    var arr = br.readLine().split(" ").map { it.toInt() }

    var l = 0
    var r = 0
    var s = arr[0]

    var result = Int.MAX_VALUE

    while(true){
        if(l == N) break
        if(s < S){
            if(r+1 in 0 until N){
                r++
                s += arr[r]
            }
            else{
                s -= arr[l]
                l++
            }
        }
        else{
            result = minOf(result, r - l + 1)
            s -= arr[l]
            l++
        }

    }

    bw.write("${if(result == Int.MAX_VALUE) 0 else result}")

    bw.flush()
    bw.close()
}


main()