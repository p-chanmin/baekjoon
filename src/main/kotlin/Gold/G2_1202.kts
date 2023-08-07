import java.io.*
import java.util.*
import kotlin.collections.ArrayList

data class Jewel(var w: Int, var v: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var (N, K) = br.readLine().split(" ").map{ it.toInt() }
    var jewels = PriorityQueue<Jewel>{ a, b -> a.w - b.w }
    var bags: ArrayList<Int> = arrayListOf()


    repeat(N){
        br.readLine().split(" ").let {
            jewels.add(Jewel(it[0].toInt(), it[1].toInt()))
        }
    }
    repeat(K){
        bags.add(br.readLine().toInt())
    }
    bags.sortBy { it }

    var result: Long = 0
    var jtemp = PriorityQueue<Jewel>{ a, b -> b.v - a.v }

    for(b in bags){
        while(jewels.isNotEmpty() && b >= jewels.peek().w){
            jtemp.add(jewels.poll())
        }
        if(jtemp.isNotEmpty()){
            result += jtemp.poll().v.toLong()
        }
        else if(jewels.isEmpty()) break
    }

    bw.write("${result}")

    bw.flush()
    bw.close()
}

main()