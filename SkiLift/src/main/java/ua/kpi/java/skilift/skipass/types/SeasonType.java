package ua.kpi.java.skilift.skipass.types;


import java.time.Month;
import java.time.MonthDay;

import static java.time.MonthDay.of;

public enum  SeasonType {
    SPRING(of(Month.MARCH, 1), of(Month.AUGUST, 1)),
    WINTER(of(Month.DECEMBER, 1), of(Month.JANUARY, 28)),
    AUTUMN(of(Month.OCTOBER, 15), of(Month.NOVEMBER, 30));

    private final MonthDay from;
    private final MonthDay to;

    SeasonType(MonthDay from, MonthDay to) {
        this.from = from;
        this.to = to;
    }

    public MonthDay getFrom() {
        return from;
    }

    public MonthDay getTo() {
        return to;
    }
}
