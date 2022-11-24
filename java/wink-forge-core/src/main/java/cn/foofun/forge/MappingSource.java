package cn.foofun.forge;

import java.util.function.Function;

/**
 * 将一个Source映射为另一个Source
 */
public class MappingSource<T, S> implements Source<T> {
    final Source<S> source;

    final Function<? super S, ? extends T> mapper;

    public MappingSource(Source<S> source, Function<? super S, ? extends T> mapper) {
        this.source = source;
        this.mapper = mapper;
    }

    @Override
    public T next() {
        return this.mapper.apply(this.source.next());
    }
}


