package cn.foofun.values.exceptions

/**
 * 对复合Values执行不支持的操作
 */
class ValueNotSupportException : Exception {

    constructor(message: String) : super(message) {

    }

    constructor(message: String, cause: Exception) : super(message, cause) {

    }
}