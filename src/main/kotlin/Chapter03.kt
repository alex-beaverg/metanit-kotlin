fun main() {
    // Функции, методы
    // Без параметров
    hello()
    hello()
    println()

    // С параметрами
    helloWithName("Alexey")
    println()

    // С параметрами по умолчанию
    defaultParams("Alexey")
    defaultParams("Alexey", "Good Bye")
    defaultParams(greeting = "Hi", name = "Alexey")
    defaultParams(greeting = "Hi", name = "Alexey")
    defaultParams2(name = "Alexey")
    println()

    // Изменение исходного массива в методе
    val newArray = arrayOf(1, 2, 3)
    println("Элемент массива в main до изменения: ${newArray[0]}")
    changeArray(newArray)
    println("Элемент массива в main после изменения его в функции: ${newArray[0]}")
    println()

    // Переменное количество параметров
    printUserGroup("Group Name", "Alexey", "Dima", "Sasha", count = 3)
    val stringArray = arrayOf("One", "Two", "Three")
    printUserGroup("Name of Group", *stringArray, count = 3)
    println()

    // Возвращающие методы
    println(sum(5, 6))
    println()

    // Однострочные функции
    println(square(5))
    println()

    // Локальные функции
    compareAge(43, 54)
    println()

    // Перегрузка методов
    println(summa(2, 4))
    println(summa("ab", "cd"))
    println(summa(3, 5, 2))
    println()

    // Типы функций как переменных
    var someOperation: (Int, Int) -> Int = ::summa
    val result1 = someOperation(14, 5)
    println(result1) // 19
    someOperation = ::subtract
    val result2 = someOperation(14, 5)
    println(result2) // 9
    println()

    // Функция как параметр функции
    displayMessage(::morning)
    displayMessage(::evening)
    println()

    // Функция в возвращаемом типе
    var action = selectAction(1)
    println(action(8, 5)) // 13
    action = selectAction(2)
    println(action(8, 5)) // 3
    println()

    // Анонимные функции
    val sum = fun(x: Int, y: Int): Int = x + y
    println(sum(5, 4)) // 9
    println(doOperation(9,5, fun(x: Int, y: Int): Int = x + y)) // 14
    println(doOperation(9,5, fun(x: Int, y: Int): Int = x - y)) // 4
    action = fun(x: Int, y: Int): Int = x * y
    println(doOperation(9, 5, action)) // 45
    println(selectNewAction(1)(4, 5)) // 9
    println(selectNewAction(3)(4, 5)) // 20
    println(selectNewAction(9)(4, 5)) // 0
    println()

    // Лямбда-выражения
    val hello = {println("Hello Kotlin")}
    hello(); // Необходимая точка с запятой!!! - Чтобы следующая лямбда не пыталась казаться телом метода hello()
    {println("Hello Kotlin 2")}()
    val printer = {message: String -> println(message)}
    printer("Hello")
    printer("Good Bye"); // Снова необходимая точка с запятой!!!
    {message: String -> println(message)}("Welcome to Kotlin")
    val sumLambda = {x:Int, y:Int -> println(x + y)}
    sumLambda(2, 3); // 5
    {x:Int, y:Int -> println(x + y)}(5, 3) // 8
    println({x:Int, y:Int -> x + y}(4, 8)) // 12
    println(doOperation(3, 4) {a: Int, b: Int -> a * b}) // 12 -> Вынос лямбды за параметры - trailing lambda
    println(doOperation(5, 4) {a, b -> a * b}) // 20 -> Можем опустить типы если в методе они указаны
    println(selectLambdaAction(1)(4, 5)) // 9
    println(selectLambdaAction(3)(4, 5)) // 20
    println(selectLambdaAction(9)(4, 5)) // 0
    println()

    // Замыкания
    println(multiply(5)(6)) // 30
    println(multiply(5)(5)) // 25
    val func = multiply(10)
    println(func(10)) // 100
}

// Методы, вызываемые из main

fun hello() {
    println("Hello")
}

fun helloWithName(name: String) {
    println("Hello, $name")
}

fun defaultParams(name: String, greeting: String = "Hello") {
    println("$greeting, $name")
}

fun defaultParams2(greeting: String = "Hello", name: String) {
    println("$greeting, $name")
}

fun changeArray(array: Array<Int>) {
    array[0] *= 2
    println("Элемент массива в функции после изменения: ${array[0]}")
}

fun printUserGroup(group: String, vararg users: String, count: Int) {
    println("Group: $group")
    println("Count: $count")
    for(user in users)
        println(user)
}

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun square(a: Int) = a * a

fun compareAge(age1: Int, age2: Int) {
    fun ageIsValid(age: Int) = age > 0 && age < 111

    if(!ageIsValid(age1) || !ageIsValid(age2)) {
        println("Invalid age")
        return
    }

    when {
        age1 == age2 -> println("age1 == age2")
        age1 > age2 -> println("age1 > age2")
        else -> println("age1 < age2")
    }
}

fun summa(a: Int, b: Int) = a + b

fun summa(a: String, b: String) = a + b

fun summa(a: Int, b: Int, c: Int) = a + b + c

fun subtract(a: Int, b: Int): Int {
    return a - b
}

fun displayMessage(mes: () -> Unit) = mes()

fun morning() = println("Good Morning")

fun evening() = println("Good Evening")

fun selectAction(key: Int): (Int, Int) -> Int {
    return when(key) {
        1 -> ::sum
        2 -> ::subtract
        3 -> ::multiply
        else -> ::empty
    }
}
fun empty (a: Int, b: Int) = 0

fun multiply(a: Int, b: Int) = a * b

fun doOperation(x: Int, y: Int, op: (Int, Int) -> Int) = op(x, y)

fun selectNewAction(key: Int): (Int, Int) -> Int {
    return when(key) {
        1 -> fun(x: Int, y: Int): Int = x + y
        2 -> fun(x: Int, y: Int): Int = x - y
        3 -> fun(x: Int, y: Int): Int = x * y
        else -> fun(_, _) = 0 // Неиспользуемые параметры -> _
    }
}

fun selectLambdaAction(key: Int): (Int, Int) -> Int {
    return when(key) {
        1 -> {x, y -> x + y}
        2 -> {x, y -> x - y}
        3 -> {x, y -> x * y}
        else -> {_, _ -> 0} // Неиспользуемые параметры -> _
    }
}

fun multiply(n: Int): (Int) -> Int {
    return {m: Int -> n * m}
}