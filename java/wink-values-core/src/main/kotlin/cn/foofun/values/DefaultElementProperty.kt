package cn.foofun.values

import cn.foofun.values.exceptions.ValuesException

/**
 * 对单项数据进行一次validateInner检查
 */
abstract class DefaultElementProperty<T> : ElementProperty<T> {

    protected var valuesErrors: ValuesErrors<T>? = null

    override fun validate(): Boolean {
        if (this.valuesErrors != null) {
            return !(this.valuesErrors!!.hasErrors)
        }

        this.valuesErrors = validateInner()

        return !(this.valuesErrors!!.hasErrors)
    }

    /**
     * 进行数据检查
     */
    abstract fun validateInner(): ValuesErrors<T>

    override fun value(): T {
        if (valuesErrors == null) {
            this.validate()
        }

        if (valuesErrors!!.hasErrors) {
            throw ValuesException("存在${valuesErrors}无法取值", valuesErrors!!)
        }

        return valuesErrors!!.value
    }

    override fun errors(): ValuesErrors<T>? {
        return this.valuesErrors
    }
}