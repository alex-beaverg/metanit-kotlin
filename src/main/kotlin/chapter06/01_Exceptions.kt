package chapter06

fun exceptions() {
    // ИСКЛЮЧЕНИЯ:
    // Базовая реализация:
    handleException(10, 5)
    handleException(10, 0)
    // Проброс исключения:
    try {
        println("Проверка возраста в 150 лет:")
        throwException(150)
    } catch (e: Exception){
        println(e.message)
    }
    // Возврат значения обработчиком исключения:
    println(try { throwException(200) } catch (_: Exception) { null })
}

/** Функция с обработкой исключений */
fun handleException(a: Int, b: Int) {
    try {
        println("Результат целочисленного деления $a на $b: ${a / b}")
    } catch (e: ArithmeticException) {
        println("Результат деления на ноль - Исключение")
        println("Базовое сообщение от исключения: ${e.message}")
        println("Информация по обработанному исключению")
        for(line in e.stackTrace) {
            println("at $line")
        }
    } catch (e: Exception) {
        println("Базовое сообщение от исключения: ${e.message}")
        println("Информация по обработанному исключению")
        for(line in e.stackTrace) {
            println("at $line")
        }
    } finally {
        println("Операция деления $a на $b завершена")
    }
}

/** Функция с пробросом исключения */
fun throwException(age: Int) : Int {
    if(age < 1 || age > 125) throw Exception("Некорректное значение возраста: $age")
    println("Возраст $age корректен")
    return age
}