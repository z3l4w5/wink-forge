package cn.foofun.values.parsers

import cn.foofun.values.Parser
import cn.foofun.values.ValuesErrors
import cn.foofun.values.errors.ExceptionValuesErrors
import cn.foofun.values.errors.ValuesWithNoneErrors
import java.math.BigDecimal

open class DefaultIntParser : Parser<Int> {

    override fun parseInt(obj: Int): ValuesErrors<Int> {
        return ValuesWithNoneErrors(obj, obj)
    }

    override fun parseString(obj: String): ValuesErrors<Int> {
        return try {
            ValuesWithNoneErrors(obj.toInt(), obj)
        } catch (ex: Exception) {
            ExceptionValuesErrors(obj, ex)
        }
    }

    override fun parseBigDecimal(obj: BigDecimal): ValuesErrors<Int> {
        return try {
            ValuesWithNoneErrors(obj.toInt(), obj)
        } catch (ex: Exception) {
            ExceptionValuesErrors(obj, ex)
        }
    }

    override fun parseFloat(obj: Float): ValuesErrors<Int> {
        return try {
            ValuesWithNoneErrors(obj.toInt(), obj)
        } catch (ex: Exception) {
            ExceptionValuesErrors(obj, ex)
        }
    }

    override fun parseDouble(obj: Double): ValuesErrors<Int> {
        return try {
            ValuesWithNoneErrors(obj.toInt(), obj)
        } catch (ex: Exception) {
            ExceptionValuesErrors(obj, ex)
        }
    }
}