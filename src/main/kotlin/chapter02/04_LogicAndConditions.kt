package chapter02

import kotlin.random.Random

fun logicAndConditions() {
    val randomFirstBooleanValue = Random.nextBoolean()
    val randomSecondBooleanValue = Random.nextBoolean()

    // ЛОГИЧЕСКИЕ ОПЕРАТОРЫ
    // Оператор and - в результате будет true только если все операнды равны true:
    println("Результат сочетания $randomFirstBooleanValue and $randomSecondBooleanValue: ${randomFirstBooleanValue and randomSecondBooleanValue}")
    // Оператор or - в результате будет true если хотя бы один операнд равны true:
    println("Результат сочетания $randomFirstBooleanValue or $randomSecondBooleanValue: ${randomFirstBooleanValue or randomSecondBooleanValue}")
    // Оператор xor - в результате будет true если только один операнд равны true:
    println("Результат сочетания $randomFirstBooleanValue xor $randomSecondBooleanValue: ${randomFirstBooleanValue xor randomSecondBooleanValue}")
    // Оператор ! - возвращает обратное значение операнда:
    println("Результат операции !$randomFirstBooleanValue: ${!randomFirstBooleanValue}")
    // Метод .not() - возвращает обратное значение операнда:
    println("Результат операции $randomFirstBooleanValue.not(): ${randomFirstBooleanValue.not()}")

    // УСЛОВНЫЕ ОПЕРАТОРЫ
    val randomIntValue = (Math.random() * 100).toInt()
    val dependValue = 100 - randomIntValue
    // С использованием фигурных скобок (как в Java):
    if (randomIntValue > 50 && randomIntValue <= 100) {
        println("Результат сравнения: число $randomIntValue находится в диапазоне (50, 100]")
    } else if (randomIntValue >= 0 && randomIntValue <= 50) {
        println("Результат сравнения: число $randomIntValue находится в диапазоне [0, 50]")
    }
    // Без использования фигурных скобок:
    if (dependValue > 50 && dependValue <= 100)
        println("Результат сравнения: число $dependValue находится в диапазоне (50, 100]")
    else if (dependValue >= 0 && dependValue <= 50)
        println("Результат сравнения: число $dependValue находится в диапазоне [0, 50]")
    // В одну строку:
    println("Результат сравнения: " + if (randomIntValue > 50) "число $randomIntValue > 50" else "число $randomIntValue < 50")

    // ОПЕРАТОР ПЕРЧИСЛЕНИЯ (аналог switch в Java)
    // Когда предполагается одно действие:
    when (randomFirstBooleanValue and randomSecondBooleanValue) {
        true -> println("Результат сочетания $randomFirstBooleanValue and $randomSecondBooleanValue - это true")
        false -> println("Результат сочетания $randomFirstBooleanValue and $randomSecondBooleanValue - это false")
    }
    // Когда предполагается несколько действий:
    when (randomFirstBooleanValue or randomSecondBooleanValue) {
        true -> {
            println("Результат сочетания $randomFirstBooleanValue or $randomSecondBooleanValue:")
            println("\t- это true")
        }
        false -> {
            println("Результат сочетания $randomFirstBooleanValue or $randomSecondBooleanValue:")
            println("\t- это false")
        }
    }
    // Когда несколько значений претендуют на одно и то же действие:
    when (randomIntValue) {
        10, 20, 30, 40, 50 -> println("Результат выбора: $randomIntValue равно одному из чисел (10, 20, 30, 40, 50)")
        else -> println("Результат выбора: $randomIntValue НЕ равно ни одному из чисел (10, 20, 30, 40, 50)")
    }
    // Когда проверяемое число лежит в диапазоне:
    when (randomIntValue) {
        in 0..50 -> println("Число $randomIntValue лежит в диапазоне [0, 50]")
        else -> println("Число $randomIntValue больше 50")
    }
    // Вместо аргумента метода:
    println(
        when (randomIntValue) {
            60 -> "Число $randomIntValue равно 60"
            70 -> "Число $randomIntValue равно 70"
            80 -> "Число $randomIntValue равно 80"
            90 -> "Число $randomIntValue равно 90"
            100 -> "Число $randomIntValue равно 100"
            else -> "Число $randomIntValue НЕ равно ни одному из чисел (60, 70, 80, 90, 100)"
        }
    )
}