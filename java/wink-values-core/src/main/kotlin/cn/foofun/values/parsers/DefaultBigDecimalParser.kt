package cn.foofun.values.parsers

import cn.foofun.values.Parser
import cn.foofun.values.ValuesErrors
import cn.foofun.values.errors.ExceptionValuesErrors
import cn.foofun.values.errors.ValuesWithNoneErrors
import java.math.BigDecimal

class DefaultBigDecimalParser : Parser<BigDecimal> {

    override fun parseBigDecimal(obj: BigDecimal): ValuesErrors<BigDecimal> {
        return try {
            ValuesWithNoneErrors(obj, obj)
        } catch (ex: Exception) {
            ExceptionValuesErrors(obj, ex)
        }
    }

    override fun parseFloat(obj: Float): ValuesErrors<BigDecimal> {
        return try {
            ValuesWithNoneErrors(obj.toBigDecimal(), obj)
        } catch (ex: Exception) {
            ExceptionValuesErrors(obj, ex)
        }
    }

    override fun parseDouble(obj: Double): ValuesErrors<BigDecimal> {
        return try {
            ValuesWithNoneErrors(obj.toBigDecimal(), obj)
        } catch (ex: Exception) {
            ExceptionValuesErrors(obj, ex)
        }
    }

    override fun parseInt(obj: Int): ValuesErrors<BigDecimal> {
        return try {
            ValuesWithNoneErrors(obj.toBigDecimal(), obj)
        } catch (ex: Exception) {
            ExceptionValuesErrors(obj, ex)
        }
    }

    override fun parseString(obj: String): ValuesErrors<BigDecimal> {
        return try {
            ValuesWithNoneErrors(obj.toBigDecimal(), obj)
        } catch (ex: Exception) {
            ExceptionValuesErrors(obj, ex)
        }
    }
}