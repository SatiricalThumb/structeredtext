package edu.kit.iti.structuredtext.plcopenxml;

/**
 * Created by weigl on 23/06/14.
 */
public class App
{
    public static void main(String[] argv) {
        AstBuilder builder = new AstBuilder("example.xml");
        builder.build();
    }

}
