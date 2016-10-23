package ua.kpi.skilift.skipass;

import ua.kpi.skilift.skipass.types.DayOfWeekType;
import ua.kpi.skilift.skipass.types.PeriodType;
import ua.kpi.skilift.transfer.SkiPassType;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;

public class PeriodSkiPass extends SkiPass {
    private final PeriodType periodType;
    private final DayOfWeekType dayType;
    private int lifts;
    private LocalDateTime activationDate;

    PeriodSkiPass(Long id, PeriodType periodType, DayOfWeekType dayType, SkiPassType type) {
        super(id, type);
        this.periodType = periodType;
        this.dayType = dayType;
    }

    @Override
    public boolean countLift() {
        if (activationDate == null) {
            TemporalAmount start = periodType.getStart();
            TemporalAmount end = periodType.getEnd();
            LocalDateTime toDay = LocalDateTime.of(getNow().toLocalDate(), LocalTime.MIDNIGHT);
            activationDate = toDay.plus(start);
            expiredDate = toDay.plus(end);
        }

        if (!tooEarly() && !isExpired() && isRightDay()) {
            lifts++;
            return true;
        }
        return false;
    }

    @Override
    public int getLifts() {
        return lifts;
    }

    private boolean isRightDay() {
        return dayType.isRightDay(getNow().getDayOfWeek());
    }


    private boolean isExpired() {
        LocalDateTime now = getNow();
        return now.isAfter(expiredDate);
    }

    private boolean tooEarly() {
        LocalDateTime now = getNow();
        return now.isBefore(activationDate);
    }

    public LocalDateTime getActivationDate() {
        return activationDate;
    }
}
