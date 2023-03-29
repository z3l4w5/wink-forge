package cn.foofun.forge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PackageSource<T> implements Source<List<T>> {

    Source<T> source;

    int min;
    int max;

    public PackageSource(Source<T> source, int min, int max) {
        this.source = source;
        this.min = min;
        this.max = max;
    }

    @Override
    public List<T> next() {
        int random = new Random().nextInt(max - min) + min;

        List<T> list = new ArrayList<>(random);

        for (int i = 0; i < random; i++) {
            list.add(this.source.next());
        }

        return list;
    }
}
