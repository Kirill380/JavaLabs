package ua.kpi.skilift.skipass;


import org.junit.Assert;
import org.junit.Test;
import ua.kpi.skilift.skipass.types.SeasonType;
import ua.kpi.skilift.transfer.SkiPassRequest;

import java.time.Month;

import static java.time.LocalDateTime.of;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static ua.kpi.skilift.skipass.SkiPassFactory.SKI_PASS_FACTORY;

public class SeasonSkiPassTest extends Assert {

    @Test
    public void testWrongPeriod() {
        SkiPass skiPass = spy(SKI_PASS_FACTORY.geSkiPass(new SkiPassRequest(SeasonType.AUTUMN)));
        when(skiPass.getNow()).thenReturn(of(2016, Month.MARCH, 9, 0, 0));
        assertFalse(skiPass.countLift());
    }
}
