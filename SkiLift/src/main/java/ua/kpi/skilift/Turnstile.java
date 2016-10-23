package ua.kpi.skilift;

import ua.kpi.skilift.skipass.SkiPass;
import ua.kpi.skilift.skipass.SkiPassFactory;
import ua.kpi.skilift.statistic.ProcessResult;
import ua.kpi.skilift.transfer.SkiPassType;

import java.util.HashMap;
import java.util.Map;


public class Turnstile {

    private Map<SkiPassType, ProcessResult> statistic = new HashMap<>();
    private SkiPassFactory factory = SkiPassFactory.SKI_PASS_FACTORY;

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
            result.increment(isPassed);
        } else {
            ProcessResult result = new ProcessResult();
            result.increment(isPassed);
            statistic.put(type, result);
        }
    }

    public ProcessResult getStatisticByType(SkiPassType type) {
        return statistic.get(type);
    }

    public ProcessResult getTotalStatistic() {
        ProcessResult total = new ProcessResult();
        for (ProcessResult processResult : statistic.values()) {
            total = total.plus(processResult);
        }
        return total;
    }
}
