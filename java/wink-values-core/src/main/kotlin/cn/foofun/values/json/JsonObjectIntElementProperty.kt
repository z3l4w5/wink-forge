package cn.foofun.values.json

import cn.foofun.values.DefaultElementProperty
import cn.foofun.values.Parser
import cn.foofun.values.ValuesErrors
import cn.foofun.values.errors.NullValuesErrors
import com.alibaba.fastjson2.JSONObject

class JsonObjectIntElementProperty(
    private val jsonObject: JSONObject, private val path: String, private val parser: Parser<Int>
) : DefaultElementProperty<Int>() {

    override fun validateInner(): ValuesErrors<Int> {

        if (!this.jsonObject.containsKey(path)) {
            return NullValuesErrors(path)
        }

        val s = this.jsonObject.getIntValue(this.path)

        if (s == null) {
            return NullValuesErrors(path)
        }

        return this.parser.parseInt(s)
    }
}