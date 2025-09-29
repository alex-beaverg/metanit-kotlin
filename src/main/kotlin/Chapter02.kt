// Константа на уровне компилляции
const val number3 = 340

fun main() {
    println("Hello World!")
    println()

    // Неизменяемая переменная
    val number = 115
    println(number)
    println()

    // Изменяемая переменная
    var number2 = 10
    println(number2)
    number2 = 15
    println(number2)

    println(number3)
    println()

    // Типы переменных
    val byte: Byte = 127
    println(byte)
    val long1: Long = -345
    println(long1)
    val long2 = 421L
    println(long2)
    val short: Short = -1000
    println(short)
    // Положительные
    val uByte: UByte = 200U
    println(uByte)
    val uLong: ULong = 3_000U
    println(uLong)
    val uInt = 100U
    println(uInt)
    val uShort: UShort = 25U
    println(uShort)
    // Дробные
    val double1 = 1.89
    println(double1)
    val double2 = 24e-4 // 0.0024
    println(double2)
    val float = 1.65F
    println(float)
    // Булевые
    val bool = true
    println(bool)
    // Символьные
    val symbol = 'A'
    println(symbol)
    // Строковые
    val string1 = "Hello"
    println(string1)
    val string2 = """
                    Hello1
                    Hello2
                    Hello3
                """.trimIndent()
    println(string2)
    // Форматируемые строки
    val item1 = "Alex"
    val item2 = "Smith"
    val string3 = "Hello, $item1 $item2"
    println(string3)
    // Неопределенное значение
    var any: Any = "Hello"
    println(any)
    any = 12.65
    println(any)
    // Преобразование типов
    val intNew = 100
    println(intNew.toLong())
    println()

    // Консольный ввод
//    print("Введите число: ")
//    val numberFromConsole = readLine()
//    println("Ваше число = $numberFromConsole")

    // Операции
    var a = 100
    val b = 5
    val c = a + b
    println(c)
    val d = a - b
    println(d)
    val e = a * b
    println(e)
    val f = a / b // 20
    println(f)
    val g = a / 7.0 // Дробное
    println(g)
    val h = a % 40 // 20
    println(h)
    println(++a) // 101
    println(a++) // 101
    println(a) // 102
    println(--a) // 101
    println(a--) // 101
    println(a) // 100
    a += b
    println(a) // 105
    a -= b
    println(a) // 100
    println()

    // Операторы отношения
    val min = 23
    val max = 100
    val falseValue = min > max
    println(falseValue) // false
    println(40 > 22) // true
    println(min >= 12) // true
    println(max == 10 * 10) // true
    println(34 != 34) // false
    println()

    // Логические операторы
    println(true and false) // false
    println(true or false) // true
    println(true xor false) // true
    println(true xor false xor true) // false
    println(!true) // false
    println(true.not()) // false
    println(3 in 1..4) // true
    println(5 !in 2..7) // false
    println()

    // Условные операторы
    if (a > 50 && a < 200) {
        println("OK") // Это
    } else if (a <= 50) {
        println("NOT OK")
    } else if (a >= 200) {
        println("NOT OK TOO")
    }
    // Без фигурных скобок
    if (a > 50 && a < 200)
        println("2 OK") // Это
    else if (a <= 50)
        println("2 NOT OK")
    else if (a >= 200)
        println("2 NOT OK TOO")
    // Типа тернарного
    println(if (a > 150) "OK" else "NOT OK") // NOT OK
    println()

    // Оператор when (аналог switch в Java)
    when (bool) {
        true -> println("TRUE") // Это
        false -> println("FALSE")
    }
    when (bool) {
        true -> println("TRUE") // Это
        else -> println("FALSE")
    }
    when (bool) {
        true -> {
            println("TRUE") // Это
            println("SECOND TRUE")
        }
        false -> println("FALSE")
    }
    when (a) {
        10, 50 -> println("TRUE")
        else -> println("FALSE") // Это
    }
    when (a) {
        in 1..500 -> println("TRUE") // Это
        else -> println("FALSE")
    }
    when (val x = 250) {
        in 1..500 -> println(x) // Это
        else -> println("FALSE")
    }
    println(
        when (val x = 100) {
            100 -> "PRINT OK: $x"
            200 -> "PRINT NOT OK: $x"
            else -> "PRINT FULLY NOT OK: $x"
        }
    )
    println()

    // Циклы
    for (n in 1..9) {
        print("${n * n} \t")
    }
    println()
    // Вложенные циклы
    for (i in 1..9) {
        for (j in 1..9) {
            print("${i * j} \t")
        }
        println()
    }
    var i = 5
    while (i > 0) {
        print("${i * i} \t")
        i--
    }
    println()
    do {
        print("${i * i} \t")
        i++
    } while(i < 5)
    for (n in 1..8) {
        if (n == 5) continue;
        println(n * n)
    }
    for (i in 1..3) {
        for (j in 1..3) {
            if (j == 3) break
            println("Hello")
        }
    }
    println()
    // Метки
    OuterLoop@ for (i in 1..3) {
        for (j in 1..3) {
            if (j == 3) break@OuterLoop
            println("Hello")
        }
    }
    println()

    // Диапазоны
    val range1 = 1..5 // 12345
    val isInRange1 = 3 in range1 // true
    for (item in range1) print(item)
    println()
    val range2 = 'a'..'e' // abcde
    val isInRange2 = 'k' in range2 // false
    for (item in range2) print(item)
    println()
    val range3 = 5 downTo 1 // 54321
    for (item in range3) print(item)
    println()
    val range4 = 1..10 step 2 // 13579
    for (item in range4) print(item)
    println()
    val range5 = 1 until 7 // 123456
    for (item in range5) print(item)
    println()
    println()

    // Массивы
    val array1: Array<Int> = arrayOf(1, 2, 3)
    val array2 = arrayOf(4, 5, 6)
    val array3 = arrayOfNulls<Int>(3)  // [null, null, null]
    for (item in array3) print(item)
    println()
    val array4 = Array(3) { 5 } // [5, 5, 5]
    for (item in array4) print(item)
    println()
    var z = 1;
    val array5 = Array(3) { z++ * 2 } // [2, 4, 6]
    for (item in array5) print(item)
    println()
    val arrayInts1: IntArray = intArrayOf(1, 2, 3, 4, 5)
    val arrayDoubles1: DoubleArray = doubleArrayOf(2.4, 4.5, 1.2)
    val arrayInts2 = intArrayOf(1, 2, 3, 4, 5)
    val arrayDoubles2 = doubleArrayOf(2.4, 4.5, 1.2)
    for (item in arrayDoubles2) print(item)
}