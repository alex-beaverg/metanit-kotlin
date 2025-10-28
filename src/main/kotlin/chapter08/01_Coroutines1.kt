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
    withNestedCoroutine()
    // Объект Job и ожидание join():
    withJobJoin()
    // Отложенный Job:
    withLazyJob()
    // Объект Deferred и ожидание await():
    withDeferredAwait()
    // Объект Deferred и ожидание await() на несколько coroutine (запускаются одновременно):
    withDeferredAwaitCoroutines()
    // Объект с отложенным Deferred и ожидание await() на несколько coroutine (запускаются пока не закончит предыдущий):
    withDeferredLazyAwaitCoroutines()
    // Диспетчер coroutine:

}

/** Функция с задержкой без coroutine */
suspend fun withoutCoroutine() {
    for(i in 1..4) {
        delay(200L)
        println(i)
    }
    println("Прошлись по циклу с задержками без coroutine")
}

/** Функция с задержкой с coroutine */
suspend fun withCoroutine() = coroutineScope {
    launch {
        for(i in 1..4) {
            delay(200L)
            println(i)
        }
    }
    println("Параллельно идем по циклу с задержками в составе coroutine")
    println("И выводим это сообщение параллельно с циклом в coroutine")
    println("Оно без задержек, поэтому будет выведено до того как отработает даже первый println() в цикле")
}

/** Функция с задержкой с coroutines */
suspend fun withCoroutines() = coroutineScope {
    launch {
        for(i in 1..4) {
            delay(200L)
            println(i)
        }
    }
    launch {
        for(i in 10..40 step 10) {
            delay(100L)
            println(i)
        }
    }
    println("Запускаем параллельно несколько coroutine")
}

/** Функция с задержкой runBlocking coroutine */
fun withRunBlocking() = runBlocking {
    launch {
        for(i in 1..4) {
            delay(200L)
            println(i)
        }
    }
    println("Запускаем процесс с блокировкой вызывающего потока")
}

/** Функция с задержкой с вложенным coroutine */
suspend fun withNestedCoroutine() = coroutineScope {
    launch {
        delay(500L)
        println("Внешний coroutine")
        launch {
            delay(500L)
            println("Внутренний coroutine")
        }
    }
    println("Параллельно запускаем coroutine")
}

/** Функция с задержкой с coroutine с ожиданием его выполнения */
suspend fun withJobJoin() = coroutineScope {
    val job = launch {
        for(i in 1..4) {
            delay(100L)
            println(i)
        }
    }
    println("Параллельно запускаем coroutine и ожидаем выполнения Job")
    job.join() // Ожидание завершения coroutine
    println("Дождались выполнения Job")

}

/** Функция с задержкой с отложенным coroutine */
suspend fun withLazyJob() = coroutineScope {
    val job = launch(start = CoroutineStart.LAZY) {
        for(i in 1..4) {
            delay(100L)
            println(i)
        }
    }
    println("Поставили задержку")
    delay(500)
    job.start() // Запускаем Job с coroutine
    delay(250)
    println("Запускаем coroutine с Job за 250 мс до этого сообщения")
}

/** Функция с задержкой с coroutine с ожиданием его выполнения */
suspend fun withDeferredAwait() = coroutineScope {
    val message: Deferred<String> = async { getMessage() }
    println("Запускаем процесс запроса сообщения")
    println("Сообщение: ${message.await()}")
    println("Сообщение получено, программа завершена!")
}

/** Функция сообщения с задержкой */
suspend fun getMessage(): String {
    delay(1000L)
    return "Функция выполнена после 1 секунды ожидания!"
}

/** Функция с задержкой с coroutine с ожиданием его выполнения */
suspend fun withDeferredAwaitCoroutines() = coroutineScope {
    val message1: Deferred<String> = async { getMessage(500L) }
    val message2: Deferred<String> = async { getMessage(100L) }
    val message3: Deferred<String> = async { getMessage(1500L) }
    println("Запускаем процесс запроса сообщений")
    println("Сообщение: ${message1.await()}")
    println("Сообщение: ${message2.await()}")
    println("Сообщение: ${message3.await()}")
    println("Сообщения получены, программа завершена!")
}

/** Функция сообщения с задержкой */
suspend fun getMessage(delayMillis: Long): String {
    delay(delayMillis)
    return "Функция выполнена после ${delayMillis / 1000.0} секунды ожидания!"
}

/** Функция с задержкой с отложенным coroutine с ожиданием его выполнения */
suspend fun withDeferredLazyAwaitCoroutines() = coroutineScope {
    val message1: Deferred<String> = async(start = CoroutineStart.LAZY) { getMessage(1300L) }
    val message2: Deferred<String> = async(start = CoroutineStart.LAZY) { getMessage(100L) }
    val message3: Deferred<String> = async(start = CoroutineStart.LAZY) { getMessage(1500L) }
    println("Запускаем процесс запроса сообщений")
    println("Сообщение: ${message1.await()}")
    println("Сообщение: ${message2.await()}")
    println("Сообщение: ${message3.await()}")
    println("Сообщения получены, программа завершена!")
}