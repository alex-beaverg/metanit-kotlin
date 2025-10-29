package chapter09

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.*

suspend fun asyncThreads() {
    // АСИНХРОННЫЕ ПОТОКИ
    // Интерфейс Flow:
    val userList = getUsers() // Создание потока
    userList.collect { user -> println(user) } // Запуск потока
    // Создание потока без функции:
    val userFlow = flow {
        val userList = listOf("Алексей", "Дима", "Таня")
        for (item in userList) {
            emit(item)
        }
    }
    userFlow.collect { user -> println(user) }
    // Функция flowOf:
    val numberFlow: Flow<Int> = flowOf(1, 2, 3, 4, 5)
    numberFlow.collect { num -> println(num) }
    // Метод asFlow:
    val userFlowNew = listOf("Алексей", "Дима", "Таня").asFlow()
    userFlowNew.collect { user -> println(user) }
    // Функция count():
    val userFlowSuperNew = listOf("Алексей", "Дима", "Таня", "Саша", "Алиса").asFlow()
    val count = userFlowSuperNew.count { username -> username.length > 4 }
    println("Количество имен с длиной более 4 символов: $count")
    // Функция take():
    userFlowSuperNew.take(3).collect { user -> println(user) }
    // Функция drop():
    userFlowSuperNew.drop(3).collect { user -> println(user) }
    // Функция first() / firstOrNull():
    println("Первое имя в потоке меньше 5 символов: ${userFlowNew.first { it.length < 5 }}")
    println("Первое имя в потоке больше 10 символов: ${userFlowNew.firstOrNull { it.length > 10 }}")
    // Функция last() / lastOrNull():
    println("Последнее имя в потоке: ${userFlowNew.last()}")
    println("Последнее имя в потоке если оно null: ${listOf(null).asFlow().lastOrNull()}")
    // Функция single() / singleOrNull():
    println("Единственное имя в потоке: ${listOf("Дима").asFlow().single()}")
    println("Единственное имя в потоке если оно null: ${listOf(null).asFlow().singleOrNull()}")
    println("Единственное имя в потоке если там их много: ${userFlowNew.singleOrNull()}")
    // Функция map():
    userFlowSuperNew.map { "Это имя: $it" }.collect { namePlus -> println(namePlus) }
    // Функция transform():
    val furnitureFlow = listOf(
        Furniture("Стол", 1),
        Furniture("Стул", 4),
        Furniture("Диван", 1),
        Furniture("Кровать", 1)
    ).asFlow()
    furnitureFlow.transform { furniture ->
        if (furniture.quantity == 4) emit("Этой мебели много: ${furniture.title}")
    }.collect { titlePlus -> println(titlePlus) }
    // Функция filter():
    furnitureFlow.filter { furniture -> furniture.quantity == 1 }.collect { furniture -> println(furniture.title) }
    // Функция takeWhile():
    userFlowSuperNew.takeWhile { name -> name.length > 4 }
        .collect { name -> println("Пока есть имя больше 4 символов: $name") }
    // Функция dropWhile():
    userFlowSuperNew.dropWhile { name -> name.length > 4 }
        .collect { name -> println("Пока не появилось имя с длиной менее 5 символов: $name") }
    // Функция reduce():
    println("Сводим поток к одному числу: ${listOf(1, 2, 3, 4, 5).asFlow().reduce { a, b -> a * b }}")
    // Функция fold():
    println("Сводим поток к одному числу: ${listOf(1, 2, 3, 4, 5).asFlow().fold(0) { a, b -> a + b }}")
    // Объединение потоков:
    val english = listOf("red", "yellow", "blue").asFlow()
    val russian = listOf("красный", "желтый", "синий").asFlow()
    english.zip(russian) { en, ru -> "$en: $ru" }.collect { words -> println(words) }
}

/** Функция с интерфейсом Flow с асинхронным получением данных */
fun getUsers(): Flow<String> = flow {
    val database = listOf("Алексей", "Дима", "Таня")
    var i = 1
    for (item in database) {
        delay(400L)
        println("Получаем $i элемент")
        emit(item)
        i++
    }
    println("Данные получены")
}

/** Дата класс для мебели */
data class Furniture(val title: String, val quantity: Int)