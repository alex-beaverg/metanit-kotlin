package chapter02

fun numberOperations() {
    // ОПЕРАЦИИ С ЧИСЛАМИ
    // Сложение:
    var numberA = 100
    val numberB = 6
    val numberC = 6.0
    val addition = numberA + numberB
    println("Результат суммы чисел 100 и 6: $addition")
    // Вычитание:
    val subtraction = numberA - numberB
    println("Результат разности чисел 100 и 6: $subtraction")
    // Умножение:
    val multiplication = numberA * numberB
    println("Результат умножения чисел 100 и 6: $multiplication")
    // Деление целых чисел:
    val divisionInteger = numberA / numberB
    println("Результат деления целых чисел 100 и 6: $divisionInteger")
    // Деление дробных чисел:
    val divisionDouble = numberA / numberC
    println("Результат деления дробных чисел 100 и 6.0: $divisionDouble")
    // Остаток целочисленного деления:
    val divisionTail = numberA % numberB
    println("Остаток деления целых чисел 100 и 6: $divisionTail")
    // Результат инкремента:
    println("Результат инкремента ++100 в момент операции: ${++numberA}")
    println("Результат инкремента 101++ в момент операции: ${numberA++}")
    println("Результат инкремента 101++ после операции: $numberA")
    // Результат декремента:
    println("Результат декремента --102 в момент операции: ${--numberA}")
    println("Результат декремента 101-- в момент операции: ${numberA--}")
    println("Результат декремента 101-- после операции: $numberA")
    // Присвоение со сложением:
    numberA += numberB
    println("Результат присвоения со сложением 100 и 6: $numberA")
    // Присвоение с вычитанием:
    numberA -= numberB
    println("Результат присвоения с вычитанием 106 и 6: $numberA")
    // Присвоение с умножением:
    numberA *= numberB
    println("Результат присвоения с умножением 100 и 6: $numberA")
    // Присвоение с делением:
    numberA /= numberB
    println("Результат присвоения с делением 600 и 6: $numberA")
    // Присвоение с делением:
    numberA %= numberB
    println("Результат присвоения с остатком от деления 100 и 6: $numberA")
    // Отношения:
    val randomVariable = (Math.random() * 10).toInt()
    println("Результат отношения 4 > $randomVariable: ${numberA > randomVariable}")
    println("Результат отношения 4 <= $randomVariable: ${numberA <= randomVariable}")
    println("Результат отношения 4 == $randomVariable: ${numberA == randomVariable}")
    println("Результат отношения 4 != $randomVariable: ${numberA != randomVariable}")
}