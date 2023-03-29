package cn.foofun.forge.domain;

import java.time.LocalDate;
import java.util.Date;

/**
 * 前后日期范围
 */
public class LocalDateRange {

    private LocalDate beginDate;

    private LocalDate endDate;

    public LocalDateRange(LocalDate beginDate, LocalDate endDate) {
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
