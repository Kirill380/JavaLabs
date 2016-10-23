package ua.kpi.skilift;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.kpi.skilift.skipass.SkiPass;
import ua.kpi.skilift.skipass.SkiPassFactory;
import ua.kpi.skilift.skipass.types.DayOfWeekType;
import ua.kpi.skilift.skipass.types.LiftsType;
import ua.kpi.skilift.skipass.types.PeriodType;
import ua.kpi.skilift.statistic.ProcessResult;
import ua.kpi.skilift.transfer.SkiPassRequest;
import ua.kpi.skilift.transfer.SkiPassType;

import java.time.LocalDateTime;
import java.time.Month;

import static java.time.LocalDateTime.of;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


//@RunWith(PowerMockRunner.class)
//@PrepareForTest({LocalDate.class, K.class})
public class TurnstileTest extends Assert {

    private static final LocalDateTime MONDAY_MORNING = of(2016, Month.OCTOBER, 24, 9, 0, 0);
    private SkiPassFactory factory = SkiPassFactory.SKI_PASS_FACTORY;
    private Turnstile turnstile;

    @Before
    public void setUp() throws Exception {
        turnstile = new Turnstile();
    }

    @After
    public void tearDown() throws Exception {
        factory.clearDB();
    }

    @Test
    public void testBlockedSkiPass() {
        SkiPass skiPass = factory.geSkiPass(new SkiPassRequest(LiftsType.TEN, DayOfWeekType.WEEKDAYS));
        skiPass.setBlocked(true);
        boolean pass = turnstile.processSkiPass(skiPass);
        assertTrue(!pass);
    }


    @Test
    public void testNotExistSkiPass() {
        SkiPass skiPass = factory.geSkiPass(new SkiPassRequest(LiftsType.TEN, DayOfWeekType.WEEKDAYS));
        factory.removeSkiPassFromDB(skiPass.getId());
        boolean pass = turnstile.processSkiPass(skiPass);
        assertTrue(!pass);
    }

    @Test
    public void testStatistic() {
        final long liftsTypeCount = 5;
        final long periodTypeCount = 7;

        for (int i = 0; i < liftsTypeCount; i++) {
            SkiPass skiPass = factory.geSkiPass(new SkiPassRequest(LiftsType.TEN, DayOfWeekType.WEEKDAYS));
            turnstile.processSkiPass(skiPass);
        }

        for (int i = 0; i < periodTypeCount; i++) {
            SkiPass skiPass = spy(factory.geSkiPass(new SkiPassRequest(PeriodType.DAY, DayOfWeekType.WEEKDAYS)));
            when(skiPass.getNow()).thenReturn(MONDAY_MORNING);
            turnstile.processSkiPass(skiPass);
        }

        ProcessResult statisticLifts = turnstile.getStatisticByType(SkiPassType.WEEKDAY_LIFTS);
        ProcessResult statisticPeriod = turnstile.getStatisticByType(SkiPassType.WEEKDAY_PERIOD);
        ProcessResult totalStatistic = turnstile.getTotalStatistic();

        assertEquals(liftsTypeCount, statisticLifts.getPassedNum());
        assertEquals(periodTypeCount, statisticPeriod.getPassedNum());
        assertEquals(liftsTypeCount + periodTypeCount, totalStatistic.getPassedNum());
    }


}
