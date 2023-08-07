import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var T = br.readLine().toInt()



    repeat(T){

        var n = br.readLine().toInt()

        var team: IntArray = IntArray(n+1){ 0 }
        var visit: BooleanArray = BooleanArray(n+1){ false }
        var end: BooleanArray = BooleanArray(n+1){ false }
        br.readLine().split(" ").forEachIndexed { i, s ->
            team[i+1] = s.toInt()
        }

        fun dfs(s: Int){
            visit[s] = true
            if(!visit[team[s]]) dfs(team[s])
            else{
                if(!end[team[s]]){
                    var i = team[s]
                    n--
                    while(s != i){
                        n--
                        i = team[i]
                    }
                }
            }
            end[s] = true
        }

        for(i in 1 until team.size){
            if(!visit[i]){
                dfs(i)
            }
        }
        bw.write("${n}\n")
    }

    bw.flush()
    bw.close()
}


main()