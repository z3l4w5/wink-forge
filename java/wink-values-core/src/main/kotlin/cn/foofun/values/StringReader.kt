package cn.foofun.values

import cn.foofun.values.exceptions.ValuesException

interface StringReader {
    fun getString(index: Int): String {
        return withString(index).value()
    }

    fun getString(path: String): String {
        try {
            return withString(path).value()
        } catch (valueEx: ValuesException) {
            throw ValuesException("因为${valueEx}，无法从${path}取得数据", valueEx)
        }
    }

    fun defaultStringParser(): Parser<String>

    fun getString(index: Int, defaultValue: String): String {
        return getString(index, defaultStringParser(), defaultValue)
    }

    fun getString(path: String, defaultValue: String): String {
        return getString(path, defaultStringParser(), defaultValue)
    }

    fun getString(index: Int, parser: Parser<String>, defaultValue: String): String {
        val v = withString(index, parser)
        if (v.validate()) {
            return v.value()
        }
        return defaultValue
    }

    fun getString(path: String, parser: Parser<String>, defaultValue: String): String {
        val elementProperty = withString(path, parser)
        if (elementProperty.validate()) {
            return elementProperty.value()
        }
        return defaultValue
    }

    fun withString(index: Int): ElementProperty<String> {
        return withString(index, defaultStringParser())
    }

    fun withString(index: Int, parser: Parser<String>): ElementProperty<String>

    fun withString(path: String): ElementProperty<String> {
        return withString(path, defaultStringParser())
    }

    fun withString(path: String, parser: Parser<String>): ElementProperty<String>
}