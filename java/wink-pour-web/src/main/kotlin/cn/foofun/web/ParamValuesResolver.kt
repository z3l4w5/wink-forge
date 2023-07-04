package cn.foofun.web

import cn.foofun.values.Values
import cn.foofun.spring.values.WebRequestValues
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

/**
 * 以传统Request的Params参数获取的值集合
 */
class ParamValuesResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        if (parameter.hasParameterAnnotation(ParamValues::class.java)) {
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
        return WebRequestValues(webRequest)
    }
}