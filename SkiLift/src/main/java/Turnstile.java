import ua.kpi.java.skilift.skipass.SkiPass;
import ua.kpi.java.skilift.skipass.SkiPassFactory;
import ua.kpi.java.skilift.statistic.ProcessResult;
import ua.kpi.java.skilift.transfer.SkiPassType;

import java.util.HashMap;
import java.util.Map;


public class Turnstile {

    private Map<SkiPassType, ProcessResult> statistic = new HashMap<>();
    private SkiPassFactory factory = SkiPassFactory.INSTANCE;

    public boolean processSkiPass(SkiPass skiPass) {
        boolean isExist = factory.checkId(skiPass.getId());
        if (isExist && !skiPass.isBlocked()) {
            boolean isPassed = skiPass.countLift();
            saveResult(isPassed, skiPass.getSkiPassType());
            return isPassed;
        } else {
            saveResult(false, skiPass.getSkiPassType());
            return false;
        }
    }


    private void saveResult(boolean isPassed, SkiPassType type) {
        if (statistic.containsKey(type)) {
            ProcessResult result = statistic.get(type);
            if(isPassed)
                result.getPassedNum();
            else
                result.incrementRejected();
        } else {
            ProcessResult result = new ProcessResult();
            if(isPassed)
                result.getPassedNum();
            else
                result.incrementRejected();
            statistic.put(type, result);
        }
    }

    private ProcessResult getStatisticByType(SkiPassType type) {
        return statistic.get(type);
    }

    private ProcessResult getTotalStatistic() {
        long passed = 0;
        long rejected = 0;
        for (ProcessResult processResult : statistic.values()) {
            passed += processResult.getPassedNum();
            rejected += processResult.getRejectedNum();
        }
        return  new ProcessResult(passed, rejected);
    }
}
