package cn.foofun.values.errors

import cn.foofun.values.ValuesErrors
import cn.foofun.values.exceptions.ValueNotSupportException

/**
 * 空null数据错误
 */
class NullValuesErrors<T> : ValuesErrors<T> {

    override val rawValue: Any?
        get() = null

    override val value: T
        get() = throw ValueNotSupportException("null不是有效的数据")

    override val hasErrors: Boolean
        get() = true

    var path: String? = null

    constructor(path: String) {
        this.path = path
    }

    var index: Int? = null

    constructor(index: Int) {
        this.index = index
    }

    override fun toString(): String {
        if (this.path != null) {
            return "从${path}取到的是null"
        }
        if (this.index == null) {
            return "从${index}取到的是null"
        }

        return "取到null错误"
    }
}