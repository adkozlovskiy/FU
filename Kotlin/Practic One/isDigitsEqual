package project

fun main() {
    print("Enter l: ")
    val l = readLine()!!.toInt()

    print("Enter r: ")
    val r = readLine()!!.toInt()
    var s = 0

    for (i: Int in l..r) {
        if (i < 10 || isDigitEquals(i)) {
            s++
        }
    }
    println(s)
}

fun isDigitEquals(i: Int): Boolean {
    var number = i
    val digit = number % 10

    while (number > 0) {
        if (number % 10 != digit) return false
        number /= 10
    }

    return true
}
