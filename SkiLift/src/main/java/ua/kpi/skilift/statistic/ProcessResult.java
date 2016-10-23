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

    public ProcessResult plus(ProcessResult processResult) {
        return new ProcessResult(passedNum + processResult.getPassedNum(), rejectedNum + processResult.getRejectedNum());
    }
    public long getRejectedNum() {
        return rejectedNum;
    }


    public long getPassedNum() {
        return passedNum;
    }


    public void increment(boolean passed) {
        if(passed) {
            this.passedNum++;
        } else {
            this.rejectedNum++;
        }
    }
}
