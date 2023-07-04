package cn.foofun.values.parsers

import cn.foofun.values.ValuesErrors
import cn.foofun.values.errors.EmptyValuesErrors
import cn.foofun.values.errors.ValuesWithNoneErrors


/**
 * 必须不为空白,去掉前后空白
 */
class SqlStringEqualParser : DefaultStringParser() {

    override fun parseString(obj: String): ValuesErrors<String> {
        val s = obj.trim()

        if (s.isEmpty()) {
            return EmptyValuesErrors(s)
        }

        return ValuesWithNoneErrors(s, obj)
    }
}