package cn.foofun.values.parsers

import cn.foofun.values.ValuesErrors
import cn.foofun.values.errors.EmptyValuesErrors
import cn.foofun.values.errors.ValuesWithNoneErrors


/**
 * 必须不为空白，且防止特殊字符% ?的注入
 */
class SqlLikeParser : DefaultStringParser() {

    override fun parseString(obj: String): ValuesErrors<String> {
        var c = obj.replace("%", "")
        c = c.replace("?", "")
        c = c.trim()

        if (c.isEmpty()) {
            return EmptyValuesErrors(c)
        }

        return ValuesWithNoneErrors(c, obj)
    }
}