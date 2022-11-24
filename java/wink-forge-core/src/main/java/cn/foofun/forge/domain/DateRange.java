package cn.foofun.forge.domain;

import java.util.Date;

/**
 * 前后日期范围
 */
public class DateRange {

    Date begin;

    Date end;

    public DateRange() {
    }

    public DateRange(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return "DateRange{" +
                "begin=" + begin +
                ", end=" + end +
                '}';
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
