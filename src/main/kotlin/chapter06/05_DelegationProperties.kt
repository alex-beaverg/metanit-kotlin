package chapter06

import kotlin.reflect.KProperty

fun delegationProperties() {
    // Делегированные свойства:
    val address = Address("Комсомольская", 15, 34)
    println(address)
    address.street = "Центральная"
    println(address)
}

/** Класс, получающий делегированное свойство */
class Address(streetDelegate: String, val house: Int, val flat: Int) {
    var street: String by LoggerStreetDelegate(streetDelegate)

    override fun toString(): String {
        return "Адрес: ул.$street, $house-$flat"
    }
}

/** Класс, делегирующий свойство */
class LoggerStreetDelegate(private var street: String) {
    operator fun getValue(thisRef: Address, property: KProperty<*>): String {
        println("Запрошено свойство адреса с именем \"${property.name}\" и значением \"$street\"")
        return street
    }

    operator fun setValue(thisRef: Address, property: KProperty<*>, value: String) {
        println("Установлено свойство адреса с именем \"${property.name}\" и значением \"$value\"")
        street = value
    }
}