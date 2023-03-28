package cn.foofun.pour

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

interface PrimitiveValue : Value {

    fun hasValue(): Boolean

    fun withLong(format: String): ElementProperty<Long>

    fun withString(format: String): ElementProperty<String>

    fun withInteger(format: String): ElementProperty<Int>

    fun withFloat(format: String): ElementProperty<Float>

    fun withBigDecimal(format: String): ElementProperty<BigDecimal>

    fun withBoolean(format: String): ElementProperty<Boolean>

    fun withLocalDate(format: String): ElementProperty<LocalDate>

    fun withLocalDateTime(format: String): ElementProperty<LocalDateTime>
}