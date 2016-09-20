package ua.kpi.java.skilift.skipass;

import ua.kpi.java.skilift.skipass.types.SeasonType;
import ua.kpi.java.skilift.transfer.SkiPassType;

import java.time.LocalDate;
import java.time.Year;

public class SeasonSkiPass extends SkiPass {
    private final LocalDate from;
    private final LocalDate to;
    private Integer lifts;

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

    private boolean isRightPeriod() {
        LocalDate now = LocalDate.now();
        return now.isBefore(to) && now.isAfter(from);
    }

}
