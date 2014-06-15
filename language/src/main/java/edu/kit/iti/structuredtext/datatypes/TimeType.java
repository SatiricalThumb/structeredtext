package edu.kit.iti.structuredtext.datatypes;

import edu.kit.iti.structuredtext.datatypes.values.TimeValue;

public class TimeType extends Any {
    public static final TimeType TIME_TYPE = new TimeType();

    @Override
    public String repr(Object obj) {
        TimeValue time = (TimeValue) obj;
        return String.format("TIME#%fd_%fh_%fm_%fs_%fms",
                time.getDays(), time.getHours(), time.getMinutes(), time.getSeconds(), time.getMillieseconds());

    }
}