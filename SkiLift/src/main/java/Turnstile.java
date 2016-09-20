import ua.kpi.java.skilift.skipass.SkiPass;
import ua.kpi.java.skilift.skipass.SkiPassFactory;
import ua.kpi.java.skilift.statistic.ProcessResult;
import ua.kpi.java.skilift.transfer.SkiPassType;

import java.util.HashMap;
import java.util.Map;


public class Turnstile {

    private Map<SkiPassType, ProcessResult> statistic = new HashMap<>();
    private SkiPassFactory factory = SkiPassFactory.getInstance();

    public void processSkiPass(SkiPass skiPass) {
        boolean isExist = factory.checkId(skiPass.getId());

        if (isExist && !skiPass.isBlocked()) {



        } else {
            if (statistic.containsKey(skiPass.getSkiPassType())) {
                ProcessResult result = statistic.get(skiPass.getSkiPassType());
                result.incrementRejected();
            } else {
                ProcessResult result = new ProcessResult();
                result.incrementRejected();
                statistic.put(skiPass.getSkiPassType(), result);
            }
        }
    }
}
