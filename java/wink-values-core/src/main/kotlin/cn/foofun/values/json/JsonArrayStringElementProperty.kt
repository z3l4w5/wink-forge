package cn.foofun.values.json

import cn.foofun.values.DefaultElementProperty
import cn.foofun.values.ValuesErrors
import cn.foofun.values.errors.NullValuesErrors
import com.alibaba.fastjson2.JSONArray

class JsonArrayStringElementProperty(
    private val jsonArray: JSONArray, private val index: Int, private val parser: cn.foofun.values.Parser<String>
) : DefaultElementProperty<String>() {

    override fun validateInner(): ValuesErrors<String> {

        val s = jsonArray.getString(index) ?: return NullValuesErrors(index)

        return this.parser.parseString(s)
    }
}