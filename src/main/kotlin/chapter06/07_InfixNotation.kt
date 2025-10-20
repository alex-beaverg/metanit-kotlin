package chapter06

fun infixNotation() {
    // Инфиксная нотация:
    with(ClassWithInfix(2)) {
        printResult()
        multiply(4)
        printResult()
        add(100)
        printResult()
    }
}

/** Класс с инфиксной функцией */
class ClassWithInfix(var result: Int) {
    infix fun multiply(value: Int) {
        result = result * value
    }

    fun printResult() = println(result)
}

/** Инфиксная функция вне класса */
infix fun ClassWithInfix.add(value: Int) {
    this.result = this.result + value
}