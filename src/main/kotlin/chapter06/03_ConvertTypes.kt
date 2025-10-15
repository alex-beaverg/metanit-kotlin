package chapter06

fun convertTypes() {
    // ПРЕОБРАЗОВАНИЕ ТИПОВ
    // Базовая реализация:
    var str = "55"
    val num: Int = str.toInt()
    println("Это преобразованное в Int число из String: $num")
    // Такие преобразования лучше делать через исключения:
    str = "Число"
    try {
        println("Это не выведется на печать: ${str.toInt()}")
    } catch(e: Exception){
        println("Исключение при преобразовании типов: ${e.message}")
    }
    // Умное преобразование Smart Cast с оператором "is" и обратным "!is":
    val dog = Pet("Тузик")
    val cat = Cat("Мурзик", "Черный")
    println("Проверим, является ли ${dog.name} котом (нет): ${isKitty(dog)}")
    println("Проверим, является ли ${dog.name} не котом (да): ${isNotKitty(dog)}")
    println("Проверим, является ли ${cat.color} ${cat.name} котом (да): ${isKitty(cat)}")
    println("Проверим, является ли ${cat.color} ${cat.name} не котом (нет): ${isNotKitty(cat)}")
    // К изменяемым типам var (в свойствах) оператор is не применяется:

}

/** Класс для наследования */
open class Pet(val name: String)

/** Класс наследник */
class Cat(name: String, val color: String): Pet(name)

/** Функция проверки на котика */
fun isKitty(pet: Pet) : Boolean {
    return pet is Cat
}

/** Функция проверки на не котика */
fun isNotKitty(pet: Pet) : Boolean {
    return pet !is Cat
}