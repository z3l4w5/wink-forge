package cn.foofun.values

import cn.foofun.values.exceptions.ValueNotSupportException
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * 只支持path:String取值的Values
 */
interface PathsValues : Values {

    override fun getValue(index: Int): Any? {

        throw ValueNotSupportException("不支持取得${index}下的Value")
    }

    override fun getValues(index: Int): Values? {

        throw ValueNotSupportException("不支持取得${index}下的Values")
    }

    override fun indices(): Iterable<Int> {

        throw ValueNotSupportException("不支持取得数字索引")
    }

    override fun withInt(index: Int, parser: Parser<Int>): ElementProperty<Int> {
        throw ValueNotSupportException("不支持从${index}取得Int")
    }

    override fun withString(index: Int, parser: Parser<String>): ElementProperty<String> {
        throw ValueNotSupportException("不支持从${index}取得String")
    }


    override fun withLocalDate(index: Int, parser: Parser<LocalDate>): ElementProperty<LocalDate> {
        throw ValueNotSupportException("不支持从${index}取得LocalDate")
    }

    override fun withBigDecimal(index: Int, parser: Parser<BigDecimal>): ElementProperty<BigDecimal> {
        throw ValueNotSupportException("不支持从${index}取得BigDecimal")
    }

    override fun withInstant(index: Int, parser: Parser<Instant>): ElementProperty<Instant> {
        throw ValueNotSupportException("不支持从${index}取得Instant")
    }

    override fun withLocalDateTime(index: Int, parser: Parser<LocalDateTime>): ElementProperty<LocalDateTime> {
        throw ValueNotSupportException("不支持取得${index}下的LocalDateTime")
    }
}
