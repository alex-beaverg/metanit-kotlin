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
    // Изменяемые коллекции:

}