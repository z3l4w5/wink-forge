package cn.foofun.values.errors

import cn.foofun.values.ValuesErrors

/**
 * 正确，无错Values
 */
class ValuesWithNoneErrors<T>(private val v: T, override val rawValue: Any?) : ValuesErrors<T> {

    /**
     * 成功返回值
     */
    override val value: T
        get() = v

    /**
     * 无错永远返回false
     */
    override val hasErrors: Boolean
        get() = false
}
