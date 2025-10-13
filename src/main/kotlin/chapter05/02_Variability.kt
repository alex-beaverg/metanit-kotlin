package chapter05

fun variability() {
    // Инвариантность:
    val file: File = Picture("Так сработает")
    val storage: Storage<File> = Storage(file)
    println(storage.file.value)
    println("Так не сработает: val storage: Storage<File> = Storage<Picture>(Picture(\"Так не сработает\"))")
    // Ковариантность:
    val fileOut: File = Picture("Так сработает")
    val storageOut: OutStorage<File> = OutStorage(fileOut)
    println(storageOut.file.value)
    val storageOut2: OutStorage<File> = OutStorage(Picture("А вот тут и так сработает"))
    println(storageOut2.file.value)
    // Контр-вариантность:
    val fileIn: File = Picture("Так сработает")
    val storageIn: InStorage<File> = InStorage()
    storageIn.printInfo(fileIn)
}

/** Класс для инвариантности */
class Storage<T: File>(val file: T)

/** Класс для ковариантности */
class OutStorage<out T: File>(val file: T)

/** Класс для контр-вариантности */
class InStorage<in T: File>() {
    fun printInfo(file: T) {
        println("Так можно: ${file.value}")
    }
}

/** Класс для наследования */
open class File(val value: String)

/** Класс-наследник */
class Picture(value: String) : File(value)