package ru.javawebinar.topjava.util;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // HSQLDB doesn't support LocalDate.MIN/MAX
    private static final LocalDate MIN_DATE = LocalDate.of(1, 1, 1);
    private static final LocalDate MAX_DATE = LocalDate.of(3000, 1, 1);

    private DateTimeUtil() {
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static @Nullable LocalDate parseLocalDate(@Nullable String str) {
        return StringUtils.isEmpty(str) ? null : LocalDate.parse(str);
    }

    public static @Nullable LocalTime parseLocalTime(@Nullable String str) {
        return StringUtils.isEmpty(str) ? null : LocalTime.parse(str);
    }

    public static LocalDateTime getStartInclusive(LocalDate localDate) {
        return startOfDay(localDate != null ? localDate : MIN_DATE);
    }

    public static LocalDateTime getEndExclusive(LocalDate localDate) {
        return startOfDay(localDate != null ? localDate.plus(1, ChronoUnit.DAYS) : MAX_DATE);
    }

    private static LocalDateTime startOfDay(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MIN);
    }

    private static LocalDateTime endOfDay(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MAX.withNano(0));
    }

    public static LocalDateTime getEndInclusive(LocalDate localDate) {
        return endOfDay(localDate != null ? localDate : MAX_DATE);
    }

    public static String convertLocalDateTimeToString(LocalDateTime ldt){
        String s=null;
        s=addThreeZero(ldt.getYear()) + "-" + addZero(ldt.getMonthValue()) + "-" + addZero(ldt.getDayOfMonth()) + " "
                + addZero(ldt.getHour()) + ":" + addZero(ldt.getMinute()) + ":" + addZero(ldt.getSecond());

        //s=s.replace("T"," ");
        return s;
    }

    private static String addZero(Integer n){
        return n>=0 && n<10 ? "0"+n : n.toString();
    }

    private static String addThreeZero(Integer n){
        return n>=0 && n<10 ? "000"+n : n.toString();
    }


}

