package cn.foofun.pour

interface Value {

    fun isPrimitive(): Boolean

    fun isComposite(): Boolean

    fun asPrimitiveValue(): PrimitiveValue

    fun asCompositeValue(): CompositeValue

}