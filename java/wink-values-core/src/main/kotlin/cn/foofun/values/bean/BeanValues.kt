package cn.foofun.values.bean

import cn.foofun.values.DefaultPathValues
import cn.foofun.values.Values
import kotlin.reflect.full.memberProperties

class BeanValues(private val bean: Nothing) : DefaultPathValues {
    override fun getValue(path: String): Any? {
        val properties = this.bean::class.memberProperties

        for (kProperty1 in properties) {
            if (kProperty1.name == path) {
                kProperty1.get(this.bean)
                return kProperty1.call(this.bean)
            }
        }

        return null
    }

    override fun getValues(path: String): Values? {
        TODO("Not yet implemented")
    }

    override fun paths(): Iterable<String> {
        val t = this.bean::class.memberProperties

        return t.map { it.name }
    }
}