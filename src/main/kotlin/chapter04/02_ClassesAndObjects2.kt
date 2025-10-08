package chapter04

import kotlin.math.floor

fun classesAndObjects2() {
    // Вложенные (nested) классы (НЕ ИМЕЮТ доступ к внешнему классу):
    CardOwner.Card("VISA", "4444555566667777").printCardDetails()
    CardOwnerPrivate("Иван Иванов", "MC", "8888222244447777").printCardDetails()
    // Вложенные интерфейсы:
    CardOwnerWithInterface("Михаил Михайлов", "VISA", "2222888811113333").printCardDetails()
    // Внутренние (inner) классы (ИМЕЮТ доступ к внешнему классу) с учетом совпадения имен переменных:
    CardOwnerWithInnerClass("Василий Васильев", 542).Card("MC", "6666111177773333", 815).printCardDetails()

    // ДАТА-КЛАССЫ
    val card = Card("VISA", "4444111100002222")
    // Переопределение метода toString:
    println(card.toString())
    // Копирование данных:
    println(card.copy(cardType = "MasterCard").toString())
    // Декомпозиция:
    val (cardType, cardNumber) = card
    println("Результат декомпозиции: тип карты - $cardType, номер карты - $cardNumber")

    // ПЕРЕЧИСЛЕНИЯ (enums)
    // Базовые детали:
    println("Позиция тестировщика: ${Position.TESTER}")
    println("Уровень джуниор: ${Grade.JUNIOR.level}")
    println("Результат расчета населения г.Минска в процентном отношении ко всему населению РБ: ${City.MINSK.getPercent()}%")
    println("Результат расчета населения г.Бреста в процентном отношении ко всему населению РБ: ${City.BREST.getPercent()}%")
    println("Позиция разработчика: ${Position.DEVELOPER.name}")
    println("Уровень средний, номер константы: ${Grade.MIDDLE.ordinal}")
    println("Объект перечисления: ${Position.valueOf("ANALYST")}")
    for (grade in Grade.entries) {
        println(grade)
    }
    // Абстрактные методы класса-перечисления:
    for (answer in YesOrNo.entries) {
        answer.printAnswer()
    }
    // Интерфейсы, реализуемые классом-перечислением:
    for (answer in YesOrNo.entries) {
        answer.printFullAnswer()
    }

    // ДЕЛЕГИРОВАНИЕ
    // Базовая реализация:
    val connectModule = ConnectModule("Net Connector")
    val device = Device(connectModule)
    device.connect("Wi-Fi: DP-GUEST")
    // Множественное делегирование:
    val gpsModule = GPSModule()
    val newDevice = NewDevice(connectModule, gpsModule)
    newDevice.connect("Wi-Fi: DP-MASTER")
    newDevice.findLocation()
}

/** Класс CardOwner с публичным вложенным классом */
class CardOwner {
    class Card(val cardType: String, val cardNumber: String) {
        fun printCardDetails() {
            println("Данные карты: $cardType-$cardNumber")
        }
    }
}

/** Класс CardOwnerPrivate с приватным вложенным классом */
class CardOwnerPrivate(val fullName: String, cardType: String, cardNumber: String) {
    private val card: Card = Card(cardType, cardNumber)

    private class Card(val cardType: String, val cardNumber: String)

    fun printCardDetails() {
        println("Владелец карты: $fullName")
        println("\t- данные карты: ${card.cardType}-${card.cardNumber}")
    }
}

/** Класс CardOwnerWithInterface с приватным вложенным классом и интерфейсом */
class CardOwnerWithInterface(val fullName: String, cardType: String, cardNumber: String) {
    private val card: Card = Card(cardType, cardNumber)

    private interface ICheck {
        fun check()
    }

    private class Card(val cardType: String, val cardNumber: String) : ICheck {
        override fun check() {
            println("Проверка номера карты $cardNumber прошла успешно")
        }
    }

    fun printCardDetails() {
        card.check()
        println("Владелец карты: $fullName")
        println("\t- данные карты: ${card.cardType}-${card.cardNumber}")
    }
}

/** Класс CardOwnerWithInnerClass с приватным внутренним классом */
class CardOwnerWithInnerClass(val fullName: String, val id: Int) {
    inner class Card(val cardType: String, val cardNumber: String, val id: Int) {
        fun printCardDetails() {
            println("Владелец карты: $fullName, ClientID=${this@CardOwnerWithInnerClass.id}")
            println("\t- данные карты: $cardType-$cardNumber, CardID=${this@Card.id}")
        }
    }
}

/** Дата-класс Card с переопределением метода toString */
data class Card(val cardType: String, val cardNumber: String) {
    override fun toString(): String {
        return "{\n\t\"Card\": {\n\t\t\"cardType\": \"$cardType\",\n\t\t\"cardNumber\": \"$cardNumber\"\n\t}\n}"
    }
}

/** Класс-перечисление Position без конструктора */
enum class Position {
    TESTER, DEVELOPER, ANALYST
}

/** Класс-перечисление Grade с конструктором */
enum class Grade(val level: Int) {
    JUNIOR(1), MIDDLE(2), SENIOR(3)
}

/** Класс-перечисление City с конструктором и методом */
enum class City(val population: Int) {
    MINSK(1_997_000), BREST(346_000);

    fun getPercent(): Double {
        return floor((population / 9_134_000.0) * 10000) / 100
    }
}

/** Интерфейс IPrintFullAnswer с одним методом */
interface IPrintFullAnswer {
    fun printFullAnswer()
}

/** Класс-перечисление YesOrNo с абстрактным методом и реализующим интерфейс */
enum class YesOrNo: IPrintFullAnswer {
    YES {
        override fun printAnswer() {
            println("ДА!")
        }

        override fun printFullAnswer() {
            println("Ну конечно же ДА!")
        }
    },
    NO {
        override fun printAnswer() {
            println("НЕТ!")
        }

        override fun printFullAnswer() {
            println("Ну конечно же НЕТ!")
        }
    };

    abstract fun printAnswer()
}

/** Интерфейс IConnection с одним методом */
interface IConnection {
    fun connect(netName: String)
}

/** Класс ConnectModule, реализующий интерфейс IConnection */
class ConnectModule(val settingName: String): IConnection {
    override fun connect(netName: String) {
        println("Подключение к сети '$netName' через настройку '$settingName'")
    }
}

/** Интерфейс ILocation с одним методом */
interface ILocation {
    fun findLocation()
}

/** Класс GPSModule, реализующий интерфейс ILocation */
class GPSModule(): ILocation {
    override fun findLocation() {
        println("Местоположение определено")
    }
}

/** Класс Device с делегированием метода connect объекту "c" */
class Device(c: IConnection): IConnection by c

/** Класс NewDevice с множественным делегированием метода connect объекту "c" и метода findLocation объекту "l" */
class NewDevice(c: IConnection, l: ILocation): IConnection by c, ILocation by l