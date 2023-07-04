package cn.foofun.spring.values

import cn.foofun.values.*
import cn.foofun.values.errors.NullValuesErrors
import org.springframework.web.context.request.WebRequest
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime

class WebRequestValues(private val webRequest: WebRequest) : DefaultPathValues {

    override fun getValue(path: String): Any? {
        val values = this.webRequest.getParameterValues(path)
        return if (values == null) {
            null
        } else if (values.size == 1) {
            values[0]
        } else {
            values
        }
    }

    override fun getValues(path: String): Values? {
        val strs: Array<String?> = webRequest.getParameterValues(path) ?: return null

        return StringArrayValues(strs)
    }

    override fun paths(): Iterable<String> {
        return webRequest.parameterMap.keys
    }

    override fun withInt(path: String, parser: Parser<Int>): ElementProperty<Int> {
        val str = webRequest.getParameter(path)

        return object : DefaultElementProperty<Int>() {
            override fun validateInner(): ValuesErrors<Int> {
                if (str == null) {
                    return NullValuesErrors(path)
                }

                return parser.parseString(str)
            }
        }
    }

    override fun withString(path: String, parser: Parser<String>): ElementProperty<String> {
        val str = webRequest.getParameter(path)

        return object : DefaultElementProperty<String>() {
            override fun validateInner(): ValuesErrors<String> {
                if (str == null) {
                    return NullValuesErrors(path)
                }

                return parser.parseString(str)
            }
        }
    }

    override fun withLocalDate(path: String, parser: Parser<LocalDate>): ElementProperty<LocalDate> {
        val str = webRequest.getParameter(path)

        return object : DefaultElementProperty<LocalDate>() {
            override fun validateInner(): ValuesErrors<LocalDate> {
                if (str == null) {
                    return NullValuesErrors(path)
                }

                return parser.parseString(str)
            }
        }
    }

    override fun withBigDecimal(path: String, parser: Parser<BigDecimal>): ElementProperty<BigDecimal> {
        val str = webRequest.getParameter(path)

        return object : DefaultElementProperty<BigDecimal>() {
            override fun validateInner(): ValuesErrors<BigDecimal> {
                if (str == null) {
                    return NullValuesErrors(path)
                }

                return parser.parseString(str)
            }
        }
    }

    override fun withInstant(path: String, parser: Parser<Instant>): ElementProperty<Instant> {
        val str = webRequest.getParameter(path)

        return object : DefaultElementProperty<Instant>() {
            override fun validateInner(): ValuesErrors<Instant> {
                if (str == null) {
                    return NullValuesErrors(path)
                }

                return parser.parseString(str)
            }
        }
    }

    override fun withLocalDateTime(path: String, parser: Parser<LocalDateTime>): ElementProperty<LocalDateTime> {
        val str = webRequest.getParameter(path)

        return object : DefaultElementProperty<LocalDateTime>() {
            override fun validateInner(): ValuesErrors<LocalDateTime> {
                if (str == null) {
                    return NullValuesErrors(path)
                }

                return parser.parseString(str)
            }
        }
    }
}
