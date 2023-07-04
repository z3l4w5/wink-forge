package cn.foofun.values

import java.time.LocalDateTime

interface LocalDateTimeReader {

    fun getLocalDateTime(index: Int): LocalDateTime {
        return withLocalDateTime(index).value()
    }

    fun getLocalDateTime(path: String): LocalDateTime {
        return withLocalDateTime(path).value()
    }

    fun defaultLocalDateTimeParser(): Parser<LocalDateTime>

    fun getLocalDateTime(index: Int, defaultValue: LocalDateTime): LocalDateTime {
        return getLocalDateTime(index, defaultLocalDateTimeParser(), defaultValue)
    }

    fun getLocalDateTime(path: String, defaultValue: LocalDateTime): LocalDateTime {
        return getLocalDateTime(path, defaultLocalDateTimeParser(), defaultValue)
    }

    fun getLocalDateTime(index: Int, parser: Parser<LocalDateTime>, defaultValue: LocalDateTime): LocalDateTime {
        val v = withLocalDateTime(index, parser)
        if (v.validate()) {
            return v.value()
        }
        return defaultValue
    }

    fun getLocalDateTime(path: String, parser: Parser<LocalDateTime>, defaultValue: LocalDateTime): LocalDateTime {
        val v = withLocalDateTime(path, parser)
        if (v.validate()) {
            return v.value()
        }
        return defaultValue
    }

    fun withLocalDateTime(index: Int): ElementProperty<LocalDateTime> {
        return withLocalDateTime(index, defaultLocalDateTimeParser())
    }

    fun withLocalDateTime(index: Int, parser: Parser<LocalDateTime>): ElementProperty<LocalDateTime>

    fun withLocalDateTime(path: String): ElementProperty<LocalDateTime> {
        return withLocalDateTime(path, defaultLocalDateTimeParser())
    }

    fun withLocalDateTime(path: String, parser: Parser<LocalDateTime>): ElementProperty<LocalDateTime>
}
