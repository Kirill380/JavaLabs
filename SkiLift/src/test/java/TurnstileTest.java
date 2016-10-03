import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.kpi.java.skilift.skipass.SkiPass;
import ua.kpi.java.skilift.skipass.SkiPassFactory;
import ua.kpi.java.skilift.skipass.types.DayOfWeekType;
import ua.kpi.java.skilift.skipass.types.LiftsType;
import ua.kpi.java.skilift.transfer.SkiPassRequest;


//@RunWith(PowerMockRunner.class)
//@PrepareForTest({LocalDate.class, K.class})
public class TurnstileTest extends Assert {

    private SkiPassFactory factory = SkiPassFactory.INSTANCE;
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
