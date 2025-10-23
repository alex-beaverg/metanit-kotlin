package chapter07

import java.util.Date

fun mutableAndImmutableCollections() {
    // ИЗМЕНЯЕМЫЕ И НЕИЗМЕНЯЕМЫЕ КОЛЛЕКЦИИ:
    // Неизменяемые коллекции:
    val imList = if (Date().time % 2 == 0L) listOf() else listOf(1, 2, 3)
    println("Пустой ли лист: ${imList.isEmpty()}")
    val imSet = setOf("1", "2", "3")
    println("Размер множества: ${imSet.size}")
    val imMap = mapOf(Pair(1, "One"), Pair(2, "Two"))
    println("Содержит ли карта значение по ключу 2: ${imMap.contains(2)}")

    // ИЗМЕНЯЕМЫЕ КОЛЛЕКЦИИ:
    // List:
    val list = mutableListOf(1, 2, 3)
    list.add(4)
    list.add(1, 200)
    list.remove(3)
    list.removeAt(0)
    list.forEach { element -> println("\t- $element") }
    println("Или так можно вывести: $list")
    println("Второй элемент списка: ${list[1]}") // Равносильно list.get(1)
    println("Несуществующий элемент списка без генерации исключения: ${list.getOrNull(10)}")
    println("Последний элемент списка вместо несуществующего: ${list.getOrElse(10) { list[list.size - 1] }}")
    list.clear()
    println("Размер листа после очистки: ${list.size}")
    // Set:
    val set = mutableSetOf("1", "2", "3")
    println("Все ли элементы при конвертировании в целочисленные менее 10: ${set.all { it.toInt() < 10 }}")
    println("Хотя бы 1 элемент при конвертировании в целочисленные менее 2: ${set.any { it.toInt() < 2 }}")
    println("Количество элементов во множестве как count(): ${set.count()}")
    val newSet = set.filter { it.toInt() < 3 }
    println("Убрали элемент по фильтру:")
    newSet.forEach { element -> println("\t- $element") }
    println("Или так можно вывести: $set")
    val nextNewSet = newSet.plus("500")
    println("Добавили элемент плюсом:")
    nextNewSet.forEach { element -> println("\t- $element") }
    val unionSet = set.union(nextNewSet)
    println("Объединили два множества:")
    unionSet.forEach { element -> println("\t- $element") }
    println("Пересекающиеся элементы множеств: ${newSet.intersect(set)}")
    println("Пересекающиеся элементы множеств (другая конструкция): ${newSet intersect set}")
    println("Вычитание множеств: ${unionSet.subtract(set)}")
    println("Вычитание множеств (другая конструкция): ${unionSet subtract(set)}")
    val newMutableSet = unionSet.toMutableSet()
    newMutableSet.addAll(setOf("350", "820", "54"))
    println("Перевели множество в изменяемое и добавили элементы: $newMutableSet")
    // Map:
    val map = mutableMapOf(Pair(1, "One"), 2 to "Two", 3 to "Three")
    println("Карта: $map")
    map.forEach { (key, value) -> println("\t$key: $value") }
    println("Значение по ключу 2 -> Two: ${map[2]}")
    println("Значение по ключу 5 -> null: ${map[5]}")
    println("Значение по ключу 5 с умолчанием -> default: ${map.getOrDefault(5, "default")}")
    println("Значение по ключу 5 с ИЛИ -> Not Found: ${map.getOrElse(5){"Not Found"}}")
    println("Ключи: ${map.keys}")
    println("Значения: ${map.values}")
    println("Есть ли ключ 3: ${map.containsKey(3)}")
    println("Есть ли значение Four: ${map.containsValue("Four")}")
    map.put(4, "Four")
    map.putAll(mapOf(Pair(5, "Five")))
    map[2] = "Second"
    map.remove(1)
    println("Обновленная карта: $map")
    // Sequence:
    val sequence = sequenceOf(100, 200, 300, 300, 400)
    println("Максимальное значение последовательности: ${sequence.max()}")
    println("Сумма элементов последовательности: ${sequence.sum()}")
    println("Последовательность: ${sequence.joinToString(":", "(", ")")}")
    println("Последовательность из множества: ${set.asSequence().joinToString(":", "(", ")")}")
    var i = 0
    println("Генерируем последовательность: ${generateSequence { i += 2; if (i > 13) null else i}.joinToString()}")
    println("Генерируем последовательность иначе: ${sequence { yield(4); yield(6) }.joinToString()}")
    println("Операция .all(): ${sequence.all { it % 100 == 0 }}")
    println("Операция .any(): ${sequence.any { it % 150 == 0 }}")
    println("Операция .average(): ${sequence.average()}")
    println("Операция .chunked(): ${sequence.chunked(2).joinToString()}")
    println("Операция .distinct(): ${sequence.distinct().joinToString()}")
    println("Операция .drop(): ${sequence.drop(2).joinToString()}")
    println("Операция .filter(): ${sequence.filter { it % 200 == 0 }.joinToString()}")
    println("Операция .filterNot(): ${sequence.filterNot { it % 200 == 0 }.joinToString()}")
    println("Операция .fold(): ${sequence.fold(0) { result, element -> result + element }}")
    println("Операция .groupBy(): ${sequence.groupBy { it }}")
    println("Операция .map(): ${sequence.map { it % 75 }.joinToString()}")
    println("Операция .shuffled(): ${sequence.shuffled().joinToString()}")
    println("Операция .sortedDescending(): ${sequence.sortedDescending().joinToString()}")
    println("Операция .take(): ${sequence.take(2).joinToString()}")
    println("Операция .toList(): ${sequence.toList()}")
    // Array:
    println("Массив из null: ${arrayOfNulls<Int>(2).joinToString()}")
    println("Массив из одинаковых значений: ${Array(3) { "Hello" }.joinToString()}")
    val array = arrayOf(50, 125, 44, 13, 100)
    println("Второй элемент массива: ${array[1]}")
    array[0] = 45
    println("Обновленный массив: ${array.joinToString()}")
    println("Диапазон индексов массива: ${array.indices}")
    array.forEach { println("\t- $it") }
    array.forEachIndexed { index, element -> println("$index. $element") }
    val squareArray = arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(7, 8, 9))
    for (row in squareArray) {
        for (element in row) {
            print("$element ")
        }
        println()
    }
    // Разница коллекций и последовательностей:
    // Операция в коллекции выполняются для всех элементов, потом вторая операция
    // Операции в коллекции выполняются вне зависимости от того используется ли коллекция потом
    val people1 = listOf(
        Person("Tom", 37),
        Person("Sam", 25),
        Person("Alice", 33)
    )
    println("Это выведется, хоть и не используется дальше:")
    people1.filter { println("Age filter: $it"); it.age > 30 }
        .filter{ println("Name filter: $it"); it.name.length == 3 }
    // Операция в последовательности выполняется для й элемента, потом вторая операция, потом то же для следующего
    // Операции в последовательности выполняются только там, где нужно далее по коду использовать ее
    var people2 = sequenceOf(
        Person("Tom", 37),
        Person("Sam", 25),
        Person("Alice", 33)
    )
    println("Это не выведется пока мы не начнем это использовать:")
    people2 = people2.filter { println("Age filter: $it"); it.age > 30 }
        .filter{ println("Name filter: $it"); it.name.length == 3 }
    println("А вот тут выведется, но в порядке поэлементного прохода по всем операциям:")
    for(person in people2) println(person)
}

/** Класс Person */
data class Person(val name: String, val age: Int)