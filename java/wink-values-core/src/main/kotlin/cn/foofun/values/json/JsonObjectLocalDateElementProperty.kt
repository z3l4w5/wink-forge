package cn.foofun.values.json

import cn.foofun.values.DefaultElementProperty
import cn.foofun.values.Parser
import cn.foofun.values.ValuesErrors
import cn.foofun.values.errors.NullValuesErrors
import com.alibaba.fastjson2.JSONObject
import java.time.LocalDate

class JsonObjectLocalDateElementProperty(
    private val jsonObject: JSONObject, private val path: String, private val parser: Parser<LocalDate>
) : DefaultElementProperty<LocalDate>() {

    override fun validateInner(): ValuesErrors<LocalDate> {
        if (!this.jsonObject.containsKey(path)) {
            return NullValuesErrors(path)
        }

        // TODO 数字日期
        val s = this.jsonObject.getString(path)

        if (s == null) {
            return NullValuesErrors(path)
        }

        return this.parser.parseString(s)
    }
}