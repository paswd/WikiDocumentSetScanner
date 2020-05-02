package ru.paswd.infosearch.wikidocumentsetscanner

import com.fasterxml.jackson.databind.ObjectMapper
import ru.paswd.infosearch.wikidocumentsetscanner.dto.DataSet
import java.io.FileInputStream

fun main(attrs: Array<String>) {

    if (attrs.isEmpty()) {
        println("ERROR: json file path not set")
        return
    }

    val mapper = ObjectMapper()

    println("Открываю файл \"${attrs[0]}\"")
    val input = FileInputStream(attrs[0])
    val dataSet = mapper.readValue(input, DataSet::class.java)

    var sourceSize = 0L
    var textSize = 0L
    var count = 0L

    dataSet.data?.stream()?.
        filter { it?.title != null && it.pageId != null && it.text != null } ?.
        forEach {
            count++

//            println("Progress: $count / ${dataSet?.total}")

            val source = mapper.writeValueAsString(it)
            sourceSize += source.length
            textSize += it.text?.length ?: 0
        }

    if ((dataSet?.total ?: 0L) == count)
        println("[OK] Размер корпуса документов корректен")
    else
        println("[ERROR] Некорректный размер корпуса документов")

    println("===")
    println("Текста всего: ${getPrintableSize(textSize)}")
    println("Средний размер документа: ${sourceSize / count} Б")
    println("Средний объём текста в документе: ${textSize / count} Б")
}

fun getPrintableSize(bytes: Long): String {
    var tmp = bytes.toFloat()
    var count = 0

    while (tmp >= 1024 && count < 5) {
        tmp /= 1024
        count++
    }

    return when (count) {
        0 -> "$bytes Б"
        1 -> "${round(tmp, 2)} КБ"
        2 -> "${round(tmp, 2)} МБ"
        3 -> "${round(tmp, 2)} ГБ"
        else -> "${round(tmp, 2)} ТБ"
    }
}

fun round(num: Float, signs: Int): Float {
    val long = (num * signs).toLong()
    return long.toFloat() / signs
}