package cn.foofun.values.json

import cn.foofun.values.DefaultElementProperty
import cn.foofun.values.Parser
import cn.foofun.values.ValuesErrors
import cn.foofun.values.errors.ExceptionValuesErrors
import cn.foofun.values.errors.NullValuesErrors
import com.alibaba.fastjson2.JSONArray
import java.math.BigDecimal

class JsonArrayBigDecimalElementProperty(
    private val jsonArray: JSONArray,
    private val index: Int,
    private val parser: Parser<BigDecimal>
) :
    DefaultElementProperty<BigDecimal>() {
    override fun validateInner(): ValuesErrors<BigDecimal> {
        if (index < 0 || index >= this.jsonArray.size) {
            return NullValuesErrors(index)
        }

        return try {
            val s = jsonArray.getBigDecimal(index)
            this.parser.parseBigDecimal(s)
        } catch (ex: Exception) {
            ExceptionValuesErrors(jsonArray[index], ex)
        }
    }
}
