package ua.kpi.java.skilift.skipass.types;

import java.time.Duration;
import java.time.temporal.TemporalAmount;

public enum PeriodType {
    FIRST_HALF_DAY(Duration.ofHours(9), Duration.ofHours(13)),
    SECOND_HALF_DAY(Duration.ofHours(13), Duration.ofHours(17)),
    DAY(Duration.ZERO, Duration.ofDays(1)),
    TWO_DAYS(Duration.ZERO, Duration.ofDays(2)),
    FIVE_DAYS(Duration.ZERO, Duration.ofDays(5));

    private final TemporalAmount start;
    private final TemporalAmount end;

    public TemporalAmount getStart() {
        return start;
    }

    public TemporalAmount getEnd() {
        return end;
    }

    PeriodType(TemporalAmount start, TemporalAmount end) {
        this.start = start;
        this.end = end;
    }
}
