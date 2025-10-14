package chapter06

import java.util.Date

fun nullAndNullableTypes() {
    // Null тип:
    val a = null
    println("Null тип: $a")
    // val a: Int = null - так нельзя, Int не может быть null
    // Nullable тип:
    val b: Int? = null
    println("А так (Int?) можно, это Nullable тип: $b")
    val some: Some? = null
    println("И так (ИмяКласса?) тоже можно, это Nullable тип: $some")
    // Не Nullable переменной нельзя присвоить значение Nullable переменной
    // Для этого нужно использовать оператор "?:":
    val name1: String?  = "Алексей"
    val userName1: String = name1 ?: "Undefined"
    println("Не Nullable переменная получает значение из Nullable переменной: $userName1")
    val name2: String?  = null
    val userName2: String = name2 ?: "Undefined"
    println("Не Nullable переменная получает null-значение из Nullable переменной: $userName2")
    // Оператор ? с точкой:
    val message: String? = if (Date().time % 2 == 0L) null else "Hello"
    val length1: Int? = message?.length
    val length2: Int = message?.length ?:0
    println("Тут должен вернуться null или 5: $length1")
    println("Тут должен вернуться 0 или 5: $length2")
    // Оператор "!!" not-null assertion operator:
    val name : String? = "Дима"
    val otherName: String = name!!
    println("Вот тут будет имя, потому что мы уверены что тут не null: $otherName")
    println("А вот тут возможно будет исключение и возвращаем null или имя: ${
        try {
            val name : String? = if (Date().time % 2 == 0L) null else "Саша"
            val id= name!!
            println(id)
        } catch (_: Exception) { 
            null
        }
    }")
}

/** Класс Some */
class Some()