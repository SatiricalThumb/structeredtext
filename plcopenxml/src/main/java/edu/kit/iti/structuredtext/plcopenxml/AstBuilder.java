package edu.kit.iti.structuredtext.plcopenxml;

import edu.kit.iti.plcopenxml.xsd.ObjectFactory;
import edu.kit.iti.plcopenxml.xsd.Project;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by weigl on 23/06/14.
 */
public class AstBuilder
{
    private File filename;


    public AstBuilder(String s) {
    filename = new File(s);
    }

    protected Project loadXml() throws JAXBException {
            JAXBContext jc = JAXBContext.newInstance(Project.class);
            Unmarshaller u = jc.createUnmarshaller();
            Project project = (Project) u.unmarshal(filename);
            return project;
    }

    public PLCGraph build() {
        try {
            Project p = loadXml();




        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
