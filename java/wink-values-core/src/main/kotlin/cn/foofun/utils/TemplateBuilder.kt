package cn.foofun.utils

import java.util.regex.Pattern

/**
 * 从模板替换字符串
 */
class TemplateBuilder(private val pattern: Pattern) {
    fun build(template: String, params: Map<String, String>): String {

        val matcher = pattern.matcher(template)

        val stringBuilder = StringBuilder()

        var begin = 0

        while (matcher.find()) {
            val start = matcher.start()

            if (start > begin) {
                val subStr = template.substring(begin, start)
                stringBuilder.append(subStr)
            }

            val key = matcher.group(1)
            val value = params[key]
            stringBuilder.append(value)

            val end = matcher.end()
            begin = end
        }

        if (begin < template.length) {
            // 后续还有文本
            val subStr = template.substring(begin)
            stringBuilder.append(subStr)
        }

        return stringBuilder.toString()
    }
}