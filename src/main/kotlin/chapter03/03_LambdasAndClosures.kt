package chapter03

fun lambdasAndClosures() {
    val numberA = (Math.random() * 100).toInt()
    val numberB = (Math.random() * 10).toInt()

    // Лямбда-выражения:
    val lambdaGreetingsFunction = { println("Привет, это лямбда-функция в переменной") }
    lambdaGreetingsFunction(); // Точка с запятой!!! - Чтобы следующая лямбда не пыталась казаться телом этой функции
    { println("Привет, это непосредственно вызываемая лямбда-функция") }()
    val lambdaMessageFunction = { message: String -> println(message) }
    lambdaMessageFunction("Привет, это лямбда-функция с параметром, заданная как переменная"); // Точка с запятой!!!
    { message: String -> println(message) }("Привет, это непосредственно вызываемая лямбда-функция с параметром")
    val lambdaFunctionForAddition = { x: Int, y: Int -> x + y }
    println(
        "Вызов лямбда функции-переменной сложения чисел $numberA и $numberB: ${
            lambdaFunctionForAddition(
                numberA,
                numberB
            )
        }"
    )
    println(
        "Вызов непосредственно лямбда функции сложения чисел $numberA и $numberB: ${
            { x: Int, y: Int -> x + y }(
                numberA,
                numberB
            )
        }"
    )
    println(
        "Вызов непосредственно лямбда функции умножения чисел $numberA и $numberB с выносом лямбды за параметры: ${
            functionWithAnonymousFunctionAsArgument(
                numberA,
                numberB
            ) { a: Int, b: Int -> a * b }
        }"
    ) // Вынос лямбды за параметры - trailing lambda
    println(
        "Вызов непосредственно лямбда функции умножения чисел $numberA и $numberB с выносом лямбды за параметры без указания типов: ${
            functionWithAnonymousFunctionAsArgument(
                numberA,
                numberB
            ) { a, b -> a * b }
        }"
    ) // Можем опустить типы если в методе они указаны
    println(
        "Результат вызова функции, возвращающей анонимную лямбда-функцию (сложение чисел $numberA и $numberB): ${
            functionWithReturnAnonymousFunctionWithLambda(1)(numberA, numberB)
        }"
    )
    println(
        "Результат вызова функции, возвращающей анонимную лямбда-функцию (умножение чисел $numberA и $numberB): ${
            functionWithReturnAnonymousFunctionWithLambda(3)(numberA, numberB)
        }"
    )
    println(
        "Результат вызова функции, возвращающей анонимную лямбда-функцию (ноль): ${
            functionWithReturnAnonymousFunctionWithLambda(9)(numberA, numberB)
        }"
    )

    // Замыкания:
    println(
        "Результат использования замыкания функции на саму себя для умножения чисел $numberA и $numberB: ${
            functionWithReturnAnonymousFunctionWithLambdaForMultiplication(
                numberA
            )(numberB)
        }"
    )
    val functionForClosures = functionWithReturnAnonymousFunctionWithLambdaForMultiplication(numberA)
    println(
        "Результат использования замыкания функции-переменной на саму себя для умножения чисел $numberA и $numberB: ${
            functionForClosures(
                numberB
            )
        }"
    )
}

/** Функция, возвращающая анонимную функцию с лямбдами */
fun functionWithReturnAnonymousFunctionWithLambda(key: Int): (Int, Int) -> Int {
    return when (key) {
        1 -> { x, y -> x + y }
        2 -> { x, y -> x - y }
        3 -> { x, y -> x * y }
        else -> { _, _ -> 0 } // Неиспользуемые параметры -> _
    }
}

/** Функция, возвращающая анонимную функцию с лямбдами для умножения */
fun functionWithReturnAnonymousFunctionWithLambdaForMultiplication(n: Int): (Int) -> Int {
    return { m -> n * m }
}