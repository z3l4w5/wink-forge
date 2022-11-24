package cn.foofun.forge;

/**
 * 辅助复合数据生成器
 */
public class CompositeSourceBuilder {

    final MapSource mapSource;

    public CompositeSourceBuilder() {
        this.mapSource = new MapSource();
    }

    public CompositeSourceBuilder add(String key, Source<?> source) {
        this.mapSource.keys.add(key);
        this.mapSource.sourceMap.put(key, source);

        return this;
    }

    public MapSource toMapSource() {

        return this.mapSource;
    }

    public <T> BeanSource<T> toBeanSource(Class<T> clazz) {
        BeanSource<T> beanSource = new BeanSource<>(clazz);

        beanSource.keys = this.mapSource.keys;
        beanSource.sourceMap = this.mapSource.sourceMap;

        return beanSource;
    }
}
