package cn.foofun.forge;

import java.util.Date;
import java.util.Random;

/**
 * 随机向前几个元素的源
 *
 * @param <T>
 */
public class RandomSource<T> implements Source<T> {

    int count = 3;

    Source<T> innerSource;

    Random random;

    public RandomSource(Source<T> innerSource, int count) {
        this.count = count;
        this.innerSource = innerSource;

        this.random = new Random(new Date().getTime());
    }

    @Override
    public T next() {
        int r = random.nextInt(count);
        for (int i = 0; i < r; i++) {
            this.innerSource.next();
        }
        return this.innerSource.next();
    }
}
