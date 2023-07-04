package cn.foofun.values.json

import cn.foofun.values.*
import cn.foofun.values.errors.ExceptionValuesErrors
import cn.foofun.values.errors.NullValuesErrors
import cn.foofun.values.errors.ValuesWithNoneErrors
import cn.foofun.values.exceptions.ValueNotSupportException
import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime

class JsonObjectValues(private val jsonObject: JSONObject) : DefaultPathValues {

    override fun getValue(path: String): Any? {
        return this.jsonObject.get(path)
    }

    override fun getValues(path: String): Values? {
        if (!jsonObject.containsKey(path)) {
            return null
        }

        val obj = jsonObject.get(path)

        if (obj is JSONObject) {
            return JsonObjectValues(obj)
        } else if (obj is JSONArray) {
            return JsonArrayValues(obj)
        }

        throw ValueNotSupportException("期望从${path}取得${jsonObject}内的复合Value，但是结果为$obj")
    }

    override fun paths(): Iterable<String> {

        return jsonObject.keys
    }

    override fun withInt(path: String, parser: Parser<Int>): ElementProperty<Int> {
        return JsonObjectIntElementProperty(jsonObject, path, parser)
    }

    override fun withString(path: String, parser: Parser<String>): ElementProperty<String> {
        return JsonObjectStringElementProperty(jsonObject, path, parser)
    }

    override fun withLocalDate(path: String, parser: Parser<LocalDate>): ElementProperty<LocalDate> {
        return JsonObjectLocalDateElementProperty(jsonObject, path, parser)
    }

    override fun withBigDecimal(path: String, parser: Parser<BigDecimal>): ElementProperty<BigDecimal> {
        return JsonObjectBigDecimalElementProperty(jsonObject, path, parser)
    }

    override fun withInstant(path: String, parser: Parser<Instant>): ElementProperty<Instant> {
        return object : DefaultElementProperty<Instant>() {
            override fun validateInner(): ValuesErrors<Instant> {

                if (!jsonObject.containsKey(path)) {
                    return NullValuesErrors(path)
                }

                return try {
                    val d = jsonObject.getInstant(path)
                    ValuesWithNoneErrors(d, jsonObject.get(path))
                } catch (ex: Exception) {
                    ExceptionValuesErrors(jsonObject.get(path), ex)
                }
            }
        }
    }

    override fun withLocalDateTime(path: String, parser: Parser<LocalDateTime>): ElementProperty<LocalDateTime> {
        return object : DefaultElementProperty<LocalDateTime>() {
            override fun validateInner(): ValuesErrors<LocalDateTime> {

                if (!jsonObject.containsKey(path)) {
                    return NullValuesErrors(path)
                }

                return try {
                    // TODO 数字转换日期
                    val s = jsonObject.getString(path)
                    parser.parseString(s)
                } catch (ex: Exception) {
                    ExceptionValuesErrors(jsonObject.get(path), ex)
                }
            }
        }
    }
}
