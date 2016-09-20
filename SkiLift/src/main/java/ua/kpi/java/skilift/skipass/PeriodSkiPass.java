package ua.kpi.java.skilift.skipass;

import ua.kpi.java.skilift.skipass.types.DayOfWeekType;
import ua.kpi.java.skilift.skipass.types.PeriodType;
import ua.kpi.java.skilift.transfer.SkiPassType;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;

public class PeriodSkiPass extends SkiPass {
    private final PeriodType periodType;
    private final DayOfWeekType dayType;
    private Integer lifts;
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
            LocalDateTime toDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
            activationDate = toDay.plus(start);
            expiredDate = toDay.plus(end);
        }

        if (!isExpired() && isRightDay()) {
            lifts++;
        }
        return true;
    }

    private boolean isRightDay() {
        return dayType.isRightDay(LocalDate.now().getDayOfWeek());
    }

    private boolean isExpired() {
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(expiredDate) && now.isAfter(activationDate);
    }


    public LocalDateTime getActivationDate() {
        return activationDate;
    }
}
