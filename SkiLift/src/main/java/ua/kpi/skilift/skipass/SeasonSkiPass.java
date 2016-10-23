package ua.kpi.skilift.skipass;

import ua.kpi.skilift.skipass.types.SeasonType;
import ua.kpi.skilift.transfer.SkiPassType;

import java.time.LocalDate;
import java.time.Year;

public class SeasonSkiPass extends SkiPass {
    private final LocalDate from;
    private final LocalDate to;
    private int lifts;

    SeasonSkiPass(Long id, SeasonType seasonType, SkiPassType type) {
        super(id, type);
        Year now = Year.now();
        this.from = seasonType.getFrom().atYear(now.getValue());
        this.to = seasonType.getTo().atYear(now.getValue());
    }

    @Override
    public boolean countLift() {
        if (isRightPeriod()) {
            lifts++;
            return true;
        }
        return false;
    }

    @Override
    public int getLifts() {
        return lifts;
    }

    private boolean isRightPeriod() {
        LocalDate now = getNow().toLocalDate();
        return now.isBefore(to) && now.isAfter(from);
    }

}
