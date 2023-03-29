package cn.foofun.forge;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Random;

/**
 * 生成日期间的随机值
 */
public class SimpleLocalDateSource implements Source<LocalDate> {

    final LocalDate begin;

    final LocalDate end;

    public SimpleLocalDateSource(LocalDate begin, LocalDate end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public LocalDate next() {

        Period period = Period.between(begin, end);
        int r = period.getDays();

        if (r < 0) {
            r -= r;
        }

        if (r == 0) {
            return begin;
        }

        int v = new Random().nextInt(r);

        return begin.plusDays(v);
    }
}
