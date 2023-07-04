package cn.foofun.utils

import java.time.LocalDate

object DateUtils {
    fun isNowInDate(beginDate: LocalDate?, endDate: LocalDate?): Boolean {
        if (beginDate == null || endDate == null) {
            return false
        }

        val now = LocalDate.now()

        return beginDate <= now && endDate >= now
    }
}