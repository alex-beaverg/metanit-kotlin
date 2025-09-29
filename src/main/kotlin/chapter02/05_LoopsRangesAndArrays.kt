package chapter02

fun loopsRangesAndArrays() {
    var randomIntValue = (Math.random() * 10).toInt()

    // ЦИКЛЫ
    // Обычный цикл:
    for (i in 1..3) {
        println("Проход по обычному циклу в $i-й раз")
    }
    // Вложенный цикл:
    for (i in 1..2) {
        println("Проход по $i-му кругу внешнего цикла")
        for (j in 1..2) {
            println("\t- проход по $j-му кругу внутреннего цикла")
        }
    }
    // Цикл while:
    var valueForLoops = 3
    while (valueForLoops > 0) {
        println("Проход по циклу while пока $valueForLoops > 0")
        valueForLoops--
    }
    // Цикл do-while:
    do {
        println("Проход по циклу do-while пока $valueForLoops < 3")
        valueForLoops++
    } while(valueForLoops < 3)
    // Использование оператора continue:
    for (i in 1..3) {
        if (i == 2) continue
        println("Проход по $i-му кругу обычного цикла если это не 2-й круг")
    }
    // Использование оператора break:
    for (i in 1..3) {
        println("Проход по $i-му кругу внешнего цикла")
        for (j in 1..3) {
            if (i == 2 && j == 2) break
            println("\t- проход по $j-му кругу внутреннего цикла до 2-го круга внутреннего цикла во 2-м круге внешнего цикла с переходом на следующий круг внешнего цикла")
        }
    }
    // Использование метки для указания оператору break цикла, в котором прерываемся:
    OuterLoop@ for (i in 1..3) {
        println("Проход по $i-му кругу внешнего цикла")
        for (j in 1..3) {
            if (i == 2 && j == 2) break@OuterLoop
            println("\t- проход по $j-му кругу внутреннего цикла до 2-го круга внутреннего цикла во 2-м круге внешнего цикла с выходом из внешнего цикла")
        }
    }

    // ДИАПАЗОНЫ
    // Прямой числовой диапазон:
    val range1to5 = 1..5
    println("Число $randomIntValue находится в диапазоне [1, 5]? - ${randomIntValue in range1to5}")
    for (item in range1to5) println("Число $item диапазона [1, 5]")
    // Прямой символьный диапазон:
    val rangeAtoE = 'A'..'E'
    println("Буква 'K' находится в диапазоне [A, E]? - ${'K' in rangeAtoE}")
    for (item in rangeAtoE) println("Буква $item диапазона [A, E]")
    // Обратный числовой диапазон:
    val range5to1 = 5 downTo 1
    for (item in range5to1) println("Число $item диапазона [5, 1]")
    // Прямой числовой диапазон с шагом 2:
    val range1to9step2 = 1..9 step 2
    for (item in range1to9step2) println("Число $item диапазона [1, 9] с шагом 2")
    // Прямой числовой диапазон до указанного числа (не включительно):
    val range1until7 = 1 until 7
    for (item in range1until7) println("Число $item диапазона [1, 7)")

    // МАССИВЫ
    // Массив с указанием типа данных:
    val array1to3: Array<Int> = arrayOf(1, 2, 3)
    for (item in array1to3) println("Число $item массива [1, 2, 3]")
    // Массив без указания типа данных:
    val array4to6 = arrayOf(4.1, 5.2, 6.3)
    for (item in array4to6) println("Число $item массива [4.1, 5.2, 6.3]")
    // Массив из значений null:
    val arrayNulls = arrayOfNulls<Int>(3)
    for (item in arrayNulls) println("Число $item массива [null, null, null]")
    // Массив из одинаковых значений:
    val arrayOnly5 = Array(3) { 5 }
    for (item in arrayOnly5) println("Число $item массива [5, 5, 5]")
    // Массив из зависимо рассчитанных значений:
    val arrayIncrementMultiply = Array(3) { randomIntValue++ * 2 }
    for (item in arrayIncrementMultiply) println("Число $item некоего массива")
    // Массив целых чисел:
    for (item in intArrayOf(1, 2, 3)) println("Число $item массива [1, 2, 3]")
    // Массив Double чисел:
    for (item in doubleArrayOf(2.4, 4.5, 1.2)) println("Число $item массива [2.4, 4.5, 1.2]")
}