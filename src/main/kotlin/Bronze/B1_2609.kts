import java.io.*
import java.util.*

fun main() {

    var br = BufferedReader(InputStreamReader(System.`in`))

    var (a, b) = br.readLine().split(" ").map{ it.toInt() }

    var gcd = gcd(a, b)
    var lcm = (a*b)/gcd

    println(gcd)
    println(lcm)

}
fun gcd(n: Int, m: Int): Int {
    if (m == 0) return n
    else return gcd(m, n % m)
}

main()