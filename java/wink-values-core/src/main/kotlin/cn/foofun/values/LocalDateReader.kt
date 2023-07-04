package cn.foofun.values

import cn.foofun.values.exceptions.ValuesException
import java.time.LocalDate

/**
 * 读取LocalDate 只有年月日日期
 */
interface LocalDateReader {

    fun getLocalDate(index: Int): LocalDate {
        return withLocalDate(index).value()
    }

    fun getLocalDate(path: String): LocalDate {
        try {
            return withLocalDate(path).value()
        } catch (valueEx: ValuesException) {
            throw ValuesException("因为${valueEx}，无法从${path}取得数据", valueEx)
        }
    }

    fun defaultLocalDateParser(): Parser<LocalDate>

    fun getLocalDate(index: Int, defaultValue: LocalDate): LocalDate {
        return getLocalDate(index, defaultLocalDateParser(), defaultValue)
    }

    fun getLocalDate(path: String, defaultValue: LocalDate): LocalDate {
        return getLocalDate(path, defaultLocalDateParser(), defaultValue)
    }

    fun getLocalDate(index: Int, parser: Parser<LocalDate>, defaultValue: LocalDate): LocalDate {
        val v = withLocalDate(index, parser)
        if (v.validate()) {
            return v.value()
        }
        return defaultValue
    }

    fun getLocalDate(path: String, parser: Parser<LocalDate>, defaultValue: LocalDate): LocalDate {
        val elementProperty = withLocalDate(path, parser)
        if (elementProperty.validate()) {
            return elementProperty.value()
        }
        return defaultValue
    }

    fun withLocalDate(index: Int): ElementProperty<LocalDate> {
        return withLocalDate(index, defaultLocalDateParser())
    }

    fun withLocalDate(index: Int, parser: Parser<LocalDate>): ElementProperty<LocalDate>

    fun withLocalDate(path: String): ElementProperty<LocalDate> {
        return withLocalDate(path, defaultLocalDateParser())
    }

    fun withLocalDate(path: String, parser: Parser<LocalDate>): ElementProperty<LocalDate>
}