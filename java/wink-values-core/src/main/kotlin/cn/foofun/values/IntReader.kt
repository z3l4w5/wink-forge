package cn.foofun.values

/**
 * 复合数据读取出Int
 */
interface IntReader {

    fun getInt(index: Int): Int {
        return withInt(index).value()
    }

    fun getInt(path: String): Int {
        return withInt(path).value()
    }

    fun defaultIntParser(): Parser<Int>

    fun getInt(index: Int, defaultValue: Int): Int {
        return getInt(index, defaultIntParser(), defaultValue)
    }

    fun getInt(path: String, defaultValue: Int): Int {
        return getInt(path, defaultIntParser(), defaultValue)
    }

    fun getInt(index: Int, parser: Parser<Int>, defaultValue: Int): Int {
        val v = withInt(index, parser)
        if (v.validate()) {
            return v.value()
        }
        return defaultValue
    }

    fun getInt(path: String, parser: Parser<Int>, defaultValue: Int): Int {
        val v = withInt(path, parser)
        if (v.validate()) {
            return v.value()
        }
        return defaultValue
    }

    fun withInt(index: Int): ElementProperty<Int> {
        return withInt(index, defaultIntParser())
    }

    fun withInt(index: Int, parser: Parser<Int>): ElementProperty<Int>

    fun withInt(path: String): ElementProperty<Int> {
        return withInt(path, defaultIntParser())
    }

    fun withInt(path: String, parser: Parser<Int>): ElementProperty<Int>
}