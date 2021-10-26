package com.learn.java.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.time.Month.FEBRUARY;
import static java.time.format.DateTimeFormatter.ofLocalizedDateTime;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.time.format.FormatStyle.MEDIUM;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.util.Locale.UK;

public class DateTimeAPi {

    public static void getLocalDateInformation() {

        System.out.println("Local Date Time Information : ");
        // get current date and time
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println("current date time: " + currentTime);

        // get date alone
        LocalDate d1 = currentTime.toLocalDate();
        System.out.println("current date: " + d1);

        // extract date properties
        Month month = currentTime.getMonth();
        System.out.println("Month: " + month.getValue());
        int dayOfMonth = currentTime.getDayOfMonth();
        System.out.println("Day of the month: " + dayOfMonth);
        int year = currentTime.getYear();
        System.out.println("Year: " + year);

        // extract time properties
        int hour = currentTime.getHour();
        System.out.println("current hour: " + hour);
        int minutes = currentTime.getMinute();
        System.out.println("current minutes: " + minutes);
        int seconds = currentTime.getSecond();
        System.out.println("current seconds: " + seconds);
    }

    public static void getZonedDateInformation() {

        System.out.println("Zoned Date Time Information : ");

        ZonedDateTime zoneDateTime = ZonedDateTime.now();
        System.out.println("current date time with zone: " + zoneDateTime);

        // get date alone
        LocalDate d1 = zoneDateTime.toLocalDate();
        System.out.println("current date: " + d1);

        // get zone info
        ZoneId zone = zoneDateTime.getZone();
        System.out.println("zone info: " + zone.getId());

        // extract date properties
        Month mnt = zoneDateTime.getMonth();
        System.out.println("Month: " + mnt.getValue());
        int dayOfMonth = zoneDateTime.getDayOfMonth();
        System.out.println("Day of the month: " + dayOfMonth);
        int year = zoneDateTime.getYear();
        System.out.println("Year: " + year);

        // extract time properties
        int hour = zoneDateTime.getHour();
        System.out.println("current hour: " + hour);
        int minutes = zoneDateTime.getMinute();
        System.out.println("current minutes: " + minutes);
        int seconds = zoneDateTime.getSecond();
        System.out.println("current seconds: " + seconds);
    }

    public static void localDateUtilityMethods() {

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        System.out.println("Tomorrow : " + tomorrow);

        LocalDate previousMonthSameDay = LocalDate.now().minus(1, MONTHS);
        System.out.println("Previous Month Same day : " + previousMonthSameDay);

        boolean isLeapYear = LocalDate.now().isLeapYear();
        System.out.println("Is current year leap year ? " + isLeapYear);

        boolean isBefore = LocalDate.parse("2016-06-12")
                                    .isBefore(LocalDate.parse("2016-06-11"));
        System.out.println("Does 2016-06-12 comes before 2016-06-11 ? " + isBefore);

        boolean isAfter = LocalDate.parse("2016-06-12")
                                   .isAfter(LocalDate.parse("2016-06-11"));
        System.out.println("Does 2016-06-12 comes after 2016-06-11 ? " + isAfter);

        LocalDateTime beginningOfDay = LocalDate.parse("2016-06-12").atStartOfDay();
        System.out.println("Beginning of the day : " + beginningOfDay);

        LocalDate firstDayOfMonth = LocalDate.parse("2016-06-12")
                                             .with(firstDayOfMonth());
        System.out.println("first day of month : " + firstDayOfMonth);
    }

    public static void localTimeUtilityMethods() {
        LocalTime now = LocalTime.now();
        System.out.println("Local Time : " + now);

        LocalTime sixThirty = LocalTime.parse("06:30");
        System.out.println("Six Thirty : " + sixThirty);

        LocalTime sevenThirty = LocalTime.of(7, 30);
        System.out.println("Seven Thirty : " + sevenThirty);

        LocalTime eightThirty = LocalTime.parse("08:30").plus(1, HOURS);
        System.out.println("Eight Thirty : " + eightThirty);

        int six = LocalTime.parse("06:30").getHour();
        System.out.println("Six : " + six);

        boolean isBefore = LocalTime.parse("06:30").isBefore(LocalTime.parse("07:30"));
        System.out.println("is 6:30 before 7:30 ? " + isBefore);

        LocalTime maxTime = LocalTime.MAX;
        System.out.println("Max Time : " + maxTime);
    }

    public static void localDateTimeUtilityMethods() {

        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current Date & Time : " + now);
        LocalDateTime localDateTime = LocalDateTime.of(2015, FEBRUARY, 20, 6, 30);
        System.out.println("Local Date & Time : " + localDateTime);

        LocalDateTime parsedDateTime = LocalDateTime.parse("2015-02-20T06:30:00");
        System.out.println("Parsed Date & Time : " + parsedDateTime);

        LocalDateTime nextDay = localDateTime.plusDays(1);
        System.out.println("Next Day : " + nextDay);

        LocalDateTime previousDay = localDateTime.minusHours(2);
        System.out.println("Previous Day : " + previousDay);

        String formattedDate = localDateTime.format(ofPattern("yyyy/MM/dd"));
        System.out.println("Formatted Date : " + formattedDate);

        //We can pass in formatting style either as SHORT, LONG or MEDIUM as part of the formatting option.
        String date = localDateTime.format(ofLocalizedDateTime(MEDIUM).withLocale(UK));
        System.out.println("Formatted Date : " + date);
    }

    public static void main(String[] args) {

        DateTimeAPi.getLocalDateInformation();
        DateTimeAPi.getZonedDateInformation();
        DateTimeAPi.localDateTimeUtilityMethods();
        DateTimeAPi.localDateUtilityMethods();
        DateTimeAPi.localTimeUtilityMethods();
    }
}
