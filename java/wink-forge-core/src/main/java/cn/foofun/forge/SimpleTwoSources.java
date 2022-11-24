package cn.foofun.forge;

import java.util.Random;

/**
 * 简单合并两个Source
 *
 * @param <T>
 */
public class SimpleTwoSources<T> implements Source<T> {

    final Source<T> source1;
    final Source<T> source2;
    final int rate1;
    final int rate2;

    public SimpleTwoSources(Source<T> source1, Source<T> source2, int rate1, int rate2) {
        this.source1 = source1;
        this.source2 = source2;
        this.rate1 = rate1;
        this.rate2 = rate2;
    }

    @Override
    public T next() {
        int random = new Random().nextInt(rate1 + rate2);

        if (random < rate1) {
            return source1.next();
        } else {
            return source2.next();
        }
    }
}
