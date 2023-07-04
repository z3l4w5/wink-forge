package cn.foofun.forge;

import java.util.List;
import java.util.Random;

/**
 * 不同值在不同比例
 */
public class ValuesInRateSource<T> implements Source<T> {

    final List<T> values;
    final List<Integer> rates;

    public ValuesInRateSource(List<T> values, List<Integer> rates) {
        this.values = values;
        this.rates = rates;
    }

    @Override
    public T next() {
        int sum = rates.stream().mapToInt(it -> it).sum();
        int random = new Random().nextInt(sum);
        int index = 0;
        int num = 0;
        while (num < sum) {
            num += rates.get(index);
            if (random < num) {
                return values.get(index);
            } else {
                index++;
            }
        }

        return values.get(values.size() - 1);
    }
}
