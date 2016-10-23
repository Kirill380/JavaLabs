package ua.kpi.skilift.skipass;


import org.junit.Assert;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import ua.kpi.skilift.transfer.SkiPassRequest;

import java.time.LocalDateTime;
import java.time.Month;

import static java.time.LocalDateTime.of;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static ua.kpi.skilift.skipass.SkiPassFactory.SKI_PASS_FACTORY;
import static ua.kpi.skilift.skipass.types.DayOfWeekType.WEEKDAYS;
import static ua.kpi.skilift.skipass.types.PeriodType.FIRST_HALF_DAY;

public class PeriodSkiPassTest  extends Assert{

    private static final LocalDateTime MONDAY_MORNING = of(2016, Month.OCTOBER, 24, 9, 0, 0);

    @Test
    public void testLifts() {
        SkiPass skiPass = spy(SKI_PASS_FACTORY.geSkiPass(new SkiPassRequest(FIRST_HALF_DAY, WEEKDAYS)));
        when(skiPass.getNow()).thenReturn(MONDAY_MORNING);
        skiPass.countLift();
        skiPass.countLift();
        skiPass.countLift();
        assertEquals(3, skiPass.getLifts());
    }

    @Test
    public void testExpired() {
        SkiPass skiPass = spy(SKI_PASS_FACTORY.geSkiPass(new SkiPassRequest(FIRST_HALF_DAY, WEEKDAYS)));
        when(skiPass.getNow()).thenAnswer(new Answer<LocalDateTime>() {
            @Override
            public LocalDateTime answer(InvocationOnMock invocationOnMock) throws Throwable {
                if(skiPass.getLifts() > 0) {
                    return MONDAY_MORNING.plusDays(5);
                }
                return MONDAY_MORNING; // Monday
            }
        });

        boolean firstLift = skiPass.countLift();
        boolean secondLift = skiPass.countLift();
        assertTrue(firstLift);
        assertFalse(secondLift);
    }

    @Test
    public void testBeforeActivation() {
        SkiPass skiPass = spy(SKI_PASS_FACTORY.geSkiPass(new SkiPassRequest(FIRST_HALF_DAY, WEEKDAYS)));
        when(skiPass.getNow()).thenReturn(MONDAY_MORNING.minusHours(1));
        assertFalse(skiPass.countLift());
    }

    @Test
    public void testWrongDay() {
        SkiPass skiPass = spy(SKI_PASS_FACTORY.geSkiPass(new SkiPassRequest(FIRST_HALF_DAY, WEEKDAYS)));
        when(skiPass.getNow()).thenReturn(MONDAY_MORNING.minusDays(1)); //Sunday
        assertFalse(skiPass.countLift());
    }




}
