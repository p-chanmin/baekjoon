import java.io.*
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val regex = Regex("[A-Z]")
    val matchResult: Sequence<MatchResult> = regex.findAll("aBCd")
    println(matchResult.count())    // 2
    matchResult.iterator().forEach { println(it.value) }   // B, C
    matchResult.iterator().forEach { println(it.range) }   // 1..1, 2..2

    var a = Triple(1, 2, 3)
    println(a)
    a.third = 3
    println(a)

    bw.flush()
    bw.close()
}

main()