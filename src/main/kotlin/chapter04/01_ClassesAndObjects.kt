package chapter04

import chapter04.packs.email.Message as EmailMessage
import chapter04.packs.email.send

fun classesAndObjects() {
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

    // Интерфейсы:
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

/** Интерфейс Printable с методом print */
interface Printable {
    fun print()
}

/** Класс ColoredPrinter, реализующий интерфейс Printable */
class ColoredPrinter: Printable {
    override fun print() {
        println("Цветная печать")
    }
}

/** Класс MonochromePrinter, реализующий интерфейс Printable */
class MonochromePrinter: Printable {
    override fun print() {
        println("Черно-белая печать")
    }
}