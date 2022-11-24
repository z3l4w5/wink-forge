package cn.foofun.forge;

import cn.foofun.forge.domain.IntRange;

import java.util.Random;

public class SimpleIntRangeSource implements Source<IntRange> {

    final IntSource startSource;
    final int steps;
    final int step;

    /**
     * 生成范围在 [startMin, startMax] - [start + step * steps] 范围的数据范围
     *
     * @param startMin 最小起始值
     * @param startMax 最大起始值
     * @param step     上限步进值
     * @param steps    步进数
     */
    public SimpleIntRangeSource(int startMin, int startMax, int step, int steps) {
        this.startSource = new IntSource(startMin, startMax, step);
        this.step = step;
        this.steps = steps;
    }

    @Override
    public IntRange next() {
        int b = this.startSource.next();
        int e = b + (new Random().nextInt(steps) + 1) * this.step;

        return new IntRange(b, e);
    }
}
