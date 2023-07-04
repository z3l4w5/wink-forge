package cn.foofun.values

import java.math.BigDecimal

interface BigDecimalReader {

    fun getBigDecimal(index: Int): BigDecimal {
        return withBigDecimal(index).value()
    }

    fun getBigDecimal(path: String): BigDecimal {
        return withBigDecimal(path).value()
    }

    fun defaultBigDecimalParser(): Parser<BigDecimal>

    fun getBigDecimal(index: Int, defaultValue: BigDecimal): BigDecimal {
        return getBigDecimal(index, defaultBigDecimalParser(), defaultValue)
    }

    fun getBigDecimal(path: String, defaultValue: BigDecimal): BigDecimal {
        return getBigDecimal(path, defaultBigDecimalParser(), defaultValue)
    }

    fun getBigDecimal(index: Int, parser: Parser<BigDecimal>, defaultValue: BigDecimal): BigDecimal {
        val v = withBigDecimal(index, parser)
        if (v.validate()) {
            return v.value()
        }
        return defaultValue
    }

    fun getBigDecimal(path: String, parser: Parser<BigDecimal>, defaultValue: BigDecimal): BigDecimal {
        val v = withBigDecimal(path, parser)
        if (v.validate()) {
            return v.value()
        }
        return defaultValue
    }

    fun withBigDecimal(index: Int): ElementProperty<BigDecimal> {
        return withBigDecimal(index, defaultBigDecimalParser())
    }

    fun withBigDecimal(index: Int, parser: Parser<BigDecimal>): ElementProperty<BigDecimal>

    fun withBigDecimal(path: String): ElementProperty<BigDecimal> {
        return withBigDecimal(path, defaultBigDecimalParser())
    }

    fun withBigDecimal(path: String, parser: Parser<BigDecimal>): ElementProperty<BigDecimal>
}