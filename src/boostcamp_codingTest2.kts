
/*
여러분들은 새로운 컴퓨터를 만들고 8바이트 단위로 관리하는 타입별 메모리 관리 방식을 시뮬레이션하려고 합니다.
지원하는 타입별 크기는 다음과 같습니다.

BOOL 1바이트
SHORT 2바이트
FLOAT 4바이트
INT 8바이트
LONG 16바이트

매개변수 param0에 타입들을 입력한 순서대로 메모리를 할당한 결과를 8바이트 단위로 구분해서 return해주는 solution 함수를 작성하세요.
단, BOOL을 제외한 8바이트보다 작은 타입들이 연속될 경우에는 사이에 패딩(.)을 붙여야 합니다. SHORT는 2배수, FLOAT는 4배수가 되도록 메모리를 할당해야 합니다.​

예를 들어 BOOL 타입 이후에 BOOL 타입은 바로 붙어서 할당할 수 있습니다.
 "BOOL", "BOOL" → "##......"

그렇지만 BOOL 타입 이후에 SHORT, FLOAT를 할당하기 위해서는 각각 1개, 3개 패딩을 붙여야 합니다.
"BOOL", "SHORT" → "#.##...."
"BOOL", "FLOAT" → "#...####"
"BOOL", "SHORT", "FLOAT" → "#.######"

BOOL 타입 이후에 크기가 8바이트 이상인 타입은 7개 패딩을 붙여야 합니다.
"BOOL", "INT" → "#.......,########"

BOOL과 마찬가지로 8바이트보다 작은 SHORT, FLOAT도 뒤에 패딩을 붙여야 합니다.
"SHORT", "BOOL" → "###....."
"FLOAT", "SHORT" → "######.."

제한 사항
입력 타입은 1개 이상, 100개 이하까지만 가능합니다.
출력하는 메모리 최대 크기는 128바이트를 기준으로 동작합니다. 따라서 128바이트보다 큰 메모리 할당은 불가능합니다.
만약 타입 하나라도 더 이상 할당할 수 없는 경우는 "HALT"를 리턴합니다.
 */

import java.util.*

val tc1: Array<String> = arrayOf("INT", "INT", "BOOL", "SHORT", "LONG")
val tc2: Array<String> = arrayOf("INT", "SHORT", "FLOAT", "INT","BOOL")
val tc3: Array<String> = arrayOf("FLOAT", "SHORT", "BOOL", "BOOL", "BOOL", "INT")
val tc4: Array<String> = arrayOf("BOOL", "LONG", "SHORT", "LONG", "BOOL", "LONG", "BOOL", "LONG", "SHORT", "LONG", "LONG")

val answer1: String = "########,########,#.##....,########,########"
val answer2: String = "########,##..####,########,#......."
val answer3: String = "########,#.......,########"
val answer4: String = "HALT"

val testCase: List<Array<String>> = listOf(tc1, tc2, tc3, tc4)
val answerCase: List<String> = listOf(answer1, answer2, answer3, answer4)

fun solution(param: Array<String>): String{

    val map = mapOf<String, Int>(
        "BOOL" to 1,
        "SHORT" to 2,
        "FLOAT" to 4,
        "INT" to 8,
        "LONG" to 16
    )

    var data = param.flatMapIndexed { i, s ->
        var memory = "#".repeat(map[s]!!)
        if(i != param.size-1 && param[i] == "BOOL" && param[i+1] == "SHORT") memory += "."
        else if(i != param.size-1 && param[i] == "BOOL" && param[i+1] == "FLOAT") memory += "..."
        else if(i != param.size-1 && param[i] == "SHORT" && param[i+1] == "FLOAT") memory += ".."

        if(memory.length == 16) listOf("#".repeat(8), "#".repeat(8))
        else listOf(memory)
    }

    var result = mutableListOf<String>()
    var memory = ""
    data.forEach {
        if(it.length > (8 - memory.length)) {
            memory += ".".repeat(8 - memory.length)
            result.add(memory)
            memory = "" + it
        }
        else{
            memory += it
        }
    }
    if(memory.length != 0){
        memory += ".".repeat(8 - memory.length)
        result.add(memory)
    }

    return if(result.size * 8 > 128) "HALT" else result.joinToString(",")
}


for (i in (0 until testCase.size)){
    println("===============")
    val result = solution(testCase[i])
    println(answerCase[i] == result)
    println("입력값 : ${Arrays.toString(testCase[i])}")
    println("기대값 : ${answerCase[i]}")
    println("결과값 : ${result}")
}