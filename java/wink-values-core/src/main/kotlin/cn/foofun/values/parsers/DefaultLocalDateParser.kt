package cn.foofun.values.parsers

import cn.foofun.values.Parser
import cn.foofun.values.ValuesErrors
import cn.foofun.values.errors.ExceptionValuesErrors
import cn.foofun.values.errors.ValuesWithNoneErrors
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


class DefaultLocalDateParser : Parser<LocalDate> {


    override fun parseInt(obj: Int): ValuesErrors<LocalDate> {
        TODO("Not yet implemented")
    }

    override fun parseString(obj: String): ValuesErrors<LocalDate> {
        try {

            val localDate = when (obj.length) {
                10 -> {
                    // yyyy-MM-dd 2023-01-06
                    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    LocalDate.parse(obj, dateFormatter)
                }

                19 -> {
                    // yyyy-MM-dd HH:mm:ss 2023-01-06 16:00:00
                    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    LocalDateTime.parse(obj, dateTimeFormatter).toLocalDate()
                }

                20 -> {
                    // 2023-01-06T16:00:00Z T代表后面跟着是时间，Z代表0时区（相差北京时间8小时）
                    val utcDDateTimeFormatter20 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
                    val dateFormatter = utcDDateTimeFormatter20.withZone(ZoneId.of("UTC"))
                    // 转换时区
                    ZonedDateTime.parse(obj, dateFormatter).withZoneSameInstant(ZoneId.systemDefault()).toLocalDate()
                }

                22 -> {
                    // 2023-01-24T10:22:18.722
                    val utcDDateTimeFormatter22 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS")
                    LocalDateTime.parse(obj, utcDDateTimeFormatter22).toLocalDate()
                }

                23 -> {
                    // 2023-01-24T10:22:18.722
                    val utcDDateTimeFormatter23 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
                    LocalDateTime.parse(obj, utcDDateTimeFormatter23).toLocalDate()
                }

                24 -> {
                    // 2023-01-10T16:00:00.000Z T代表后面跟着是时间，Z代表0时区（相差北京时间8小时）
                    val dateFormatter =
                        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("UTC"))
                    // 转换时区
                    ZonedDateTime.parse(obj, dateFormatter).withZoneSameInstant(ZoneId.systemDefault()).toLocalDate()
                }

                else -> null
            }
            return if (localDate == null) {
                ExceptionValuesErrors(obj, DateTimeParseException("无法转换日期", obj, 0))
            } else ValuesWithNoneErrors(localDate, obj)
        } catch (ex: Exception) {
            return ExceptionValuesErrors(obj, ex)
        }
    }

    override fun parseBigDecimal(obj: BigDecimal): ValuesErrors<LocalDate> {
        TODO("Not yet implemented")
    }

    override fun parseFloat(obj: Float): ValuesErrors<LocalDate> {
        TODO("Not yet implemented")
    }

    override fun parseDouble(obj: Double): ValuesErrors<LocalDate> {
        TODO("Not yet implemented")
    }
}