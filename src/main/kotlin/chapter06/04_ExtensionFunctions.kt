package chapter06

fun extensionFunctions() {
    // Функции расширения:
    println("В этом слове четное количество символов (Привет): ${"Привет".charParity()}")
    println("В этом слове нечетное количество символов (Дом): ${"Дом".charParity()}")
    println("Куб числа 5: ${5.cube()}")
    println("Переворот слова сработает вопреки нашему переопределению (Кошка): ${"Кошка".reversed()}")
    // Функции расширения с получателем:
    val box = Box(12, 14, 25)
    box.printInfo()
    box.update {
        height = 33
        length = 100
    }.printInfo()
    // Перегрузка операторов:
    var person1 = Person("Дима", 25)
    val person2 = Person("Саша", 22)
    println("Старше ли Дима чем Саша? (да): ${person1 > person2}")
    // Операторы в виде функций расширений:
    println("Результат сложения двух человек: ${person1 + person2}")
    println("Результат добавления человеку возраста: ${18 + person2}")
    // Переопределение унарных операторов (+a -a !a):
    println("Результат изменения возраста на отрицательный: ${-person2}")
    // Переопределение инкремента/декремента:
    println("Результат увеличения возраста на единицу: ${++person1}")
    // Переопределение equals:
    println("Результат сравнения двух человек (не равны): ${person1 == person2}")
    // Также переопределяются операторы присвоения, in, доступа по индексу и вызова
}

/** Функция, расширяющая String - четность символов */
fun String.charParity(): Boolean = this.length % 2 == 0

/** Функция, расширяющая Int - возведение в куб */
fun Int.cube(): Int = this * this * this

/** Переопределение базовой функции не будет работать */
fun String.reversed(): String = "$this (а вот и нет!!!)"

/** Класс для расширения */
class Box(var height: Int, var width: Int, var length: Int) {
    fun printInfo() {
        println("Коробка с размерами $height*$width*$length")
    }
}

/** Функция, расширяющая Box */
fun Box.update(block: Box.() -> Unit): Box {
    block()
    return this
}

/** Класс с перегруженным оператором */
class Person(val name: String, val age: Int) {
    operator fun compareTo(person: Person): Int = this.age - person.age

    override fun toString(): String {
        return "{Имя: \"$name\", Возраст: \"$age\"}"
    }

    override operator fun equals(other: Any?): Boolean {
        if(other is Person) return this.name == other.name && this.age == other.age
        return false
    }

    override fun hashCode(): Int {
        var result = age
        result = 31 * result + name.hashCode()
        return result
    }
}

/** Перегруженный оператор */
operator fun Person.plus(person: Person): Person {
    return Person("${this.name} и ${person.name}", this.age + person.age)
}

/** Перегруженный оператор */
operator fun Int.plus(person: Person): Person {
    return Person(person.name, this + person.age)
}

/** Перегруженный оператор */
operator fun Person.unaryMinus(): Person {
    return Person(this.name, -this.age)
}

/** Перегруженный оператор */
operator fun Person.inc(): Person {
    return Person(this.name, this.age + 1)
}