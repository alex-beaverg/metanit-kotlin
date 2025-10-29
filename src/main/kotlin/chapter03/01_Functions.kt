package chapter03

fun functions() {
    // ФУНКЦИИ
    // Функция без параметров:
    functionWithoutParameters()
    // Функция с параметрами:
    functionWithParameters("Привет", "Функция с параметрами")
    // Функция с параметрами по-умолчанию:
    functionWithLastDefaultParameter("Функция с параметром по умолчанию")
    functionWithLastDefaultParameter("Функция с параметром по умолчанию", "Привет")
    functionWithLastDefaultParameter(greetings = "Привет", name =  "Функция с параметром по умолчанию")
    functionWithFirstDefaultParameter(name = "Функция с параметром по умолчанию")
    // Изменение исходного массива в функции:
    val arrayForChange = arrayOf(1, 2, 3)
    println("Элемент [0] массива до попадания его в функцию: ${arrayForChange[0]}")
    functionForChangeArray(arrayForChange)
    println("Элемент [0] массива после изменения его в функции: ${arrayForChange[0]}")
    // Функции с переменным количеством параметров:
    functionWithVariableNumberOfParameters("Команда мечты", "Алексей", "Дима", "Саша", count = 3)
    val arrayAsParameter = arrayOf("Один", "Два", "Три", "Четыре")
    functionWithVariableNumberOfParameters("Набор цифр", *arrayAsParameter, count = arrayAsParameter.size)
    // Функции, возвращающие значение:
    val numberA = 5
    val numberB = 10
    println("Результат вызова функции сложения чисел $numberA и $numberB: ${functionForAddition(numberA, numberB)}")
    // Однострочная функция:
    println("Результат вызова однострочной функции умножения $numberA на $numberA: ${functionWithOneString(numberA)}")
    // Использование локальной функции:
    val randomAge1 = (Math.random() * 200).toInt()
    val randomAge2 = (Math.random() * 200).toInt()
    println("Результат выполнения функции сравнения возрастов: \n\t- ${functionForCompareAgesWithLocalFunction(randomAge1, randomAge2)}")
    // Перегрузка функций:
    val stringA = "Аб"
    val stringB = "Вг"
    val numberC = 100
    println("Результат вызова перегруженной функции сложения двух целых чисел $numberA и $numberB: ${overloadFunction(numberA, numberB)}")
    println("Результат вызова перегруженной функции сложения строк $stringA и $stringB: ${overloadFunction(stringA, stringB)}")
    println("Результат вызова перегруженной функции сложения трех целых чисел $numberA, $numberB и $numberC: ${overloadFunction(numberA, numberB, numberC)}")
    // Функция как тип переменной:
    val subtract: (Int, Int) -> Int = ::functionAsVariableType
    println("Результат присвоения переменной типа и значения функции вычитания целых чисел $numberC и $numberB: ${subtract(numberC, numberB)}")
    // Функция как параметр функции:
    println("Результат использования функции 'Доброе утро' в качестве параметра: ${functionWithFunctionAsParameter(::morning)}")
    println("Результат использования функции 'Добрый вечер' в качестве параметра: ${functionWithFunctionAsParameter(::evening)}")
    // Функция в возвращаемом типе:
    var action = functionWithReturnFunction(1)
    println("Результат вызова функции, возвращающей функцию сложения двух целых чисел $numberA и $numberB: ${action(numberA, numberB)}")
    action = functionWithReturnFunction(2)
    println("Результат вызова функции, возвращающей функцию умножения двух целых чисел $numberA и $numberC: ${action(numberA, numberC)}")
    action = functionWithReturnFunction(3)
    println("Результат вызова функции, возвращающей функцию вычитания двух целых чисел $numberC и $numberA: ${action(numberC, numberA)}")
}

/** Функция без параметров */
fun functionWithoutParameters() {
    println("Вызов функции без параметров")
}

/** Функция с параметрами */
fun functionWithParameters(greetings: String, name: String) {
    println("Вызов функции с параметрами")
    println("\t- $greetings, $name")
}

/** Функция с параметром по-умолчанию в конце */
fun functionWithLastDefaultParameter(name: String, greetings: String = "Привет") {
    println("Вызов функции с параметром по-умолчанию в конце")
    println("\t- $greetings, $name")
}

/** Функция с параметром по-умолчанию в начале */
fun functionWithFirstDefaultParameter(greetings: String = "Привет", name: String) {
    println("Вызов функции с параметром по-умолчанию в начале")
    println("\t- $greetings, $name")
}

/** Изменение исходного массива в функции */
fun functionForChangeArray(array: Array<Int>) {
    array[0] *= 2
    println("Элемент [0] массива внутри функции после его изменения: ${array[0]}")
}

/** Функция с переменным количеством параметров */
fun functionWithVariableNumberOfParameters(group: String, vararg items: String, count: Int) {
    println("Группа: $group")
    println("Количество: $count")
    for (item in items) println("\t- член группы: $item")
}

/** Функция, возвращающая значение */
fun functionForAddition(a: Int, b: Int): Int {
    return a + b
}

/** Однострочная функция */
fun functionWithOneString(a: Int) = a * a

/** Функция с локальной функцией внутри */
fun functionForCompareAgesWithLocalFunction(age1: Int, age2: Int): String {
    /** Локальная функция */
    fun isInvalidAge(age: Int) = age <= 0 || age > 111

    if (isInvalidAge(age1)) {
        return "недопустимый возраст: $age1"
    } else if (isInvalidAge(age2)) {
        return "недопустимый возраст: $age2"
    }

    return when {
        age1 == age2 -> "первый возраст ($age1) равен второму возрасту ($age2)"
        age1 > age2 -> "первый возраст ($age1) больше второго возраста ($age2)"
        else -> "первый возраст ($age1) меньше второго возраста ($age2)"
    }
}

/** Перегруженная функция */
fun overloadFunction(a: Int, b: Int) = a + b

/** Перегруженная функция */
fun overloadFunction(a: String, b: String) = a + b

/** Перегруженная функция */
fun overloadFunction(a: Int, b: Int, c: Int) = a + b + c

/** Функция вычитания как тип переменной */
fun functionAsVariableType(a: Int, b: Int): Int {
    return a - b
}

/** Функция в параметре функции */
fun functionWithFunctionAsParameter(mes: () -> String) = mes()

/** Функция Доброе утро */
fun morning() = "Доброе утро"

/** Функция Добрый вечер */
fun evening() = "Добрый вечер"

/** Функция с функцией в возвращаемом типе */
fun functionWithReturnFunction(key: Int): (Int, Int) -> Int {
    return when (key) {
        1 -> ::functionForAddition
        2 -> ::functionForMultiplication
        else -> ::functionForSubtraction
    }
}

/** Функция для вычитания */
fun functionForSubtraction(a: Int, b: Int) = a - b

/** Функция для умножения */
fun functionForMultiplication(a: Int, b: Int) = a * b