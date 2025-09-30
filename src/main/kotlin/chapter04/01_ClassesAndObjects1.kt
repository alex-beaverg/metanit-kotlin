package chapter04

import chapter04.packs.email.Message as EmailMessage
import chapter04.packs.email.send

fun classesAndObjects1() {
    // Классы и объекты:
    val person = Person()
    println("Результат запроса данных по персоне: ${person.name} в возрасте ${person.age}")
    person.name = "Алексей"
    println("Результат запроса данных по персоне после обновления имени: ${person.name} в возрасте ${person.age}")
    person.sayHello()
    person.sayAbout()
    Person("Саша", 40).sayHello().sayAbout()
    val newPerson = NewPerson("Дима", 22)
    println("Результат запроса данных по новой персоне: ${newPerson.name} в возрасте ${newPerson.age}")
    newPerson.printInfo()

    // Пакеты:
    val myMessage = EmailMessage("Это новое сообщение")
    send(myMessage, "mail@gmail.com")

    // Наследование:
    val employee = Employee("Иван", 33, "Инженер-тестировщик")
    employee.printInfo()
    println("Результат запроса идентификатора работника: ${employee.id}")

    // Абстрактные классы:
    val tesla = ElectricCar("Tesla Model S", 2020)
    println("Результат запроса типа машины: ${tesla.type}")
    tesla.about()
    val mustang = ClassicCar("Ford Mustang", 1972)
    println("Результат запроса типа машины: ${mustang.type}")
    mustang.about()

    // ИНТЕРФЕЙСЫ
    // Варианты использования реализованного интерфейса:
    ColoredPrinter().print()
    MonochromePrinter().print()
    fun doPrint(obj: Printable) = obj.print()
    doPrint(ColoredPrinter())
    doPrint(MonochromePrinter())
    val coloredPrinter: Printable = ColoredPrinter()
    coloredPrinter.print()
    val monochromePrinter: Printable = MonochromePrinter()
    monochromePrinter.print()
    // Множественная реализация интерфейсов:
    val commonMonochromePrinter = MonochromePrinter()
    commonMonochromePrinter.print().laminate()
    // Реализация методов по умолчанию:
    commonMonochromePrinter.print().laminate().cut()
    // Реализация свойств:
    val commonColoredPrinter = ColoredPrinter()
    commonColoredPrinter.printWithBrightness(115u)
    // Реализация свойств через конструктор:
    commonMonochromePrinter.printWithBrightness(57u)
    // Ромбовидное наследование интерфейсов решается единой реализацией всех методов с одним названием:
    commonColoredPrinter.print().checkResult()
    commonMonochromePrinter.print().laminate().checkResult().cut()
}

/** Класс Person с полями, конструкторами и методами */
class Person {
    var name: String = "Иван"
    var age: Int = 35

    constructor()

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }

    fun sayHello(): Person {
        println("Привет, меня зовут $name")
        return this
    }

    fun sayAbout(): Person {
        println("Мне $age")
        return this
    }
}

/** Родительский класс NewPerson с методом */
open class NewPerson(var name: String, var age: Int) {
    open fun printInfo() {
        println("Привет, меня зовут $name. Мне $age")
    }
}

/** Наследный класс Employee от класса NewPerson с переопределенным методом */
@Suppress
class Employee(empName: String, empAge: Int, var empPos: String): NewPerson(empName, empAge) {
    private val _id = empName.hashCode() + empAge.hashCode() + empPos.hashCode()
    val id: Int
        get() = _id

    override fun printInfo() {
        super.printInfo()
        println("Я $empPos и мой идентификатор в системе = $_id")
    }
}

/** Абстрактный класс Car с полями и методом */
abstract class Car() {
    abstract val model: String
    abstract val year: Int

    abstract fun about()
}

/** Наследный класс ElectricCar от абстрактного класса Car с переопределенным методом */
class ElectricCar(override val model: String, override val year: Int): Car() {
    val type = "Электрическая"

    override fun about() {
        println("Это $type машина модели $model и $year года выпуска")
        println("Эта машина передвигается тихо")
    }
}

/** Наследный класс ClassicCar от абстрактного класса Car с переопределенным методом */
class ClassicCar(override val model: String, override val year: Int): Car() {
    val type = "Классическая"

    override fun about() {
        println("Это $type машина с классическим ДВС модели $model и $year года выпуска")
        println("Эта машина передвигается громко!!!")
    }
}

/** Интерфейс Printable с методами print и printWithBrightness */
interface Printable {
    var brightness: UByte

    fun print(): Printable

    fun printWithBrightness(brightness: UByte): Printable

    fun checkResult(): Printable {
        println("Проверка качества печати")
        return this
    }
}

/** Интерфейс ILaminate с методами laminate и cut */
interface ILaminate {
    fun laminate(): ILaminate

    fun cut(): ILaminate {
        println("Обрезка по формату")
        return this
    }

    fun checkResult(): ILaminate {
        println("Проверка качества ламинирования")
        return this
    }
}

/** Класс ColoredPrinter, реализующий интерфейс Printable */
class ColoredPrinter: Printable {
    override var brightness: UByte = 100u

    override fun print(): ColoredPrinter {
        println("Цветная печать")
        return this
    }

    override fun printWithBrightness(brightness: UByte): ColoredPrinter {
        if (brightness <= 100u) {
            this.brightness = brightness
        }
        println("Цветная печать с яркостью ${this.brightness}")
        return this
    }
}

/** Класс MonochromePrinter, реализующий интерфейсы Printable и ILaminate */
class MonochromePrinter(override var brightness: UByte = 100u): Printable, ILaminate {
    override fun print(): MonochromePrinter {
        println("Черно-белая печать")
        return this
    }

    override fun printWithBrightness(brightness: UByte): MonochromePrinter {
        if (brightness <= 100u) {
            this.brightness = brightness
        }
        println("Черно-белая печать с яркостью ${this.brightness}")
        return this
    }

    override fun laminate(): MonochromePrinter {
        println("Ламинирование")
        return this
    }

    override fun checkResult(): MonochromePrinter {
        super<Printable>.checkResult()
        super<ILaminate>.checkResult()
        return this
    }
}