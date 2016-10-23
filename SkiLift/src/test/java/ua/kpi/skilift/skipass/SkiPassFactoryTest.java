package ua.kpi.skilift.skipass;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.kpi.skilift.skipass.types.DayOfWeekType;
import ua.kpi.skilift.skipass.types.LiftsType;
import ua.kpi.skilift.skipass.types.PeriodType;
import ua.kpi.skilift.skipass.types.SeasonType;
import ua.kpi.skilift.transfer.SkiPassRequest;

public class SkiPassFactoryTest extends Assert {
    private SkiPassFactory factory = SkiPassFactory.SKI_PASS_FACTORY;
    private SkiPass skiPassOne;
    private SkiPass skiPassTwo;
    private SkiPass skiPassThree;

    @Before
    public void setUp() throws Exception {
        skiPassOne = factory.geSkiPass(new SkiPassRequest(LiftsType.TEN, DayOfWeekType.WEEKDAYS));
        skiPassTwo = factory.geSkiPass(new SkiPassRequest(PeriodType.DAY, DayOfWeekType.WEEKDAYS));
        skiPassThree = factory.geSkiPass(new SkiPassRequest(SeasonType.AUTUMN));
    }

    @After
    public void tearDown() throws Exception {
        factory.clearDB();
    }

    @Test
    public void testCreation() {
        assertTrue(factory.checkId(skiPassOne.getId()));
        assertTrue(factory.checkId(skiPassTwo.getId()));
        assertTrue(factory.checkId(skiPassThree.getId()));
    }

    @Test
    public void testRemove() {
        factory.removeSkiPassFromDB(skiPassTwo.getId());
        assertTrue(factory.checkId(skiPassOne.getId()));
        assertFalse(factory.checkId(skiPassTwo.getId()));
        assertTrue(factory.checkId(skiPassThree.getId()));
    }

    @Test
    public void testClear() {
        factory.clearDB();
        assertFalse(factory.checkId(skiPassOne.getId()));
        assertFalse(factory.checkId(skiPassOne.getId()));
        assertFalse(factory.checkId(skiPassOne.getId()));
    }
}
