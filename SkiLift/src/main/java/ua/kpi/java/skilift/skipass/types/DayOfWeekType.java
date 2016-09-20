package ua.kpi.java.skilift.skipass.types;


import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum DayOfWeekType {
    WEEKENDS(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY),
    WEEKDAYS(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);

    private final Set<DayOfWeek> dayOfWeek;


    DayOfWeekType(DayOfWeek... dayOfWeek) {
        this.dayOfWeek = new HashSet<>(Arrays.asList(dayOfWeek));
    }

    public boolean isRightDay(DayOfWeek day) {
        return dayOfWeek.contains(day);
    }
}
