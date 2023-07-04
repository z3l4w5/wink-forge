package cn.foofun.values

/**
 * 复合数据自我列举
 */
interface ValuesEnumerator {

    fun indices(): Iterable<Int>

    fun paths(): Iterable<String>
}