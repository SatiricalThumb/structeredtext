package edu.kit.iti.structuredtext.ast;

import edu.kit.iti.structuredtext.datatypes.EnumerateType;
import edu.kit.iti.structuredtext.datatypes.values.ScalarValue;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by weigl on 13.06.14.
 */
public class StructureTypeDeclaration extends TypeDeclaration {
    Map<String, TypeDeclaration> fields = new HashMap<>();

    public void addField(String s, SimpleTypeDeclaration ast) {

    }

    public void addField(String s, SubRangeDataType ast) {
    }

    public void addField(String s, ScalarValue<EnumerateType, String> enumeratedValue) {

    }

    public void addField(String s, ArrayTypeDeclaration ast) {

    }

    public void addField(String s, StructureInitialization ast) {

    }
}
