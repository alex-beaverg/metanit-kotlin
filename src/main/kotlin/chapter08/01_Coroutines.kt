package chapter08

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce

suspend fun coroutines() {
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
    withDispatcherDefault()
    withDispatcherUnconfined()
    withDispatcherCustomNameThread()
    // Отмена выполнения coroutine:
    withCancelCoroutine()
    // CancellationException:
    withCancellationException()
    // Каналы:
    withChannel()
    withReceiveChannel(listOf("Леша", "Дима", "Лена"))
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

/** Функция с указанием потока для coroutine - Default */
suspend fun withDispatcherDefault() = coroutineScope {
    launch(Dispatchers.Default) {
        println("Coroutine выполняется на потоке: ${Thread.currentThread().name}")
    }
    println("Не coroutine выполняется на потоке: ${Thread.currentThread().name}")
}

/** Функция с указанием потока для coroutine - Unconfined */
suspend fun withDispatcherUnconfined() = coroutineScope {
    launch(Dispatchers.Unconfined) {
        println("Поток coroutine (до остановки): ${Thread.currentThread().name}")
        delay(500L)
        println("Поток coroutine (после остановки): ${Thread.currentThread().name}")
    }
    println("Поток не coroutine: ${Thread.currentThread().name}")
}

/** Функция с указанием потока для coroutine - Custom Thread */
@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
suspend fun withDispatcherCustomNameThread() = coroutineScope {
    val context = newSingleThreadContext("Custom Thread")
    launch(context) {
        println("Поток coroutine: ${Thread.currentThread().name}")
    }
    context.close()
    println("Поток не coroutine: ${Thread.currentThread().name}")
}

/** Функция с отменой coroutine */
suspend fun withCancelCoroutine() = coroutineScope {
    val downloader: Job = launch {
        println("Начинаем загрузку файлов")
        for(i in 1..5){
            println("Загружен файл $i")
            delay(500L)
        }
    }
    delay(800L)
    println("Прерываем загрузку...")
    downloader.cancel()
    downloader.join() // Можно использовать 1 метод вместо двух - cancelAndJoin()
    println("Работа программы завершена")
}

/** Функция с обработкой отмены coroutine */
suspend fun withCancellationException() = coroutineScope {
    val downloader: Job = launch {
        try {
            println("Начинаем загрузку файлов")
            for(i in 1..5){
                println("Загружен файл $i")
                delay(500L)
            }
        }
        catch (e: CancellationException) {
            println("Загрузка файлов прервана: ${e.message}")
        }
        finally {
            println("Загрузка завершена")
        }
    }
    delay(800L)
    println("Прерываем загрузку...")
    downloader.cancelAndJoin()
    println("Работа программы завершена")
}

/** Функция с передачей coroutine в канал */
suspend fun withChannel() = coroutineScope {
    val channel = Channel<Int>()
    println("Передаем данные в канал")
    launch {
        for (n in 1..5) {
            channel.send(n)
        }
        channel.close()
    }
    repeat(5) {
        val number = channel.receive()
        println("Получен $number элемент")
    }
    println("Данные из канала получены")
}

/** Функция для получения данных coroutine из канала ReceiveChannel */
suspend fun withReceiveChannel(users: List<String>) = coroutineScope {
    val users = getUsers(users)
    users.consumeEach { user -> println(user) }
    println("Данные из канала получены")
}

/** Функция для передачи coroutine в канал ReceiveChannel */
@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.getUsers(users: List<String>): ReceiveChannel<String> = produce {
    println("Передаем данные в канал")
    for (user in users) {
        send(user)
    }
}