import java.io.*
import java.util.*
import kotlin.collections.ArrayList

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, K) = br.readLine().split(" ").map{ it.toInt() }

    var coins: ArrayList<Int> = arrayListOf()

    repeat(N){
        coins.add(br.readLine().toInt())
    }

    var answer = 0
    for( i in N-1 downTo 0 ){
        if(K / coins[i] != 0){
            answer += K / coins[i]
            K = K % coins[i]
            if(K == 0) break
        }
    }

    bw.write("${answer}")

    bw.flush()
    bw.close()
}


main()