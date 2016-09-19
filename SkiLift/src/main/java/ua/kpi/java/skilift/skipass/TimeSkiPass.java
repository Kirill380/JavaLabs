package ua.kpi.java.skilift.skipass;

import java.time.LocalDateTime;

public class TimeSkiPass extends SkiPass {
    private final PeriodType periodType;
    private final DayType dayType;
    private Integer lifts;
    private LocalDateTime expiredTime;

    TimeSkiPass(Long id, PeriodType periodType, DayType dayType) {
        super(id);
        this.periodType = periodType;
        this.dayType = dayType;
    }

    @Override
    public boolean countLift() {
        lifts++;
        return true;
    }

}
