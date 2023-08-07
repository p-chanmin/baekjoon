import java.io.*
import java.util.*

fun main(){
    var br = BufferedReader(InputStreamReader(System.`in`))

    var T = br.readLine().toInt()

    repeat(T){

        var k = br.readLine().toInt()

        var treeMap = TreeMap<Int, Int>(Collections.reverseOrder())

        repeat(k){

            var command = br.readLine().split(" ")
            when(command[0]){
                "I" -> {
                    treeMap[command[1].toInt()] = treeMap.getOrDefault(command[1].toInt(), 0) + 1
                }
                "D" -> {
                    if(command[1] == "1"){
                        if(treeMap.isNotEmpty() && treeMap.getOrDefault(treeMap.firstKey(), 0) == 1) treeMap.remove(treeMap.firstKey())
                        else if(treeMap.isNotEmpty() && treeMap.getOrDefault(treeMap.firstKey(), 0) > 1){
                            treeMap[treeMap.firstKey()] = treeMap.getOrDefault(treeMap.firstKey(), 0) - 1
                        }
                    }
                    else{
                        if(treeMap.isNotEmpty() && treeMap.getOrDefault(treeMap.lastKey(), 0) == 1) treeMap.remove(treeMap.lastKey())
                        else if(treeMap.isNotEmpty() && treeMap.getOrDefault(treeMap.lastKey(), 0) > 1){
                            treeMap[treeMap.lastKey()] = treeMap.getOrDefault(treeMap.lastKey(), 0) - 1
                        }
                    }
                }
            }
        }
        println(if(treeMap.isEmpty()) "EMPTY" else "${treeMap.firstKey()} ${treeMap.lastKey()}")
    }
}


main()