package cn.foofun.values.parsers

import cn.foofun.values.Parser
import cn.foofun.values.ValuesErrors
import cn.foofun.values.errors.ExceptionValuesErrors
import cn.foofun.values.errors.ValuesWithNoneErrors
import java.math.BigDecimal
import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

class DefaultInstantParser : Parser<Instant> {
    override fun parseInt(obj: Int): ValuesErrors<Instant> {
        TODO("Not yet implemented")
    }

    override fun parseString(obj: String): ValuesErrors<Instant> {
        try {
            val instant = when (obj.length) {
                10 -> {
                    // yyyy-MM-dd 2023-01-06
                    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

                    LocalDate.parse(obj, dateFormatter.withZone(ZoneId.systemDefault()).withLocale(Locale.CHINA))
                        .atStartOfDay().toInstant(ZoneOffset.UTC)
                }

                19 -> {
                    // yyyy-MM-dd HH:mm:ss 2023-01-06 16:00:00
                    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    dateTimeFormatter.parse(obj, Instant::from)
                }

                20 -> {
                    // 2023-01-06T16:00:00Z T代表后面跟着是时间，Z代表0时区（相差北京时间8小时）
                    val utcDDateTimeFormatter20 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                    utcDDateTimeFormatter20.parse(obj, Instant::from)

                    // val dateFormatter = utcDDateTimeFormatter20.withZone(ZoneId.of("UTC"))
                    // 转换时区
                    // ZonedDateTime.parse(obj, dateFormatter).withZoneSameInstant(ZoneId.systemDefault()).toLocalDate()
                }

                23 -> {
                    // 2023-01-24T10:22:18.722
                    val utcDDateTimeFormatter23 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
                    utcDDateTimeFormatter23.parse(obj, Instant::from)
                }

                24 -> {
                    // 2023-01-10T16:00:00.000Z T代表后面跟着是时间，Z代表0时区（相差北京时间8小时）
                    val dateFormatter =
                        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("UTC"))
                    dateFormatter.parse(obj, Instant::from)
                    // 转换时区
                    // ZonedDateTime.parse(obj, dateFormatter).withZoneSameInstant(ZoneId.systemDefault()).toLocalDate()
                }

                else -> null
            }
            return if (instant == null) {
                ExceptionValuesErrors(obj, DateTimeParseException("无法转换日期", obj, 0))
            } else ValuesWithNoneErrors(instant, obj)
        } catch (ex: Exception) {
            return ExceptionValuesErrors(obj, ex)
        }
    }

    override fun parseBigDecimal(obj: BigDecimal): ValuesErrors<Instant> {
        TODO("Not yet implemented")
    }

    override fun parseFloat(obj: Float): ValuesErrors<Instant> {
        TODO("Not yet implemented")
    }

    override fun parseDouble(obj: Double): ValuesErrors<Instant> {
        TODO("Not yet implemented")
    }
}
