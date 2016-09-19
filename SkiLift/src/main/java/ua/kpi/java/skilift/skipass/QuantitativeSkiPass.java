package ua.kpi.java.skilift.skipass;

import java.time.LocalDate;

public class QuantitativeSkiPass extends SkiPass {
    private final LiftsType liftsType;
    private final DayType dayType;
    private Integer currAmountOfLifts;

    QuantitativeSkiPass(Long id, LiftsType liftsType, DayType dayType) {
        super(id);
        this.liftsType = liftsType;
        this.dayType = dayType;
        this.currAmountOfLifts = liftsType.getAmount();
    }


    @Override
    public boolean countLift() {
        if (!isExpired() && isRightDay()) {
            currAmountOfLifts--;
            return true;
        } else {
            return false;
        }
    }

    private boolean isExpired() {
        return currAmountOfLifts == 0;
    }

    private boolean isRightDay() {
        return dayType.isRightDay(LocalDate.now().getDayOfWeek());
    }
}
