package chapter05

fun genericTypes() {
    // Обобщенные типы:
    val alex: Person<Int> = Person(100, "Алексей")
    alex.printInfo()
    alex.compareId(100)
    val dima: Person<Long> = Person(120L, "Дима")
    dima.printInfo()
    dima.compareId(500L)
    // Несколько обобщенных параметров:
    val book: Book<Int, String> = Book(150, "105")
    book.printInfo()
    // Обобщенные функции:
    for (item in getBiggest(arrayOf(1, 5, 7, 90), arrayOf(5, 3))) {
        println("Элемент наибольшего массива: $item")
    }
    // Ограничения обобщений:
    println("Большее из двух значений A и R: ${compareObjects("A", "R")}")
    val discountPrice = DiscountPrice(0.85)
    println("Цена 100 руб с учетом скидки 0.85: ${calculatePrice(100.0, discountPrice)}")
    // Несколько ограничений:
    println("Большее из двух значений 5 и 10: ${compareNumbers(5, 10)}")
    // Ограничения в классах:
    val email = EmailMessage("Привет, друг!")
    val outlook = Messenger<EmailMessage>()
    outlook.send(email)
    val sms = SmsMessage("Привет, друг, чего не отвечаешь на е-мэйл?")
    val telegram = Messenger<SmsMessage>()
    telegram.send(sms)
}

/** Класс с обобщенными типами */
class Person<T>(val id: T, val name: String) {
    fun printInfo() {
        println("Человек $name с id = $id")
    }

    fun compareId(newId: T) {
        if (newId == id) {
            println("Идентификаторы совпадают: $newId = $id")
        } else {
            println("Идентификаторы не совпадают: $newId не равно $id")
        }
    }
}

/** Класс с несколькими обобщенными типами */
class Book<K, V>(val pages: K, val price: V) {
    fun printInfo() {
        println("Эта книга стоит $price рублей и в ней $pages страниц")
    }
}

/** Функция с обобщениями */
fun <T> getBiggest(args1: Array<T>, args2: Array<T>): Array<T> {
    if (args1.size > args2.size) return args1
    return  args2
}

/** Функция с обобщениями с ограничением */
fun <T: Comparable<T>> compareObjects(arg1: T, arg2: T): T {
    if (arg1 > arg2) return arg1
    return  arg2
}

/** Интерфейс с одним свойством */
interface Discount {
    val discountValue: Double
}

/** Функция с обобщением с ограничением */
fun<T: Discount> calculatePrice(price: Double, discount: T): Double {
    return price * discount.discountValue
}

/** Класс с ограниченным обобщением, реализующий интерфейс */
class DiscountPrice(override val discountValue: Double): Discount

/** Функция с обобщениями с двойным ограничением */
fun <T> compareNumbers(arg1: T, arg2: T): T where T: Comparable<T>, T: Number {
    if (arg1 > arg2) return arg1
    return  arg2
}

/** Класс с ограничениями обобщения */
class Messenger<T>() where T: Message, T: Logger {
    fun send(mes: T) {
        mes.log()
    }
}

/** Интерфейс с одним свойством */
interface Message {
    val text: String
}

/** Интерфейс с одним методом */
interface Logger {
    fun log()
}

/** Класс с ограничениями обобщения, реализующий интерфейсы */
class EmailMessage(override val text: String): Message, Logger {
    override fun log() = println("Отправка Е-мэйл: $text")
}

/** Класс с ограничениями обобщения, реализующий интерфейсы */
class SmsMessage(override val text: String): Message, Logger{
    override fun log() = println("Отправка СМС: $text")
}