package cn.foofun.forge.business;

import cn.foofun.forge.Source;

/**
 * 中国行政区划
 */
public class ChineseDivisionsSource implements Source<String[]> {
    @Override
    public String[] next() {
        return new String[]{"江苏省", "扬州市", "扬州经济技术开发区"};
    }
}