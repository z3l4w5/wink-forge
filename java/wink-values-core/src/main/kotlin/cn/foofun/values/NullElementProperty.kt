package cn.foofun.values

import cn.foofun.values.errors.NullValuesErrors
import cn.foofun.values.exceptions.ValueNotSupportException

/**
 * null值
 */
class NullElementProperty<T> : ElementProperty<T> {

    override fun validate(): Boolean {
        return false
    }

    override fun value(): T {
        throw ValueNotSupportException("null不是有效的数据")
    }

    override fun errors(): ValuesErrors<T>? {
        return NullValuesErrors("值为null")
    }
}