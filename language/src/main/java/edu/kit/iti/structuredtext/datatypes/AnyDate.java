package edu.kit.iti.structuredtext.datatypes;

public abstract class AnyDate extends Any {
    public static class Date extends AnyDate {
    }

    public static class TimeOfDay extends AnyDate {
    }

    public static class DateAndTime extends AnyDate {
    }

    public static final Date DATE = new Date();
    public static final TimeOfDay TIME_OF_DAY = new TimeOfDay();
    public static final DateAndTime DATE_AND_TIME = new DateAndTime();
}