package cn.foofun.values

import cn.foofun.values.exceptions.ValueNotSupportException

fun wrapValues(v: Any): Values {
    if (v is Map<*, *>) {
        return MapValues(v as Map<String, Any?>)
    }

    if (v is List<*>) {
        return ListValues(v)
    }

    if (v is Array<*>) {
        // TODO 不强制转换为String
        return StringArrayValues(v as Array<String?>)
    }

    throw ValueNotSupportException("${v}，无法转换为Values")
}

/**
 * 复合数据适配类
 * 支持按path:string取值和按index:Int取值
 */
interface Values : ValuesEnumerator, IntReader, StringReader,
    LocalDateTimeReader, LocalDateReader, InstantDateReader, BigDecimalReader {

    /**
     * 按path取复合Values
     */
    fun getValues(path: String): Values?

    /**
     * 按path取原始单值Value
     */
    fun getValue(path: String): Any?

    /// 按index取value

    /**
     * 按index取复合Values
     */
    fun getValues(index: Int): Values?

    /**
     * 按index取单值value
     */
    fun getValue(index: Int): Any?

    fun getStrings(): List<String> {
        val iterable = this.indices()

        val list = mutableListOf<String>()
        for (i in iterable) {
            val str = this.getString(i)
            list.add(str)
        }

        return list
    }

    fun getLongs(): List<Long> {
        val iterable = this.indices()

        val list = mutableListOf<Long>()
        for (i in iterable) {
            // TODO 读取Long
            val str = this.getInt(i)
            list.add(str.toLong())
        }

        return list
    }
}
