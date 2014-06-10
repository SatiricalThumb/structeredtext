package edu.kit.iti.structuredtext.ast;

import com.google.common.base.Strings;
import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Top {
    public String getName() {
        return getClass().getName();
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(0, sb);
        return sb.toString();
    }

    protected void toString(int indent, StringBuilder sb) {
        Map<String, Object> fields = new HashMap<>();
        Top.toString(this, getClass(), fields);

        sb.append("(").append(getClass().getCanonicalName()).append("\n");

        for (Map.Entry<String, Object> e : fields.entrySet()) {
            String tab = Strings.repeat(" ", indent + 3 + e.getKey().length());

            sb.append(Strings.repeat(" ", indent + 1)).append(':').append(e.getKey()).append(' ');
            if (e.getValue() instanceof Top) {
                Top a = (Top) e.getValue();
                a.toString(indent + 3+e.getKey().length(), sb);
            } else {
                sb.append(e.getValue());
            }
            sb.append("\n");
        }
        sb.setLength(sb.length()-1);

        sb.append(")");

    }

    private static void toString(Object o, Class<?> clazz, Map<String, Object> list) {
        Field f[] = clazz.getDeclaredFields();
        AccessibleObject.setAccessible(f, true);

        if (clazz.getSuperclass().getSuperclass() != null) {
            toString(o, clazz.getSuperclass(), list);
        }

        for (int i = 0; i < f.length; i++) {
            try {
                list.put(f[i].getName(), f[i].get(o));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
