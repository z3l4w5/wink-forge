package cn.foofun.values

import cn.foofun.values.parsers.*
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime

interface DefaultPathValues : PathsValues {

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
        return DefaultLocalDateTimeParser()
    }


    override fun withInt(path: String, parser: Parser<Int>): ElementProperty<Int> {
        val v = this.getValue(path) ?: return NullElementProperty()

        return object : DefaultElementProperty<Int>() {
            override fun validateInner(): ValuesErrors<Int> {
                return parser.parseAny(v)
            }
        }
    }

    override fun withString(path: String, parser: Parser<String>): ElementProperty<String> {
        val v = this.getValue(path) ?: return NullElementProperty()

        return object : DefaultElementProperty<String>() {
            override fun validateInner(): ValuesErrors<String> {
                return parser.parseAny(v)
            }
        }
    }

    override fun withLocalDate(path: String, parser: Parser<LocalDate>): ElementProperty<LocalDate> {
        val v = this.getValue(path) ?: return NullElementProperty()

        return object : DefaultElementProperty<LocalDate>() {
            override fun validateInner(): ValuesErrors<LocalDate> {
                return parser.parseAny(v)
            }
        }
    }

    override fun withBigDecimal(path: String, parser: Parser<BigDecimal>): ElementProperty<BigDecimal> {
        val v = this.getValue(path) ?: return NullElementProperty()

        return object : DefaultElementProperty<BigDecimal>() {
            override fun validateInner(): ValuesErrors<BigDecimal> {
                return parser.parseAny(v)
            }
        }
    }

    override fun withInstant(path: String, parser: Parser<Instant>): ElementProperty<Instant> {
        val v = this.getValue(path) ?: return NullElementProperty()

        return object : DefaultElementProperty<Instant>() {
            override fun validateInner(): ValuesErrors<Instant> {
                return parser.parseAny(v)
            }
        }
    }

    override fun withLocalDateTime(path: String, parser: Parser<LocalDateTime>): ElementProperty<LocalDateTime> {
        val v = this.getValue(path) ?: return NullElementProperty()

        return object : DefaultElementProperty<LocalDateTime>() {
            override fun validateInner(): ValuesErrors<LocalDateTime> {
                return parser.parseAny(v)
            }
        }
    }
}
