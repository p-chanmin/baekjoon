
/*
자연수가 들어있는 배열 arr가 매개변수로 주어집니다.
배열 arr안의 숫자들 중에서 앞에 있는 숫자들부터 뒤에 중복되어 나타나는 숫자들 중복 횟수를 계산해서
배열로 return 하도록 solution 함수를 완성해주세요.
만약 중복되는 숫자가 없다면 배열에 -1을 채워서 return 하세요.

##### 제한사항
- 배열 arr의 길이는 1 이상 100 이하의 자연수입니다.
- 배열 arr의 원소는 1 이상 100 이하의 자연수입니다.
 */

import java.util.*

val tc1: IntArray = intArrayOf(1, 2, 3, 3, 3, 3, 4, 4)          // [4, 2]
val tc2: IntArray = intArrayOf(3, 2, 4, 4, 2, 5, 2, 5, 5)       // [3, 2, 3]
val tc3: IntArray = intArrayOf(3, 5, 7, 9, 1)                   // [-1]


fun solution(arr: IntArray): IntArray{
    var result: MutableList<Int> = mutableListOf()

    var element = arr.toSet()
    if(element.size == arr.size) return intArrayOf(-1)

    for (e in element){
        val n = arr.count { it == e }
        if (n != 1) result.add(n)
    }

    return result.toIntArray()
}

println("============")
println(Arrays.toString(solution(tc1)))
println("============")
println(Arrays.toString(solution(tc2)))
println("============")
println(Arrays.toString(solution(tc3)))
println("============")