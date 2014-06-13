package edu.kit.iti.structuredtext.datatypes.values;

public class DateAndTimeValue {
    private DateValue date;
    private TimeOfDayValue tod;

    public DateAndTimeValue(DateValue date, TimeOfDayValue tod) {
        this.date = date;
        this.tod = tod;
    }

    public DateAndTimeValue(int years, int months, int days, int hours, int minutes, int seconds){
        setYear(years);
        setMonth(months);
        setDay(days);
        setHours(hours);
        setMinutes(minutes);
        setSeconds(seconds);
    }

    public void setSeconds(int seconds) {
        tod.setSeconds(seconds);
    }

    public int getHours() {
        return tod.getHours();
    }

    public void setHours(int hours) {
        tod.setHours(hours);
    }

    public int getMinutes() {
        return tod.getMinutes();
    }

    public void setMinutes(int minutes) {
        tod.setMinutes(minutes);
    }

    public int getSeconds() {
        return tod.getSeconds();
    }

    public int getYear() {
        return date.getYear();
    }

    public void setDay(int day) {
        date.setDay(day);
    }

    public void setMonth(int month) {
        date.setMonth(month);
    }

    public void setYear(int year) {
        date.setYear(year);
    }

    public int getDay() {
        return date.getDay();
    }

    public int getMonth() {
        return date.getMonth();
    }
}