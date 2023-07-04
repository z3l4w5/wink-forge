package cn.foofun.values

import cn.foofun.values.exceptions.ValueNotSupportException
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 无任何数据的值集合
 */
class EmptyValues : Values {
    override fun getValues(path: String): Values? {
        return null
    }

    override fun getValues(index: Int): Values? {
        return null
    }

    override fun getValue(path: String): Any? {
        return null
    }

    override fun getValue(index: Int): Any? {
        return null
    }

    override fun indices(): Iterable<Int> {
        return emptyList()
    }

    override fun paths(): Iterable<String> {
        return emptyList()
    }

    override fun defaultIntParser(): Parser<Int> {

        throw ValueNotSupportException("EmptyValues不支持")
    }

    override fun getString(path: String, defaultValue: String): String {
        return defaultValue
    }

    override fun withInt(index: Int, parser: Parser<Int>): ElementProperty<Int> {

        throw ValueNotSupportException("EmptyValues不支持")
    }

    override fun withInt(path: String, parser: Parser<Int>): ElementProperty<Int> {
        throw ValueNotSupportException("EmptyValues不支持")
    }

    override fun defaultStringParser(): Parser<String> {
        throw ValueNotSupportException("EmptyValues不支持")
    }

    override fun withString(index: Int, parser: Parser<String>): ElementProperty<String> {
        throw ValueNotSupportException("EmptyValues不支持")
    }

    override fun withString(path: String, parser: Parser<String>): ElementProperty<String> {
        throw ValueNotSupportException("EmptyValues不支持")
    }

    override fun defaultLocalDateTimeParser(): Parser<LocalDateTime> {
        throw ValueNotSupportException("EmptyValues不支持")
    }

    override fun withLocalDateTime(index: Int, parser: Parser<LocalDateTime>): ElementProperty<LocalDateTime> {

        throw ValueNotSupportException("EmptyValues不支持")
    }

    override fun withLocalDateTime(path: String, parser: Parser<LocalDateTime>): ElementProperty<LocalDateTime> {

        throw ValueNotSupportException("EmptyValues不支持")
    }

    override fun defaultLocalDateParser(): Parser<LocalDate> {
        throw ValueNotSupportException("EmptyValues不支持")
    }

    override fun withLocalDate(index: Int, parser: Parser<LocalDate>): ElementProperty<LocalDate> {
        throw ValueNotSupportException("EmptyValues不支持")
    }

    override fun withLocalDate(path: String, parser: Parser<LocalDate>): ElementProperty<LocalDate> {
        throw ValueNotSupportException("EmptyValues不支持")
    }

    override fun defaultInstantParser(): Parser<Instant> {
        throw ValueNotSupportException("EmptyValues不支持")
    }

    override fun withInstant(index: Int, parser: Parser<Instant>): ElementProperty<Instant> {
        throw ValueNotSupportException("EmptyValues不支持")
    }

    override fun withInstant(path: String, parser: Parser<Instant>): ElementProperty<Instant> {
        throw ValueNotSupportException("EmptyValues不支持")
    }

    override fun defaultBigDecimalParser(): Parser<BigDecimal> {
        throw ValueNotSupportException("EmptyValues不支持")
    }

    override fun withBigDecimal(index: Int, parser: Parser<BigDecimal>): ElementProperty<BigDecimal> {
        throw ValueNotSupportException("EmptyValues不支持")
    }

    override fun withBigDecimal(path: String, parser: Parser<BigDecimal>): ElementProperty<BigDecimal> {
        throw ValueNotSupportException("EmptyValues不支持")
    }
}
