package chapter06

import java.util.Date

fun scopeFunctions() {
    // Scope-ФУНКЦИИ:
    // let (если в блок кода let попадает null - блок не выполняется - хорошая проверка на null):
    (if (Date().time % 2 == 0L) null else "Hello")?.let {
        println("Длина слова, если это не null: ${it.length}")
        println("Само слово, если это не null: $it")
    }
    // with:
    val withList = with(mutableListOf("one", "two", "three")) {
        println("Первый элемент списка: ${first()}")
        println("Последний элемент списка: ${last()}")
        println("Перечислим все элементы списка внутри 'with':")
        for (element in this) {
            println("\t- $element")
        }
        add("four")
        println("Последний элемент увеличенного списка: ${last()}")
        this
    }
    println("Перечислим все элементы списка после изменений в 'with':")
    for (element in withList) {
        println("\t- $element")
    }
    // run (то же самое, что и with, но вызывается как let):
    val runList = mutableListOf("1", "2", "3").run {
        println("Первый элемент списка: ${first()}")
        println("Последний элемент списка: ${last()}")
        println("Перечислим все элементы списка внутри 'run':")
        for (element in this) {
            println("\t- $element")
        }
        add("4")
        println("Последний элемент увеличенного списка: ${last()}")
        this
    }
    println("Перечислим все элементы списка после изменений в 'run':")
    for (element in runList) {
        println("\t- $element")
    }
    // apply (применяется когда надо изменить свойства объекта):
    val applyList = mutableListOf("100", "200", "300")
    applyList.apply {
        set(0, "1000")
        set(2, "2000")
    }
    println("Перечислим все элементы списка после изменений в 'apply':")
    for (element in applyList) {
        println("\t- $element")
    }
    // also:
    val alsoList = mutableListOf("Сто", "Двести", "Триста")
    alsoList.also {
        println("В этом объекте вот столько элементов: ${it.size} шт.")
        println("Сейчас добавим еще один")
    }.add("Четыреста").also {
        println("Был ли добавлен элемент? - $it")
    }
}