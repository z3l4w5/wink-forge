package cn.foofun.pour

interface CompositeValue : Value {

    fun toCompositeValue(index: Int): CompositeValue

    fun toCompositeValue(path: String): CompositeValue

    fun toPrimitiveValue(index: Int): PrimitiveValue

    fun toPrimitiveValue(path: String): PrimitiveValue

    fun indices(): Iterable<Int>

    fun paths(): Iterable<String>
}