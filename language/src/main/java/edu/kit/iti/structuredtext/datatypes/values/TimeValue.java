package edu.kit.iti.structuredtext.datatypes.values;

/**
 * Created by weigl on 11.06.14.
 */
public class TimeValue {
    private long value;

    public TimeValue(int days, int hours, int minutes, int seconds, int millieseconds)
    {
        this.value = ((((days*24)+hours)*60+minutes)*60+seconds)*1000+millieseconds;
    }
}
