package cn.foofun.forge;

import java.util.Date;
import java.util.Random;

/**
 * 生成日期间的随机值
 */
public class SimpleDateSource implements Source<Date> {

    final Date begin;

    final Date end;

    public SimpleDateSource(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public Date next() {
        long time = begin.getTime();

        long range = end.getTime() - time;

        range = range / 1000;

        int r = (int) range;
        if (r < 0) {
            r -= r;
        }
        
        if (r == 0) {
            return begin;
        }

        int v = new Random().nextInt(r) * 1000;

        return new Date(time + v);
    }
}
