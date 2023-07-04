package cn.foofun.values

import cn.foofun.values.parsers.*
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime

interface DefaultIndicesValues : IndicesValues {

    override fun defaultIntParser(): Parser<Int> {
        return DefaultIntParser()
    }

    override fun defaultStringParser(): Parser<String> {
        return DefaultStringParser()
    }

    override fun defaultLocalDateParser(): Parser<LocalDate> {
        return DefaultLocalDateParser()
    }

    override fun defaultBigDecimalParser(): Parser<BigDecimal> {
        return DefaultBigDecimalParser()
    }

    override fun defaultInstantParser(): Parser<Instant> {
        return DefaultInstantParser()
    }

    override fun defaultLocalDateTimeParser(): Parser<LocalDateTime> {
        return defaultLocalDateTimeParser()
    }

    override fun withInt(index: Int, parser: Parser<Int>): ElementProperty<Int> {
        val v = this.getValue(index) ?: return NullElementProperty()

        return object : DefaultElementProperty<Int>() {
            override fun validateInner(): ValuesErrors<Int> {
                return parser.parseAny(v)
            }
        }
    }

    override fun withString(index: Int, parser: Parser<String>): ElementProperty<String> {
        val v = this.getValue(index) ?: return NullElementProperty()

        return object : DefaultElementProperty<String>() {
            override fun validateInner(): ValuesErrors<String> {
                return parser.parseAny(v)
            }
        }
    }

    override fun withLocalDate(index: Int, parser: Parser<LocalDate>): ElementProperty<LocalDate> {
        val v = this.getValue(index) ?: return NullElementProperty()

        return object : DefaultElementProperty<LocalDate>() {
            override fun validateInner(): ValuesErrors<LocalDate> {
                return parser.parseAny(v)
            }
        }
    }

    override fun withBigDecimal(index: Int, parser: Parser<BigDecimal>): ElementProperty<BigDecimal> {
        val v = this.getValue(index) ?: return NullElementProperty()

        return object : DefaultElementProperty<BigDecimal>() {
            override fun validateInner(): ValuesErrors<BigDecimal> {
                return parser.parseAny(v)
            }
        }
    }

    override fun withInstant(index: Int, parser: Parser<Instant>): ElementProperty<Instant> {
        val v = this.getValue(index) ?: return NullElementProperty()

        return object : DefaultElementProperty<Instant>() {
            override fun validateInner(): ValuesErrors<Instant> {
                return parser.parseAny(v)
            }
        }
    }

    override fun withLocalDateTime(index: Int, parser: Parser<LocalDateTime>): ElementProperty<LocalDateTime> {
        val v = this.getValue(index) ?: return NullElementProperty()

        return object : DefaultElementProperty<LocalDateTime>() {
            override fun validateInner(): ValuesErrors<LocalDateTime> {
                return parser.parseAny(v)
            }
        }
    }

}
