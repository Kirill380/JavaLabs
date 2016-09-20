package ua.kpi.java.skilift.skipass;

import ua.kpi.java.skilift.skipass.types.DayOfWeekType;
import ua.kpi.java.skilift.skipass.types.LiftsType;
import ua.kpi.java.skilift.transfer.SkiPassType;

import java.time.LocalDate;

public class QuantitativeSkiPass extends SkiPass {
    private final LiftsType liftsType;
    private final DayOfWeekType dayType;
    private Integer currAmountOfLifts;

    QuantitativeSkiPass(Long id, LiftsType liftsType, DayOfWeekType dayType, SkiPassType type) {
        super(id, type);
        this.liftsType = liftsType;
        this.dayType = dayType;
        this.currAmountOfLifts = liftsType.getAmount();
    }


    @Override
    public boolean countLift() {
        if (!isExpired() && isRightDay()) {
            currAmountOfLifts--;
            return true;
        }
        return false;

    }

    private boolean isExpired() {
        return currAmountOfLifts == 0;
    }

    private boolean isRightDay() {
        return dayType.isRightDay(LocalDate.now().getDayOfWeek());
    }
}
