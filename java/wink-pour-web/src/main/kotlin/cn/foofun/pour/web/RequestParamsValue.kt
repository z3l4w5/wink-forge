package cn.foofun.pour.web

import cn.foofun.pour.CompositeValue
import cn.foofun.pour.NoneValue
import cn.foofun.pour.PrimitiveValue
import javax.servlet.http.HttpServletRequest

class RequestParamsValue(
    private val httpServletRequest: HttpServletRequest
) : CompositeValue {
    override fun toCompositeValue(index: Int): CompositeValue {
        TODO("Not yet implemented")
    }

    override fun toCompositeValue(path: String): CompositeValue {
        TODO("Not yet implemented")
    }

    override fun toPrimitiveValue(index: Int): PrimitiveValue {
        TODO("Not yet implemented")
    }

    override fun toPrimitiveValue(path: String): PrimitiveValue {
        val strs = this.httpServletRequest.getParameterValues(path)
        return NoneValue()
    }

    override fun indices(): Iterable<Int> {
        TODO("Not yet implemented")
    }

    override fun paths(): Iterable<String> {
        return this.httpServletRequest.parameterMap.keys as Iterable<String>
    }

    override fun isPrimitive(): Boolean {
        return false
    }

    override fun isComposite(): Boolean {
        return true
    }

    override fun asPrimitiveValue(): PrimitiveValue {
        TODO("Not yet implemented")
    }

    override fun asCompositeValue(): CompositeValue {
        return this
    }
}