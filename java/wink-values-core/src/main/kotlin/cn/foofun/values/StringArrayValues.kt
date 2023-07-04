package cn.foofun.values

import cn.foofun.values.errors.NullValuesErrors
import cn.foofun.values.exceptions.ValueNotSupportException
import cn.foofun.values.parsers.DefaultBigDecimalParser
import cn.foofun.values.parsers.DefaultIntParser
import cn.foofun.values.parsers.DefaultLocalDateParser
import cn.foofun.values.parsers.DefaultStringParser
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 字符串数组适配成为Values
 */
class StringArrayValues(val stars: Array<String?>) : DefaultIndicesValues {

    override fun getValues(index: Int): Values? {
        throw ValueNotSupportException("不支持从字符串数组取得复合Value")
    }

    override fun withInt(index: Int, parser: Parser<Int>): ElementProperty<Int> {

        return object : DefaultElementProperty<Int>() {
            val str = stars[index]
            override fun validateInner(): ValuesErrors<Int> {
                if (str == null) {
                    return NullValuesErrors(index)
                }
                return parser.parseString(str)
            }
        }
    }

    override fun withString(index: Int, parser: Parser<String>): ElementProperty<String> {
        return object : DefaultElementProperty<String>() {
            val str = stars[index]
            override fun validateInner(): ValuesErrors<String> {
                if (str == null) {
                    return NullValuesErrors(index)
                }
                return parser.parseString(str)
            }
        }
    }

    override fun getValue(index: Int): Any? {
        return this.stars[index]
    }

    override fun indices(): Iterable<Int> {
        return 0 until this.stars.size
    }

    override fun withLocalDate(index: Int, parser: Parser<LocalDate>): ElementProperty<LocalDate> {
        return object : DefaultElementProperty<LocalDate>() {
            val str = stars[index]
            override fun validateInner(): ValuesErrors<LocalDate> {
                if (str == null) {
                    return NullValuesErrors(index)
                }
                return parser.parseString(str)
            }
        }
    }

    override fun withBigDecimal(index: Int, parser: Parser<BigDecimal>): ElementProperty<BigDecimal> {
        return object : DefaultElementProperty<BigDecimal>() {
            val str = stars[index]
            override fun validateInner(): ValuesErrors<BigDecimal> {
                if (str == null) {
                    return NullValuesErrors(index)
                }
                return parser.parseString(str)
            }
        }
    }

    override fun withInstant(index: Int, parser: Parser<Instant>): ElementProperty<Instant> {
        return object : DefaultElementProperty<Instant>() {
            val str = stars[index]
            override fun validateInner(): ValuesErrors<Instant> {
                if (str == null) {
                    return NullValuesErrors(index)
                }
                return parser.parseString(str)
            }
        }
    }

    override fun withLocalDateTime(index: Int, parser: Parser<LocalDateTime>): ElementProperty<LocalDateTime> {
        return object : DefaultElementProperty<LocalDateTime>() {
            val str = stars[index]
            override fun validateInner(): ValuesErrors<LocalDateTime> {
                if (str == null) {
                    return NullValuesErrors(index)
                }
                return parser.parseString(str)
            }
        }
    }
}
