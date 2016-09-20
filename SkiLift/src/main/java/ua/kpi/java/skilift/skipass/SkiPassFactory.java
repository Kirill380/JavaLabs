package ua.kpi.java.skilift.skipass;

import ua.kpi.java.skilift.skipass.types.DayOfWeekType;
import ua.kpi.java.skilift.skipass.types.LiftsType;
import ua.kpi.java.skilift.skipass.types.PeriodType;
import ua.kpi.java.skilift.skipass.types.SeasonType;
import ua.kpi.java.skilift.transfer.SkiPassRequest;
import ua.kpi.java.skilift.transfer.SkiPassType;

import java.util.HashMap;
import java.util.Map;

public final class SkiPassFactory {
    private Long counter = 1L;
    private Map<Long, SkiPass> skiPasses = new HashMap<>();
    private static SkiPassFactory instance;

    private SkiPassFactory() {
    }

    public static SkiPassFactory getInstance() {
        if(instance == null) {
            instance = new SkiPassFactory();
        }
        return instance;
    }

    private Long nextId() {
        return counter++;
    }

    public SkiPass geSkiPass(SkiPassRequest request) {
        if(request.getSeasonType() != null) {
            return getSkiPass(request.getSeasonType(), request.getSkiPassType());
        } else if(request.getLiftsType() != null) {
            return getSkiPass(request.getDayType(), request.getLiftsType(), request.getSkiPassType());
        } else  {
            return getSkiPass(request.getDayType(), request.getPeriodType(), request.getSkiPassType());
        }
    }

    private SkiPass getSkiPass(DayOfWeekType day, LiftsType liftsType, SkiPassType type) {
        QuantitativeSkiPass skiPass = new QuantitativeSkiPass(nextId(), liftsType, day, type);
        skiPasses.put(skiPass.getId(), skiPass);
        return skiPass;
    }

    private SkiPass getSkiPass(DayOfWeekType day, PeriodType periodType, SkiPassType type) {
        PeriodSkiPass skiPass = new PeriodSkiPass(nextId(), periodType, day, type);
        skiPasses.put(skiPass.getId(), skiPass);
        return skiPass;
    }

    private SkiPass getSkiPass(SeasonType seasonType, SkiPassType type) {
        SeasonSkiPass skiPass = new SeasonSkiPass(nextId(), seasonType, type);
        skiPasses.put(skiPass.getId(), skiPass);
        return skiPass;
    }

    public boolean checkId(Long id) {
        return id > 0 && id < counter;
    }

    public void removeSkiPassFromDB(Long id) {
        skiPasses.remove(id);
    }
}
