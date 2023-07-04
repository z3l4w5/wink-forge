package cn.foofun.values

import cn.foofun.values.errors.ExceptionValuesErrors
import cn.foofun.values.exceptions.ValueNotSupportException
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

/**
 * 数据类型以及特定格式转换
 */
interface Parser<T> {

    fun parseInt(obj: Int): ValuesErrors<T>

    fun parseString(obj: String): ValuesErrors<T>

    fun parseAny(obj: Any): ValuesErrors<T> {
        return when (obj) {
            is Int -> this.parseInt(obj)
            is String -> this.parseString(obj)
            is Float -> this.parseFloat(obj)
            is BigDecimal -> this.parseBigDecimal(obj)
            is Double -> this.parseDouble(obj)
            is Long -> this.parseLong(obj)
            is Short -> this.parseShort(obj)
            is Byte -> this.parseByte(obj)
            is Boolean -> this.parseBoolean(obj)
            is Date -> this.parseDate(obj)
            is Instant -> this.parseInstant(obj)
            is LocalDate -> this.parseLocalDate(obj)
            is LocalDateTime -> this.parseLocalDateTime(obj)
            is Char -> this.parseChar(obj)
            else -> ExceptionValuesErrors(obj, ValueNotSupportException("不支持的数据类型${obj::class.java}"))
        }
    }

    fun parseBigDecimal(obj: BigDecimal): ValuesErrors<T>
    fun parseFloat(obj: Float): ValuesErrors<T>
    fun parseDouble(obj: Double): ValuesErrors<T>
    fun parseLong(obj: Long): ValuesErrors<T> {
        TODO()
    }

    fun parseShort(obj: Short): ValuesErrors<T> {
        TODO()
    }

    fun parseByte(obj: Byte): ValuesErrors<T> {
        TODO()
    }

    fun parseBoolean(obj: Boolean): ValuesErrors<T> {
        TODO()
    }

    fun parseDate(obj: Date): ValuesErrors<T> {
        TODO()
    }

    fun parseInstant(obj: Instant): ValuesErrors<T> {
        TODO()
    }

    fun parseLocalDate(obj: LocalDate): ValuesErrors<T> {
        TODO()
    }

    fun parseLocalDateTime(obj: LocalDateTime): ValuesErrors<T> {
        TODO()
    }

    fun parseChar(obj: Char): ValuesErrors<T> {
        TODO()
    }
}