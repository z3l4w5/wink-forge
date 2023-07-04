package cn.foofun.values

import cn.foofun.values.exceptions.ValueNotSupportException
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime

class ListValues(private val list: List<*>) : DefaultIndicesValues {

    override fun getValues(index: Int): Values {
        val v = list[index]

        if (v == null) {
            throw ValueNotSupportException("${list}中${index}值为空")
        } else {
            return wrapValues(v)
        }
    }

    override fun getValue(index: Int): Any? {
        return list[index]
    }

    override fun indices(): Iterable<Int> {
        return 0 until list.size
    }
}
