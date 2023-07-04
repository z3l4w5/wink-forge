package cn.foofun.values.errors

import cn.foofun.values.ValuesErrors
import cn.foofun.values.exceptions.ValueNotSupportException

/**
 * 转换发送异常的取值错误
 */
class ExceptionValuesErrors<T>(override val rawValue: Any?, private val exception: Exception) : ValuesErrors<T> {

    override val value: T
        get() = throw ValueNotSupportException("处理${rawValue}发生错误", exception)

    override val hasErrors: Boolean
        get() = true
}