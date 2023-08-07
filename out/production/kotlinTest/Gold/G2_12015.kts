import java.io.*
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    br.readLine()

    fun Binarysearch(l: ArrayList<Int>, target: Int): Int{
        var start = 0
        var end = l.size - 1
        while(start <= end){
            var mid = (start + end) / 2
            if(l[mid] < target) start = mid + 1
            else end = mid - 1
        }
        return start
    }

    var arr: ArrayList<Int> = arrayListOf()

    br.readLine().split(" ").map { it.toInt() }.forEach {
        if(arr.isEmpty() || arr.last() < it) arr.add(it)
        else{
            arr[Binarysearch(arr, it)] = it
        }
    }

    bw.write("${arr.size}")

    bw.flush()
    bw.close()
}


main()