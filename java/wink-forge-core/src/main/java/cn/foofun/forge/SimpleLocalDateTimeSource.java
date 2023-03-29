package cn.foofun.forge;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Random;

/**
 * 生成日期间的随机值
 */
public class SimpleLocalDateTimeSource implements Source<LocalDateTime> {

    final LocalDateTime begin;

    final LocalDateTime end;

    public SimpleLocalDateTimeSource(LocalDateTime begin, LocalDateTime end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public LocalDateTime next() {

        Duration duration = Duration.between(begin, end);

        int r = (int) duration.getSeconds();

        if (r < 0) {
            r -= r;
        }

        if (r == 0) {
            return begin;
        }

        int v = new Random().nextInt(r);

        return begin.plusSeconds(v);
    }
}
