package cn.foofun.values

/**
 * 值检查结果
 */
interface ValuesErrors<T> {

    /**
     * 原始值
     */
    val rawValue: Any?

    /**
     * 转换结果值
     */
    val value: T

    /**
     * 是否有错误
     */
    val hasErrors: Boolean
}

