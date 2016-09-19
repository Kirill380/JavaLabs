package ua.kpi.java.skilift.skipass;

public abstract class SkiPass {
    private final Long id;
    private boolean blocked;

     SkiPass(Long id) {
        this.id = id;
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
}
