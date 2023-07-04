package cn.foofun.values.json

import cn.foofun.values.DefaultElementProperty
import cn.foofun.values.Parser
import cn.foofun.values.ValuesErrors
import cn.foofun.values.errors.ExceptionValuesErrors
import cn.foofun.values.errors.NullValuesErrors
import com.alibaba.fastjson2.JSONObject
import java.math.BigDecimal

class JsonObjectBigDecimalElementProperty(
    private val jsonObject: JSONObject, private val path: String, private val parser: Parser<BigDecimal>
) : DefaultElementProperty<BigDecimal>() {

    override fun validateInner(): ValuesErrors<BigDecimal> {

        if (!this.jsonObject.containsKey(path)) {
            return NullValuesErrors(path)
        }

        return try {
            val d = jsonObject.getBigDecimal(path)

            this.parser.parseBigDecimal(d)
        } catch (ex: Exception) {
            ExceptionValuesErrors(jsonObject.get(path), ex)
        }
    }
}