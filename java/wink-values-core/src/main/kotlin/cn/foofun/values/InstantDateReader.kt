package cn.foofun.values

import cn.foofun.values.exceptions.ValuesException
import java.time.Instant

/**
 * shi
 */
interface InstantDateReader {

    fun getInstant(index: Int): Instant {
        return withInstant(index).value()
    }

    fun getInstant(path: String): Instant {
        try {
            return withInstant(path).value()
        } catch (valueEx: ValuesException) {
            throw ValuesException("因为${valueEx}，无法从${path}取得数据", valueEx)
        }
    }

    fun defaultInstantParser(): Parser<Instant>

    fun getInstant(index: Int, defaultValue: Instant): Instant {
        return getInstant(index, defaultInstantParser(), defaultValue)
    }

    fun getInstant(path: String, defaultValue: Instant): Instant {
        return getInstant(path, defaultInstantParser(), defaultValue)
    }

    fun getInstant(index: Int, parser: Parser<Instant>, defaultValue: Instant): Instant {
        val v = withInstant(index, parser)
        if (v.validate()) {
            return v.value()
        }
        return defaultValue
    }

    fun getInstant(path: String, parser: Parser<Instant>, defaultValue: Instant): Instant {
        val elementProperty = withInstant(path, parser)
        if (elementProperty.validate()) {
            return elementProperty.value()
        }
        return defaultValue
    }

    fun withInstant(index: Int): ElementProperty<Instant> {
        return withInstant(index, defaultInstantParser())
    }

    fun withInstant(index: Int, parser: Parser<Instant>): ElementProperty<Instant>

    fun withInstant(path: String): ElementProperty<Instant> {
        return withInstant(path, defaultInstantParser())
    }

    fun withInstant(path: String, parser: Parser<Instant>): ElementProperty<Instant>
}