package cn.foofun.values.exceptions

import cn.foofun.values.ValuesErrors

/**
 * 校验失败的取值操作
 */
class ValuesException : Exception {

    val errors: ValuesErrors<*>

    constructor(errors: ValuesErrors<*>) : super("原值${errors.rawValue}存在错误") {
        this.errors = errors
    }

    constructor(message: String, errors: ValuesErrors<*>) : super(message) {
        this.errors = errors
    }

    constructor(message: String?, cause: ValuesException) : super(message, cause) {
        this.errors = cause.errors
    }
}