package cn.foofun.forge;

import java.util.*;

/**
 * 包含多个键对应的Source
 */
public class MapSource implements Source<Map<String, Object>> {

    final Map<String, Source> sourceMap;

    final List<String> keys;

    public MapSource() {
        this.sourceMap = new HashMap<>();
        this.keys = new ArrayList<>();
    }

    @Override
    public Map<String, Object> next() {

        Map<String, Object> value = new LinkedHashMap<>();

        for (String key : this.keys) {
            Source<?> source = this.getSource(key);

            value.put(key, source.next());
        }

        return value;
    }

    private Source<?> getSource(String sourceName) {
        Source source = sourceMap.get(sourceName);

        if (source == null) {
            source = new NullSource();
        }

        return source;
    }
}
