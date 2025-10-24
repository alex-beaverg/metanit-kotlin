package chapter07

fun operations() {
    // Операции с Iterable, расширенно:
    // ФИЛЬТРАЦИЯ:
    // По условию:
    val list = arrayListOf("list", "map", "sequence", "set", "array")
    println("Отфильтрованный список со строками в 3 символа: ${list.filter { it.length == 3 }}")
    println("Отфильтрованный список со строками не в 3 символа: ${list.filterNot { it.length == 3 }}")
    // По индексу:
    println("Отфильтрованный список по индексам и условию: ${list.filterIndexed { index, item -> index % 2 != 0 && item.length == 3 }}")
    // По типу:
    val objectList = arrayListOf(Animal("Животное в вакууме"), Cat("Кот Барсик"), Cat("Кот Васька"))
    println("Отфильтрованный список по типу, только коты: ${objectList.filterIsInstance<Cat>()}")
    // По null:
    val withNullList = arrayListOf(Animal("Животное в вакууме"), null, Cat("Кот Барсик"), null, Cat("Кот Васька"), null)
    println("Отфильтрованный список по null: ${withNullList.filterNotNull()}")

    // ПРОВЕРКА ЭЛЕМЕНТОВ:
    println("Все ли элементы длиной строго больше 3 символов: ${list.all { it.length > 3 }}")
    println("Хотя бы один элемент длиной строго больше 5 символов: ${list.any { it.length > 5 }}")
    println("Ни один элемент длиной строго больше 10 символов: ${list.none { it.length > 10 }}")
    println("Коллекция содержит хотя бы один элемент: ${list.any()}")
    println("Коллекция не содержит элементов: ${list.none()}")
    println("Коллекция содержит хотя бы один элемент \"set\": ${list.contains("set")}")
    val smallList = arrayListOf("map", "set")
    println("Коллекция содержит все элементы другой коллекции: ${list.containsAll(smallList)}")

    // ТРАНСФОРМАЦИИ:
    println("Возвращаем имена котов: ${objectList.map { it.name }}")
    println("Возвращаем имена котов с индексами + 1 элементов: ${objectList.mapIndexed { index, item -> "${index + 1}. ${item.name}" }}")
    println("Возвращаем имена котов, исключая результаты null: ${objectList.mapNotNull { if (it.name.length == 10) null else it.name }}")
    val listOfLists = arrayListOf(listOf(1, 2, 3), listOf(5, 3))
    println("Объединяем коллекции: ${listOfLists.flatten()}")

    // ГРУППИРОВКА:
    val employees = arrayListOf(
        Employee("Дима", "Займы", 25),
        Employee("Саша", "Финансовые инновации", 30),
        Employee("Леша", "ДП-Core", 27),
        Employee("Лена", "Займы", 32),
        Employee("Таня", "ДП-Core", 23),
        Employee("Вадим", "ДП-Core", 23)
    )
    println("Группируем в карту с сотрудниками по отделам: ${employees.groupBy { it.department }}")
    println("Группируем в карту с именами сотрудников по отделам: ${employees.groupBy({ it.department }) { it.name }}")

    // СОРТИРОВКА:
    println("Сортируем работников по имени: ${employees.sorted()}")
    println(
        "Сортируем работников по отделу с компаратором: ${
            employees.sortedWith(Comparator { e1: Employee, e2: Employee ->
                e1.department.compareTo(
                    e2.department
                )
            })
        }"
    )
    println("Сортируем работников по возрасту с компаратором (более просто): ${employees.sortedWith(compareBy { it.age })}")
    println("Сортируем работников по отделу (критерий): ${employees.sortedBy { it.department }}")
    println("Сортируем работников по имени в обратном направлении (критерий): ${employees.sortedByDescending { it.name }}")
    println("Развернем работников: ${employees.reversed()}")
    println("Перемешаем работников: ${employees.shuffled()}")

    // АГРЕГАТНЫЕ ОПЕРАЦИИ:
    val numberList = arrayListOf(1, 23, 43, 80, 5)
    val emptyList = arrayListOf<Int>()
    println("Максимальное значение: ${numberList.max()}")
    println("Максимальное значение или null при пустом списке: ${emptyList.maxOrNull()}")
    println("Минимальное значение: ${numberList.min()}")
    println("Минимальное значение или null при пустом списке: ${emptyList.minOrNull()}")
    println("Минимальный возраст у работника по имени ${employees.minByOrNull { it.age }}")
    println("Минимальный возраст работника равен ${employees.minOfOrNull { it.age }}")
    println("Максимальный возраст (с компаратором) у работника по имени ${employees.maxWithOrNull(compareBy { it.age })}")
    println(
        "Максимальная длина имени (с компаратором и селектором) у работника по имени ${
            employees.maxOfWithOrNull(
                compareBy { it.length }) { it.name }
        }"
    )
    println("Среднее значение: ${numberList.average()}")
    println("Сумма: ${numberList.sum()}")
    println("Количество элементов: ${numberList.count()}")
    println("Сведение к значению: ${numberList.reduce { a, b -> a * b }}")
    println("Сведение к значению с начальным значением: ${numberList.fold(100) { a, b -> a + b }}")

    // СЛОЖЕНИЕ, ВЫЧИТАНИЕ И ОБЪЕДИНЕНИЕ:

}

/** Класс для наследования */
open class Animal(val name: String) {
    override fun toString(): String = "Это $name"
}

/** Класс наследник */
class Cat(name: String) : Animal(name)

/** Класс для группировки и сортировки */
class Employee(val name: String, val department: String, val age: Int) : Comparable<Employee> {
    override fun toString(): String {
        return name
    }

    override fun compareTo(other: Employee): Int = name.compareTo(other.name)
}