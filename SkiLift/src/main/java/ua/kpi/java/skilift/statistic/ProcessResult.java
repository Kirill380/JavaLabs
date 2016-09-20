package ua.kpi.java.skilift.statistic;


public final class ProcessResult {

    private Long rejectedNum;
    private Long passedNum;

    public Long getRejectedNum() {
        return rejectedNum;
    }

    public void incrementRejected() {
        this.rejectedNum++;
    }

    public Long getPassedNum() {
        return passedNum;
    }

    public void incrementPassed() {
        this.passedNum++;
    }
}
