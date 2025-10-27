package chapter08

import kotlinx.coroutines.*

suspend fun coroutines1() {
    // Введение в coroutine:
    println("Без coroutine:")
    withoutCoroutine()
}

suspend fun withoutCoroutine() {
    for(i in 0..5) {
        delay(200L)
        println(i)
    }
    println("Прошлись по циклу с задержками без coroutine")
}