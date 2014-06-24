package edu.kit.iti.structuredtext.plcopenxml;

/**
 * Created by weigl on 23/06/14.
 */
public class App
{
    public static void main(String[] argv) {
        SFCModelBuilderCodeSys builder = new SFCModelBuilderCodeSys("Scenario0_Final.xml");
        builder.build();
    }

}
