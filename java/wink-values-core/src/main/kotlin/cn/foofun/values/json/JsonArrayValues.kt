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

class JsonArrayValues(private val jsonArray: JSONArray) : DefaultIndicesValues {

    override fun getValues(index: Int): Values? {
        if (index < 0 || index >= jsonArray.size) {
            return null
        }

        val obj = jsonArray[index]
        if (obj is JSONObject) {
            return JsonObjectValues(obj)
        } else if (obj is JSONArray) {
            return JsonArrayValues(obj)
        }

        throw ValueNotSupportException("不支持从${jsonArray}取得${index}下的Value")
    }

    override fun getValue(index: Int): Any? {
        return this.jsonArray[index]
    }

    override fun indices(): Iterable<Int> {
        return 0 until this.jsonArray.size
    }

    override fun withInt(index: Int, parser: Parser<Int>): ElementProperty<Int> {
        return JsonArrayIntElementProperty(this.jsonArray, index, parser)
    }

    override fun withLocalDate(index: Int, parser: Parser<LocalDate>): ElementProperty<LocalDate> {
        return JsonArrayLocalDateElementProperty(this.jsonArray, index, parser)
    }

    override fun withBigDecimal(index: Int, parser: Parser<BigDecimal>): ElementProperty<BigDecimal> {
        return JsonArrayBigDecimalElementProperty(this.jsonArray, index, parser)
    }

    override fun withInstant(index: Int, parser: Parser<Instant>): ElementProperty<Instant> {
        return object : DefaultElementProperty<Instant>() {
            override fun validateInner(): ValuesErrors<Instant> {

                if (index < 0 || index >= jsonArray.size) {
                    return NullValuesErrors(index)
                }

                return try {
                    // TODO 改写为支持多种类型
                    val d = jsonArray.getInstant(index)
                    ValuesWithNoneErrors(d, jsonArray.get(index))
                } catch (ex: Exception) {
                    ExceptionValuesErrors(jsonArray.get(index), ex)
                }
            }
        }
    }

    override fun withLocalDateTime(index: Int, parser: Parser<LocalDateTime>): ElementProperty<LocalDateTime> {

        return object : DefaultElementProperty<LocalDateTime>() {
            override fun validateInner(): ValuesErrors<LocalDateTime> {

                if (index < 0 || index >= jsonArray.size) {
                    return NullValuesErrors(index)
                }

                return try {
                    // TODO 改写为支持多种类型
                    val s = jsonArray.getString(index)
                    parser.parseString(s)
                } catch (ex: Exception) {
                    ExceptionValuesErrors(jsonArray.get(index), ex)
                }
            }
        }
    }

    override fun withString(index: Int, parser: Parser<String>): ElementProperty<String> {
        return JsonArrayStringElementProperty(this.jsonArray, index, parser)
    }
}
