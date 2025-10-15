package chapter06

import java.util.Date

fun convertTypes() {
    // ПРЕОБРАЗОВАНИЕ ТИПОВ
    // Базовая реализация:
    var str = "55"
    val num: Int = str.toInt()
    println("Это преобразованное в Int число из String: $num")
    // Такие преобразования лучше делать через исключения:
    str = "Число"
    try {
        println("Это вызовет исключение: ${str.toInt()}")
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
    val catOwner = CatOwner("Вася")
    if (catOwner.pet is Cat) {
//        println("${catOwner.pet.color} ${catOwner.pet.name} - это кот") // тут ошибка из-за того что pet - изменяемая
    } else {
        println("${catOwner.name} владелец не кота по имени ${catOwner.pet.name}")
    }
    // Явные преобразования и оператор "as":
    var name: String? = "Саша"
    val otherName: String = name as String
    println("Ненулевое значение String? преобразовали в String: $otherName")
    name = if (Date().time % 2 == 0L) null else "Саша"
    try {
        println("Это может вызвать исключение: ${name as String}")
    } catch (e: NullPointerException) {
        println("Исключение при преобразовании типов: ${e.message}")
    }
    println("Пробуем определить цвет кота если это не кот: ${checkCatColor(dog)}")
    println("Пробуем определить цвет кота если это кот: ${checkCatColor(cat)}")
}

/** Класс для наследования */
open class Pet(val name: String)

/** Класс наследник */
class Cat(name: String, val color: String): Pet(name)

/** Функция проверки на котика */
fun isKitty(pet: Pet): Boolean {
    return pet is Cat
}

/** Функция проверки на не котика */
fun isNotKitty(pet: Pet): Boolean {
    return pet !is Cat
}

/** Класс с изменяемым свойством */
class CatOwner(val name: String) {
    var pet: Pet = Cat("Васька", "Белый")
}

/** Функция проверки цвета животного, если это кот */
fun checkCatColor(pet: Pet) = (pet as? Cat)?.color ?: "Это не кот"