package ua.kpi.skilift.skipass;


import org.junit.Assert;
import org.junit.Test;
import ua.kpi.skilift.skipass.types.LiftsType;
import ua.kpi.skilift.transfer.SkiPassRequest;

import java.time.LocalDateTime;
import java.time.Month;

import static java.time.LocalDateTime.of;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static ua.kpi.skilift.skipass.SkiPassFactory.SKI_PASS_FACTORY;
import static ua.kpi.skilift.skipass.types.DayOfWeekType.WEEKDAYS;

public class QuantitativeSkiPassTest  extends Assert {

    private static final LocalDateTime MONDAY_MORNING = of(2016, Month.OCTOBER, 24, 9, 0, 0);

    @Test
    public void testExpired() {
        SkiPass skiPass = spy(SKI_PASS_FACTORY.geSkiPass(new SkiPassRequest(LiftsType.TEN, WEEKDAYS)));
        when(skiPass.getNow()).thenReturn(MONDAY_MORNING);
        for (int i = 0; i < LiftsType.TEN.getAmount(); i++) {
            skiPass.countLift();
        }
        assertFalse(skiPass.countLift());
    }

    @Test
    public void testWrongDay() {
        SkiPass skiPass = spy(SKI_PASS_FACTORY.geSkiPass(new SkiPassRequest(LiftsType.TEN, WEEKDAYS)));
        when(skiPass.getNow()).thenReturn(MONDAY_MORNING.minusDays(1)); //Sunday
        assertFalse(skiPass.countLift());
    }
}
