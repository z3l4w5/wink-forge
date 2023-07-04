package cn.foofun.values.parsers

import cn.foofun.values.Parser
import cn.foofun.values.ValuesErrors
import cn.foofun.values.errors.ValuesWithNoneErrors
import java.math.BigDecimal

open class DefaultStringParser : Parser<String> {

    override fun parseInt(obj: Int): ValuesErrors<String> {
        return ValuesWithNoneErrors(obj.toString(), obj)
    }

    override fun parseString(obj: String): ValuesErrors<String> {
        return ValuesWithNoneErrors(obj.trim(), obj)
    }

    override fun parseBigDecimal(obj: BigDecimal): ValuesErrors<String> {
        return ValuesWithNoneErrors(obj.toString(), obj)
    }

    override fun parseFloat(obj: Float): ValuesErrors<String> {
        return ValuesWithNoneErrors(obj.toString(), obj)
    }

    override fun parseDouble(obj: Double): ValuesErrors<String> {
        return ValuesWithNoneErrors(obj.toString(), obj)
    }
}