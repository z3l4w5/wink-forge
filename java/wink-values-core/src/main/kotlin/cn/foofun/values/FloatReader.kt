package cn.foofun.values

class FloatReader {
    fun Values.withInt(index: Int, parser: Parser<Int>): ElementProperty<Int> {
        return withInt(index, defaultIntParser())
    }
}