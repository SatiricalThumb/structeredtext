package edu.kit.iti.structuredtext.ast;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weigl on 13.06.14.
 */
public class StructureInitialization implements Initialization {
    private Map<String, Initialization> initValues = new HashMap<>();
    private String structureName;


    public Map<String, Initialization> getInitValues() {
        return initValues;
    }

    public void setInitValues(Map<String, Initialization> initValues) {
        this.initValues = initValues;
    }

    public void addField(String s, Initialization init) {
        initValues.put(s, init);
    }

    public String getStructureName() {
        return structureName;
    }

    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }
}
