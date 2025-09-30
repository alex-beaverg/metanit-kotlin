package chapter03

fun anonymousFunctions() {
    val numberA = (Math.random() * 100).toInt()
    val numberB = (Math.random() * 10).toInt()

    // Анонимные функции:
    val anonymousFunctionForAddition = fun(x: Int, y: Int): Int = x + y
    println(
        "Результат вызова анонимной функции сложения чисел $numberA и $numberB: ${
            anonymousFunctionForAddition(
                numberA,
                numberB
            )
        }"
    )
    println(
        "Результат передачи анонимной функции как аргумент (сложение чисел $numberA и $numberB): ${
            functionWithAnonymousFunctionAsArgument(
                numberA,
                numberB,
                fun(x: Int, y: Int): Int = x + y)
        }"
    )
    println(
        "Результат передачи анонимной функции как аргумент (вычитание чисел $numberA и $numberB): ${
            functionWithAnonymousFunctionAsArgument(
                numberA,
                numberB,
                fun(x: Int, y: Int): Int = x - y)
        }"
    )
    val anonymousFunctionForMultiplication = fun(x: Int, y: Int): Int = x * y
    println(
        "Результат передачи анонимной функции как аргумент (умножение чисел $numberA и $numberB): ${
            functionWithAnonymousFunctionAsArgument(
                numberA,
                numberB,
                anonymousFunctionForMultiplication
            )
        }"
    )
    println(
        "Результат вызова функции, возвращающей анонимную функцию (сложение чисел $numberA и $numberB): ${
            functionWithReturnAnonymousFunction(1)(numberA, numberB)
        }"
    )
    println(
        "Результат вызова функции, возвращающей анонимную функцию (умножение чисел $numberA и $numberB): ${
            functionWithReturnAnonymousFunction(3)(numberA, numberB)
        }"
    )
    println(
        "Результат вызова функции, возвращающей анонимную функцию (ноль): ${
            functionWithReturnAnonymousFunction(9)(numberA, numberB)
        }"
    )
}

/** Функция с анонимной функцией в аргументах */
fun functionWithAnonymousFunctionAsArgument(x: Int, y: Int, op: (Int, Int) -> Int) = op(x, y)

/** Функция, возвращающая анонимную функцию */
fun functionWithReturnAnonymousFunction(key: Int): (Int, Int) -> Int {
    return when (key) {
        1 -> fun(x: Int, y: Int): Int = x + y
        2 -> fun(x: Int, y: Int): Int = x - y
        3 -> fun(x: Int, y: Int): Int = x * y
        else -> fun(_, _) = 0 // Неиспользуемые параметры -> _
    }
}