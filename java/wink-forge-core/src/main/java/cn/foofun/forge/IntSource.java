package cn.foofun.forge;

import java.util.*;

/**
 * 生产min到max范围内,按步进大小不重复的数字
 */
public class IntSource implements Source<Integer> {

    final int max;
    final int min;
    final int step;

    List<Integer> results;
    int index = 0;

    public IntSource(int min, int max, int step) {
        this.min = min;
        this.max = max;
        this.step = step;

        this.init();
    }

    final Random random = new Random(new Date().getTime());

    void init() {
        this.results = new ArrayList<>();
        int value = this.min;
        while (value < this.max) {
            this.results.add(value);
            value += step;
        }
        this.results.add(this.max);

        // 从末尾开始，前面范围生成一个随机数
        for (int i = this.results.size() - 1; i >= 1; --i) {
            int swapIndex = this.random.nextInt(i);
            // 交换数字
            Collections.swap(this.results, swapIndex, i);
        }
    }

    @Override
    public Integer next() {
        if (this.index >= (this.results.size())) {
            this.index = 0;
        }
        return this.results.get(this.index++);
    }
}
