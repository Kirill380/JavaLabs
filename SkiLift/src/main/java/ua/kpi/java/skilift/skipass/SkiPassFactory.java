package ua.kpi.java.skilift.skipass;

public class SkiPassFactory {
    private Long counter = 1L;

    private Long nextId() {
        return counter++;
    }


    public SkiPass getSkiPass(DayType day, LiftsType liftsType) {
        return new QuantitativeSkiPass(nextId(), liftsType, day);
    }

    public SkiPass getSkiPass(DayType day, PeriodType periodType) {
        return new TimeSkiPass(nextId(), periodType, day);
    }

    public SkiPass getSkiPass(SeasonType seasonType) {
        return new SeasonSkiPass(nextId(), seasonType);
    }

    private boolean checkId(Long id) {
        return id > 0 && id < counter;
    }
}
