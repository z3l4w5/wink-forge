package cn.foofun.values

import cn.foofun.values.exceptions.ValueNotSupportException
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 只支持index:Int索引的Values
 */
interface IndicesValues : Values {

    override fun getValues(path: String): Values? {

        throw ValueNotSupportException("不支持取得${path}下的Values")
    }

    override fun getValue(path: String): Any? {

        throw ValueNotSupportException("不支持取得${path}下的Value")
    }

    override fun paths(): Iterable<String> {

        throw ValueNotSupportException("不支持取得路径索引")
    }

    override fun withInt(path: String, parser: Parser<Int>): ElementProperty<Int> {

        throw ValueNotSupportException("不支持取得${path}下的Int")
    }

    override fun withString(path: String, parser: Parser<String>): ElementProperty<String> {

        throw ValueNotSupportException("不支持取得${path}下的String")
    }

    override fun withLocalDate(path: String, parser: Parser<LocalDate>): ElementProperty<LocalDate> {

        throw ValueNotSupportException("不支持取得${path}下的LocalDate")
    }

    override fun withBigDecimal(path: String, parser: Parser<BigDecimal>): ElementProperty<BigDecimal> {

        throw ValueNotSupportException("不支持取得${path}下的BigDecimal")
    }

    override fun withInstant(path: String, parser: Parser<Instant>): ElementProperty<Instant> {

        throw ValueNotSupportException("不支持取得${path}下的Instant")
    }

    override fun withLocalDateTime(path: String, parser: Parser<LocalDateTime>): ElementProperty<LocalDateTime> {

        throw ValueNotSupportException("不支持取得${path}下的LocalDateTime")
    }

}
