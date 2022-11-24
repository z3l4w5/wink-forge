package cn.foofun.forge;

import java.util.List;

/**
 * 将一个列表值中的乱序输出
 *
 * @param <T>
 */
public class ValueListSource<T> implements Source<T> {

    final List<T> values;

    final IntSource intSource;

    public ValueListSource(List<T> values, IntSource intSource) {
        this.values = values;
        this.intSource = intSource;
    }

    public ValueListSource(List<T> values) {
        this(values, new IntSource(0, values.size() - 1, 1));
    }

    @Override
    public T next() {
        int index = intSource.next();

        return values.get(index);
    }
}
