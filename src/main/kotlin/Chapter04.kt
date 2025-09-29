import email.send
import email.Message as EmailMessage

fun main() {
    // Классы и объекты
    val person = Person()
    println("${person.name} with age ${person.age}") // Name with age 35
    person.name = "Alex"
    println("${person.name} with age ${person.age}") // Alex with age 35
    person.sayHello()
    person.sayAbout()
    Person("John", 40).sayHello().sayAbout()
    val newPerson = NewPerson("Dima", 22)
    println(newPerson.name)
    println(newPerson.age)
    newPerson.printInfo()
    println()

    // Пакеты
    val myMessage = EmailMessage("New Message")
    send(myMessage, "mail@gmail.com")
    println()

    // Наследование
    val employee = Employee("Ivan", 33, "Engineer")
    employee.printInfo()
    println(employee.id)
    println()

    // Абстрактные классы
    val tesla = ElectricCar("Model S", 2020)
    println(tesla.type)
    tesla.about()
    val mustang = ClassicCar("Ford Mustang", 1972)
    println(mustang.type)
    mustang.about()
    println()

    // Интерфейсы
    ColoredPrinter().print()
    MonochromePrinter().print()
    fun doPrint(obj: Printable) = obj.print()
    doPrint(ColoredPrinter())
    doPrint(MonochromePrinter())
}

class Person {
    var name: String = "Name"
    var age: Int = 35

    constructor()

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }

    fun sayHello(): Person {
        println("Hello, my name is $name")
        return this
    }

    fun sayAbout(): Person {
        println("I'm $age")
        return this
    }
}

open class NewPerson (var name: String, var age: Int) {

    open fun printInfo() {
        println("Hello, my name is $name. I'm $age")
    }
}

@Suppress
class Employee(empName: String, empAge: Int, var empPos: String): NewPerson(empName, empAge) {
    private val _id = empName.hashCode() + empAge.hashCode() + empPos.hashCode()
    val id: Int
        get() = _id

    override fun printInfo() {
        super.printInfo()
        println("I'm $empPos and my ID = $_id")
    }
}

abstract class Car() {
    abstract val model: String
    abstract val year: Int

    abstract fun about()
}

class ElectricCar(override val model: String, override val year: Int): Car() {
    val type = "Electric"

    override fun about() {
        println("This is an $type car called $model $year")
        println("This car is moving quietly")
    }
}

class ClassicCar(override val model: String, override val year: Int): Car() {
    val type = "Classic"

    override fun about() {
        println("This is a $type car with classic engine called $model $year")
        println("This car is moving loudly!!!")
    }
}

interface Printable {
    fun print()
}

class ColoredPrinter: Printable {
    override fun print() {
        println("Цветная печать")
    }
}

class MonochromePrinter: Printable {
    override fun print() {
        println("Черно-белая печать")
    }
}