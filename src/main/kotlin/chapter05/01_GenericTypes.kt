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
    // Ограничения обощений:

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
fun <T> getBiggest(args1: Array<T>, args2: Array<T>): Array<T>{
    if(args1.size > args2.size) return args1
    return  args2
}