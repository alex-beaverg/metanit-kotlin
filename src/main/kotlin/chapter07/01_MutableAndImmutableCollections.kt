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
    val nextNewSet = newSet.plus("500")
    println("Добавили элемент плюсом:")
    nextNewSet.forEach { element -> println("\t- $element") }
    val unionSet = set.union(nextNewSet)
    println("Объединили два множества:")
    unionSet.forEach { element -> println("\t- $element") }

    // Map:

    // Sequence:
    val sequence = sequenceOf(100, 200, 300)
    println("Максимальное значение последовательности: ${sequence.max()}")
    println("Сумма элементов последовательности: ${sequence.sum()}")

    // Array:

}