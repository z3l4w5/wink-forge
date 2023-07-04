package cn.foofun.web

import cn.foofun.values.EmptyValues
import cn.foofun.values.Values
import cn.foofun.values.json.JsonArrayValues
import cn.foofun.values.json.JsonObjectValues
import com.alibaba.fastjson2.JSON
import com.alibaba.fastjson2.JSONArray
import com.alibaba.fastjson2.JSONObject
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.servlet.http.HttpServletRequest


/**
 * 以传统Request的Params参数获取的值集合
 */
class JsonValuesResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        if (parameter.hasParameterAnnotation(JsonValues::class.java)) {
            if (parameter.parameterType == Values::class.java) {
                return true
            }
        }
        return false
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {

        val servletRequest = webRequest.getNativeRequest(HttpServletRequest::class.java) as HttpServletRequest

        // 读取流到string
        val sb = StringBuffer()
        val inputStream = servletRequest.inputStream
        val isr = InputStreamReader(inputStream)
        val br = BufferedReader(isr)
        var s: String? = ""
        while (br.readLine().also { s = it } != null) {
            sb.append(s)
        }
        val str = sb.toString()

        if (str.isBlank()) {
            return EmptyValues()
        }

        val json = JSON.parse(str)

        if (json is JSONObject) {
            return JsonObjectValues(json)
        } else if (json is JSONArray) {
            return JsonArrayValues(json)
        } else {
            return null
        }
    }
}