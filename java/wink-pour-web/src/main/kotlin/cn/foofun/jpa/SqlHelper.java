package cn.foofun.jpa;

import org.jetbrains.annotations.Nullable;

/**
 * SQL参数语句处理
 * TODO 支持 ？ % 搜索，处理空白字符串
 * <p>
 * TODO 实现一种 a=b&d!=b|d<b&类似的查询解析器
 */
public class SqlHelper {

    /**
     * 前后模糊匹配的字符串处理
     */
    public static String strLike(@Nullable String str) {
        if (str == null) {
            return null;
        }

        String result = cleanUp(str);

        return '%' + result + '%';
    }

    public static String strBegin(String str) {

        String result = cleanUp(str);

        return result + '%';
    }

    public static String strEnd(String str) {

        String result = cleanUp(str);

        return '%' + result;
    }

    /**
     * 去除中间的 %|?关键字
     */
    public static String cleanUp(String str) {
        String result = str.replaceAll("[%|?]", "");

        return result;
    }

    public static String starsLike(String str) {
        String result = str.replaceAll("[%|?]", "");
        if (result.length() == 0) {
            return result;
        }

        result = result.replace("*", "?");

        return result;
    }
}
