package cn.foofun.values

/**
 * 待校验单值
 */
interface ElementProperty<T> {

    /**
     * 检查数据
     * @return 返回true表示有效
     */
    fun validate(): Boolean

    /**
     * 返回值
     */
    fun value(): T

    /**
     * 返回存在的错误内容
     */
    fun errors(): ValuesErrors<T>?
}

