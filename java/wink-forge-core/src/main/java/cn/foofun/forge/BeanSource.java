package cn.foofun.forge;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成Bean多个字段值
 *
 * @param <T>
 */
public class BeanSource<T> implements Source<T> {

    Map<String, Source> sourceMap;

    List<String> keys;

    final Class<T> beanClazz;

    public BeanSource(Class<T> beanClazz) {
        this.sourceMap = new HashMap<>();
        this.keys = new ArrayList<>();

        this.beanClazz = beanClazz;
    }

    @Override
    public T next() {
        try {

            T value = this.beanClazz.getConstructor().newInstance();

            for (String key : this.keys) {
                Source<?> source = this.getSource(key);

                Object v = source.next();

                Field field = this.beanClazz.getDeclaredField(key);

                field.set(value, v);
            }

            return value;
        } catch (Exception exception) {

            return null;
        }
    }

    private Source<?> getSource(String sourceName) {
        Source source = sourceMap.get(sourceName);

        if (source == null) {
            source = new NullSource();
        }

        return source;
    }
}
