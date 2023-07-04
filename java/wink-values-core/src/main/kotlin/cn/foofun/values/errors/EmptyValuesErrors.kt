package cn.foofun.values.errors

import cn.foofun.values.ValuesErrors
import cn.foofun.values.exceptions.ValueNotSupportException
import cn.foofun.values.exceptions.ValuesException

/**
 * 零长度字符串等空白值错误
 */
class EmptyValuesErrors<T>(override val rawValue: Any?) : ValuesErrors<T> {

    override val value: T
        get() = throw ValueNotSupportException("${rawValue}是空白值")

    override val hasErrors: Boolean
        get() = true
}