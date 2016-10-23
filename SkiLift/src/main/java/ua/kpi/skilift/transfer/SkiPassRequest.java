package ua.kpi.skilift.transfer;


import ua.kpi.skilift.skipass.types.DayOfWeekType;
import ua.kpi.skilift.skipass.types.LiftsType;
import ua.kpi.skilift.skipass.types.PeriodType;
import ua.kpi.skilift.skipass.types.SeasonType;

//Data transfer object
public class SkiPassRequest {
    private final SeasonType seasonType;
    private final DayOfWeekType dayType;
    private final LiftsType liftsType;
    private final PeriodType periodType;
    private final SkiPassType skiPassType;

    public SkiPassRequest(LiftsType liftsType, DayOfWeekType dayType) {
        this.liftsType = liftsType;
        this.dayType = dayType;
        this.periodType = null;
        this.seasonType = null;
        this.skiPassType = dayType == DayOfWeekType.WEEKDAYS ? SkiPassType.WEEKDAY_LIFTS : SkiPassType.WEEKEND_LIFTS;
    }


    public SkiPassRequest(PeriodType periodType, DayOfWeekType dayType) {
        this.periodType = periodType;
        this.dayType = dayType;
        this.liftsType = null;
        this.seasonType = null;
        this.skiPassType = dayType == DayOfWeekType.WEEKDAYS ? SkiPassType.WEEKDAY_PERIOD : SkiPassType.WEEKEND_PERIOD;
    }

    public SkiPassRequest(SeasonType seasonType) {
        this.seasonType = seasonType;
        this.dayType = null;
        this.liftsType = null;
        this.periodType = null;
        this.skiPassType = SkiPassType.SEASON;
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
        return skiPassType;
    }
}
