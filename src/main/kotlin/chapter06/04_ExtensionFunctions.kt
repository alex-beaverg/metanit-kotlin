package chapter06

fun extensionFunctions() {
    // Функции расширения:
    println("В этом слове четное количество символов (Привет): ${"Привет".charParity()}")
    println("В этом слове нечетное количество символов (Дом): ${"Дом".charParity()}")
    println("Куб числа 5: ${5.cube()}")
    println("Переворот слова сработает вопреки нашему переопределению (Кошка): ${"Кошка".reversed()}")
    // Функции расширения с получателем:

}

/** Функция, расширяющая String - четность символов */
fun String.charParity(): Boolean = this.length % 2 == 0

/** Функция, расширяющая Int - возведение в куб */
fun Int.cube(): Int = this * this * this

/** Переопределение базовой функции не будет работать */
fun String.reversed(): String = "$this (а вот и нет!!!)"