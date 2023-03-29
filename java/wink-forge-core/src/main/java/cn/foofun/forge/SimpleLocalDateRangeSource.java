package cn.foofun.forge;

import cn.foofun.forge.domain.DateRange;
import cn.foofun.forge.domain.LocalDateRange;

import java.time.LocalDate;
import java.util.Date;

public class SimpleLocalDateRangeSource implements Source<LocalDateRange> {

    final Source<LocalDate> dateSource;

    final IntSource intSource;

    /**
     * @param begin   最小起始天
     * @param end     最大起始天
     * @param minDays 最小间隔天数
     * @param maxDays 最大间隔天数
     */
    public SimpleLocalDateRangeSource(LocalDate begin, LocalDate end, int minDays, int maxDays) {

        this.dateSource = new SimpleLocalDateSource(begin, end);
        this.intSource = new IntSource(minDays, maxDays, 1);
    }

    @Override
    public LocalDateRange next() {

        LocalDate value = this.dateSource.next();

        int d = this.intSource.next();

        LocalDate endValue = value.plusDays(d);

        return new LocalDateRange(value, endValue);
    }
}
