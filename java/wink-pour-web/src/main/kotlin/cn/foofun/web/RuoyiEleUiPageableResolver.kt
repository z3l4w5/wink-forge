package cn.foofun.web

import org.springframework.core.MethodParameter
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

/**
 * 用于适配Ruoyi ElementUi的分页参数
 * 用PageNum和PageSize
 */
class RuoyiEleUiPageableResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        if (parameter.hasParameterAnnotation(RuoyiEleUiPageable::class.java)) {
            if (parameter.parameterType == Pageable::class.java) {
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
        val ruoyiEleUiPageable = parameter.getParameterAnnotation(RuoyiEleUiPageable::class.java) ?: return null

        // 初始化PageSize
        var pageSize = ruoyiEleUiPageable.defaultPageSize
        if (ruoyiEleUiPageable.pageSize > 0) {
            pageSize = ruoyiEleUiPageable.pageSize
        }
        // 尝试从客户端获取分页参数
        val strPageSize = webRequest.getParameter("pageSize")
        if (strPageSize != null) {
            try {
                val size = strPageSize.toInt()
                if (size > 0) {
                    pageSize = size
                }
            } catch (ex: java.lang.NumberFormatException) {
            }
        }

        // 初始化PageNum 从1开始
        var pageNum = 1
        val strPageNum = webRequest.getParameter("pageNum")
        if (strPageNum != null) {
            pageNum = strPageNum.toInt()
        }

        var sort = ruoyiEleUiPageable.sort
        var direction = ruoyiEleUiPageable.direction

        val s = webRequest.getParameterValues("sort")
        if (s != null && s.size > 0) {
            sort = s
        }

        val d = webRequest.getParameter("direction")
        if (d != null) {
            direction = Sort.Direction.fromString(d)
        }

        return if (sort.isEmpty()) {
            PageRequest.of(pageNum - 1, pageSize)
        } else {
            PageRequest.of(pageNum - 1, pageSize, direction, *sort)
        }

        //isAsc
        //orderByColumn
        //pageSize

    }
}