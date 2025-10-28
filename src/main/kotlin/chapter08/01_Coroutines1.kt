package chapter08

import kotlinx.coroutines.*

suspend fun coroutines1() {
    // Введение в coroutine:
    println("Без coroutine:")
    withoutCoroutine()
    withCoroutine()
    // Несколько параллельных coroutines:
    withCoroutines()
    // Блокируем вызывающий поток:
    withRunBlocking()
    // Вложенные coroutines:

}

suspend fun withoutCoroutine() {
    for(i in 1..5) {
        delay(200L)
        println(i)
    }
    println("Прошлись по циклу с задержками без coroutine")
}

suspend fun withCoroutine() = coroutineScope {
    launch {
        for(i in 1..5) {
            delay(200L)
            println(i)
        }
    }
    println("Параллельно идем по циклу с задержками в составе coroutine")
    println("И выводим это сообщение параллельно с циклом в coroutine")
    println("Оно без задержек, поэтому будет выведено до того как отработает даже первый println() в цикле")
}

suspend fun withCoroutines() = coroutineScope {
    launch {
        for(i in 1..5) {
            delay(200L)
            println(i)
        }
    }
    launch {
        for(i in 10..50 step 10) {
            delay(300L)
            println(i)
        }
    }
    println("Запускаем параллельно несколько coroutine")
}

fun withRunBlocking() = runBlocking {
    launch {
        for(i in 1..5) {
            delay(200L)
            println(i)
        }
    }
    println("Запускаем процесс с блокировкой вызывающего потока")
}