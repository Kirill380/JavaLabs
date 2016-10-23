package ua.kpi.skilift.statistic;


public final class ProcessResult {
    private Long rejectedNum = 0L;
    private Long passedNum = 0L;

    public ProcessResult() {
    }

    public ProcessResult(Long passedNum, Long rejectedNum) {
        this.rejectedNum = rejectedNum;
        this.passedNum = passedNum;
    }

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
