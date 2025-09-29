package chapter02

// Константа на уровне компилляции:
const val constNumber = 500

fun variablesAndTypes() {
    // ПЕРЕМЕННЫЕ
    // Неизменяемая переменная:
    val immutableVar = 115
    println("Неизменяемая переменная: $immutableVar")
    // Изменяемая переменная:
    var mutableVar = 10
    println("Изменяемая переменная: $mutableVar, до изменения")
    mutableVar = 15
    println("Изменяемая переменная: $mutableVar, после изменения")
    // Константа на уровне компилляции:
    println("Константа на уровне компилляции: $constNumber")

    // ТИПЫ ДАННЫХ
    // ЦЕЛОЧИСЛЕННЫЕ ТИПЫ ДАННЫХ
    // Тип данных Byte (тип нужно обязательно объявить):
    val byteVariable: Byte = 127
    println("Переменная типа Byte: $byteVariable")
    // Тип данных Long, задаваемый без маркировки L (тип нужно обязательно объявить):
    val longVariableWithoutL: Long = -345
    println("Переменная типа Long: $longVariableWithoutL, заданная без маркировки L")
    // Тип данных Long, задаваемый с маркировкой L (тип объявлять не обязательно):
    val longVariableWithL = 421L
    println("Переменная типа Long: $longVariableWithL, заданная с маркировкой L")
    // Тип данных Short (тип нужно обязательно объявить):
    val shortVariable: Short = -1000
    println("Переменная типа Short: $shortVariable")
    // Тип данных Int (тип объявлять не обязательно):
    val intVariable = 155
    println("Переменная типа Int: $intVariable")
    // Тип данных UByte - положительный Byte (тип нужно обязательно объявить):
    val uByteVariable: UByte = 200U
    println("Переменная типа UByte - положительный Byte: $uByteVariable")
    // Тип данных ULong - положительный Long (тип нужно обязательно объявить):
    val uLongVariable: ULong = 3000U
    println("Переменная типа ULong - положительный Long: $uLongVariable")
    // Тип данных UInt - положительный Int (тип нужно обязательно объявить):
    val uIntVariable = 100U
    println("Переменная типа UInt - положительный Int: $uIntVariable")
    // Тип данных UShort - положительный Short (тип нужно обязательно объявить):
    val uShortVariable: UShort = 25U
    println("Переменная типа UShort - положительный Short: $uShortVariable")

    // ТИПЫ ДАННЫХ С ПЛАВАЮЩЕЙ ТОЧКОЙ
    // Тип данных Double, задаваемый стандартно (тип объявлять не обязательно):
    val doubleVariableBase = 1.89
    println("Переменная типа Double, задаваемая стандартно: $doubleVariableBase")
    // Тип данных Double, задаваемый экспоненциально (тип объявлять не обязательно):
    val doubleVariableExponent = 24e-4 // 0.0024
    println("Переменная типа Double, задаваемая экспоненциально: $doubleVariableExponent")
    // Тип данных Float (тип объявлять не обязательно):
    val floatVariable = 421.45F
    println("Переменная типа Float: $floatVariable")

    // ОСТАЛЬНЫЕ ТИПЫ ДАННЫХ
    // Тип данных Boolean (тип объявлять не обязательно):
    val booleanVariable = true
    println("Переменная типа Boolean: $booleanVariable")
    // Тип данных Char (тип объявлять не обязательно):
    val charVariable = 'A'
    println("Переменная типа Char: $charVariable")
    // Тип данных String, задаваемый двойными кавычками (тип объявлять не обязательно):
    val stringVariableDoubleQuotes = "Строка"
    println("Переменная типа String, задаваемая двойными кавычками: $stringVariableDoubleQuotes")
    // Тип данных String, задаваемый блоком в трех двойных кавычках (тип объявлять не обязательно):
    val stringVariableBlock = """
        Строка 1
            Строка 2"""
    println("Переменная типа String, задаваемая двойными кавычками: $stringVariableBlock")
    // Тип данных Any - неопределенный тип (тип нужно обязательно объявить):
    var anyVariable: Any = "Строка Any"
    println("Переменная типа Any, заданная как строка: $anyVariable")
    anyVariable = 100
    println("Переменная типа Any, переопределенная как целочисленный тип Int: $anyVariable")

    // ПРЕОБРАЗОВАНИЕ ТИПОВ
    val intVariableForTransform = 100
    println("Переменная типа Int, преобразованная в тип Long: ${intVariableForTransform.toLong()}")
    println("Переменная типа Int, преобразованная в тип Short: ${intVariableForTransform.toShort()}")
    println("Переменная типа Int, преобразованная в тип Byte: ${intVariableForTransform.toByte()}")
    println("Переменная типа Int, преобразованная в тип UInt: ${intVariableForTransform.toUInt()}")
    println("Переменная типа Int, преобразованная в тип ULong: ${intVariableForTransform.toULong()}")
    println("Переменная типа Int, преобразованная в тип UShort: ${intVariableForTransform.toUShort()}")
    println("Переменная типа Int, преобразованная в тип UByte: ${intVariableForTransform.toUByte()}")
    println("Переменная типа Int, преобразованная в тип Double: ${intVariableForTransform.toDouble()}")
    println("Переменная типа Int, преобразованная в тип Float: ${intVariableForTransform.toFloat()}")
}