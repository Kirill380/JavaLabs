package ua.kpi.java.skilift.skipass;

import ua.kpi.java.skilift.transfer.SkiPassType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class SkiPass {
    private final Long id;
    protected boolean blocked;
    protected LocalDateTime expiredDate;
    private final SkiPassType skiPassType;

    SkiPass(Long id, SkiPassType skiPassType) {
        this.id = id;
        this.skiPassType = skiPassType;
    }

    SkiPass(Long id, SkiPassType skiPassType,  LocalDateTime expiredDate) {
        this(id, skiPassType);
        this.expiredDate = expiredDate;
    }

    public Long getId() {
        return id;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public abstract boolean countLift();

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    // for statistic
    public SkiPassType getSkiPassType() {
        return skiPassType;
    }
}
