import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val (n, m) = br.readLine().split(" ").map{ it.toInt() }
    val (r, c, d) = br.readLine().split(" ").map{ it.toInt() }

    var room = Array<IntArray>(n){ IntArray(m){ 0 } }

    for(i in 0 until n){
        br.readLine().split(" ").forEachIndexed{ j, v ->
            room[i][j] = v.toInt()
        }
    }
    println(clean(room, r, c, d, 0))
}

fun clean(m: Array<IntArray>, r: Int, c: Int, d: Int, cl: Int): Int{
    if(m[r][c] == 0){
        m[r][c] = 2
        return clean(m, r, c, d, cl+1)
    }
    else if((m[r-1][c] == 0)||(m[r+1][c] == 0)||(m[r][c-1] == 0)||(m[r][c+1] == 0)){
        var d = d
        var front = 1
        do{
            d = turn(d)
            if(d == 0) front = m[r-1][c]
            else if(d == 1) front = m[r][c+1]
            else if(d == 2) front = m[r+1][c]
            else front = m[r][c-1]
        }while(front != 0)

        if(d == 0) return clean(m, r-1, c, d, cl)
        else if(d == 1) return clean(m, r, c+1, d, cl)
        else if(d == 2) return clean(m, r+1, c, d, cl)
        else return clean(m, r, c-1, d, cl)

    }
    else{
        if(d == 0 && m[r+1][c] != 1) return clean(m, r+1, c, d, cl)
        else if(d == 1 && m[r][c-1] != 1) return clean(m, r, c-1, d, cl)
        else if(d == 2 && m[r-1][c] != 1) return clean(m, r-1, c, d, cl)
        else if(d == 3 && m[r][c+1] != 1) return clean(m, r, c+1, d, cl)
        else return cl
    }


}

fun turn(d: Int): Int{
    return if(d == 0) 3 else d-1
}


main()