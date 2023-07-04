package cn.foofun.values.parsers

import cn.foofun.values.ValuesErrors
import cn.foofun.values.errors.EmptyValuesErrors
import cn.foofun.values.errors.ValuesWithNoneErrors

/**
 * 非空Sql条件格式化
 */
class SqlWhereParser : DefaultStringParser() {

    override fun parseString(obj: String): ValuesErrors<String> {
        val s = obj.trim()
        if (s.isEmpty()) {
            return EmptyValuesErrors(obj)
        }

        return ValuesWithNoneErrors(s, obj)
    }
}