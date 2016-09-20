package ua.kpi.java.skilift.transfer;


import ua.kpi.java.skilift.skipass.types.DayOfWeekType;
import ua.kpi.java.skilift.skipass.types.LiftsType;
import ua.kpi.java.skilift.skipass.types.PeriodType;
import ua.kpi.java.skilift.skipass.types.SeasonType;

//Data transfer object
public class SkiPassRequest {
    private final SeasonType seasonType;
    private final DayOfWeekType dayType;
    private final LiftsType liftsType;
    private final PeriodType periodType;

    public SkiPassRequest(LiftsType liftsType, DayOfWeekType dayType) {
        this.liftsType = liftsType;
        this.dayType = dayType;
        this.periodType = null;
        this.seasonType = null;
    }


    public SkiPassRequest(PeriodType periodType, DayOfWeekType dayType) {
        this.periodType = periodType;
        this.dayType = dayType;
        this.liftsType = null;
        this.seasonType = null;
    }

    public SkiPassRequest(SeasonType seasonType) {
        this.seasonType = seasonType;
        this.dayType = null;
        this.liftsType = null;
        this.periodType = null;
    }

    public SeasonType getSeasonType() {
        return seasonType;
    }

    public DayOfWeekType getDayType() {
        return dayType;
    }


    public LiftsType getLiftsType() {
        return liftsType;
    }


    public PeriodType getPeriodType() {
        return periodType;
    }


    public SkiPassType getSkiPassType() {
        if (seasonType != null) {
            return SkiPassType.SEASON;
        } else if (dayType == DayOfWeekType.WEEKDAYS) {
            if (liftsType != null) {
                return SkiPassType.WEEKDAY_LIFTS;
            } else {
                return SkiPassType.WEEKDAY_PERIOD;
            }
        } else {
            if (liftsType != null) {
                return SkiPassType.WEEKEND_LIFTS;
            } else {
                return SkiPassType.WEEKEND_PERIOD;
            }
        }
    }
}
