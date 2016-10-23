package ua.kpi.skilift;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.kpi.skilift.skipass.SkiPass;
import ua.kpi.skilift.skipass.SkiPassFactory;
import ua.kpi.skilift.skipass.types.DayOfWeekType;
import ua.kpi.skilift.skipass.types.LiftsType;
import ua.kpi.skilift.transfer.SkiPassRequest;


//@RunWith(PowerMockRunner.class)
//@PrepareForTest({LocalDate.class, K.class})
public class TurnstileTest extends Assert {

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
}
