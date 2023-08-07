import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, S) = br.readLine().split(" ").map { it.toInt() }

    var arr = br.readLine().split(" ").map { it.toInt() }
    var answer: Long = 0

    var mapA: MutableMap<Int, Int> = mutableMapOf(0 to 1)
    var mapB: MutableMap<Int, Int> = mutableMapOf(0 to 1)

    fun subsetA(s: Int, k: Int){
        if(k >= N/2) return

        if(s + arr[k] in mapA.keys) mapA[s + arr[k]] = mapA[s + arr[k]]!! + 1
        else mapA.put(s + arr[k], 1)

        subsetA(s, k+1)
        subsetA(s + arr[k], k+1)
    }
    fun subsetB(s: Int, k: Int){
        if(k >= N) return

        if(s + arr[k] in mapB.keys) mapB[s + arr[k]] = mapB[s + arr[k]]!! + 1
        else mapB.put(s + arr[k], 1)

        subsetB(s, k+1)
        subsetB(s + arr[k], k+1)
    }

    subsetA(0, 0)
    subsetB(0, N/2)

    for(a in mapA.keys){
        if(S - a in mapB.keys){
            answer += mapA[a]!!.toLong() * mapB[S-a]!!.toLong()
        }
    }
    if(S == 0) answer--

    bw.write("${answer}")

    bw.flush()
    bw.close()
}


main()