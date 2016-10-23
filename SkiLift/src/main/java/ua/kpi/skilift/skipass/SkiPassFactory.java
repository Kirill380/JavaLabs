package ua.kpi.skilift.skipass;

import ua.kpi.skilift.skipass.types.DayOfWeekType;
import ua.kpi.skilift.skipass.types.LiftsType;
import ua.kpi.skilift.skipass.types.PeriodType;
import ua.kpi.skilift.skipass.types.SeasonType;
import ua.kpi.skilift.transfer.SkiPassRequest;
import ua.kpi.skilift.transfer.SkiPassType;

import java.util.HashMap;
import java.util.Map;

public enum SkiPassFactory {
    SKI_PASS_FACTORY;

    private Long counter = 1L;
    private Map<Long, SkiPass> skiPasses = new HashMap<>();


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
        return skiPasses.containsKey(id);
    }

    public void removeSkiPassFromDB(Long id) {
        skiPasses.remove(id);
    }

    public void clearDB() {
        skiPasses.clear();
    }
}
