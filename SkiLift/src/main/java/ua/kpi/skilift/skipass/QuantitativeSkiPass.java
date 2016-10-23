package ua.kpi.skilift.skipass;

import ua.kpi.skilift.skipass.types.DayOfWeekType;
import ua.kpi.skilift.skipass.types.LiftsType;
import ua.kpi.skilift.transfer.SkiPassType;

public class QuantitativeSkiPass extends SkiPass {
    private final LiftsType liftsType;
    private final DayOfWeekType dayType;
    private int currAmountOfLifts;

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

    @Override
    public int getLifts() {
        return liftsType.getAmount() - currAmountOfLifts;
    }

    private boolean isExpired() {
        return currAmountOfLifts == 0;
    }

    private boolean isRightDay() {
        return dayType.isRightDay(getNow().getDayOfWeek());
    }
}
