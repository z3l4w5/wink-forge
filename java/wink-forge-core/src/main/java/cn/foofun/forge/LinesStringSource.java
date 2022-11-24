package cn.foofun.forge;

import java.util.Arrays;

/**
 * 按单行或多行切割字符串，随机返回
 */
public class LinesStringSource implements Source<String> {

    final ValueListSource<String> stringSource;

    public LinesStringSource(String input, int nextLines) {

        String regex = "\\r?\\n";

        if (nextLines == 2) {
            regex = "\\r?\\n\\r?\\n";
        }

        String[] lines = input.split(regex, -1);

        this.stringSource = new ValueListSource<>(Arrays.asList(lines));
    }

    @Override
    public String next() {
        return this.stringSource.next();
    }
}
