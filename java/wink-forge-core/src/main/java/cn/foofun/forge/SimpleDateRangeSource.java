package cn.foofun.forge;

import cn.foofun.forge.domain.DateRange;

import java.util.Date;

public class SimpleDateRangeSource implements Source<DateRange> {

    final Source<Date> dateSource;

    final IntSource intSource;

    /**
     * @param begin   最小起始天
     * @param end     最大起始天
     * @param minDays 最小间隔天数
     * @param maxDays 最大间隔天数
     */
    public SimpleDateRangeSource(Date begin, Date end, int minDays, int maxDays) {

        this.dateSource = new SimpleDateSource(begin, end);
        this.intSource = new IntSource(minDays, maxDays, 1);
    }

    @Override
    public DateRange next() {
        Date value = this.dateSource.next();

        int d = this.intSource.next();

        long time = value.getTime() + d * 1000L * 60 * 60 * 24;

        Date endValue = new Date(time);

        return new DateRange(value, endValue);
    }
}
