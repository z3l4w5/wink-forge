package cn.foofun.pour

interface ElementProperty<T> {

    fun validate(): Boolean

    fun value(defaultValue: T): T

    fun value(): T
}