package cn.foofun.utils

import java.util.regex.Pattern

/**
 * 对身份证和手机号进行脱敏
 * https://cloud.tencent.com/developer/article/1636078
 */
object StringHelper {

    fun buildTemplate(fix: String): TemplateBuilder {
        val s = "$fix(\\w+?)$fix"
        val pattern = Pattern.compile(s)

        return TemplateBuilder(pattern)
    }

    /**
     * 电话号码脱敏
     */
    fun phoneNumber(number: String?): String {
        // 从第四个字符开始遮蔽最多5个字符
        if (number == null) {
            return ""
        }
        if (number.length < 4) {
            return number
        }
        var count = 0
        val chars = number.toCharArray()
        for (i in 3 until chars.size) {
            if (count > 5) {
                break
            }
            chars[i] = '*'
            count++
        }

        return String(chars)
    }

    /**
     * 身份证脱敏，
     * 保留前六后三
     */
    fun idCard(no: String?): String {
        if (no == null) {
            return ""
        }
        val end = no.length - 3
        if (end < 6) {
            return no
        }
        val chars = no.toCharArray()
        for (i in 6 until end) {
            chars[i] = '*'
        }

        return String(chars)
    }
}