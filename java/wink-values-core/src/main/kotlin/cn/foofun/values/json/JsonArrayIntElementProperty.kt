package cn.foofun.values.json

import cn.foofun.values.DefaultElementProperty
import cn.foofun.values.ValuesErrors
import cn.foofun.values.errors.ExceptionValuesErrors
import com.alibaba.fastjson2.JSONArray

class JsonArrayIntElementProperty(
    private val jsonArray: JSONArray, private val index: Int, private val parser: cn.foofun.values.Parser<Int>
) : DefaultElementProperty<Int>() {

    override fun validateInner(): ValuesErrors<Int> {
        return try {
            val s = jsonArray.getIntValue(index)
            this.parser.parseInt(s)
        } catch (ex: Exception) {
            ExceptionValuesErrors(jsonArray[index], ex)
        }
    }
}