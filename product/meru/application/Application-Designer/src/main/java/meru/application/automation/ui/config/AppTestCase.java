package meru.application.automation.ui.config;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import meru.io.JavaClassWriter;

@XmlRootElement(name = "test-case")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppTestCase {

    @XmlAttribute(required=true)
    private String name;

    @XmlElement(name = "input")
    private List<TestInput> inputs;

    @XmlElement(name = "test")
    private List<AppTest> tests;

    public void buildTestClass(JavaClassWriter classWriter,
                               int index) throws IOException {

        // classWriter.addFieldMethodAnnotration("Test")
        // .startMethod("t"+index+"_"+name);

        classWriter.newLine()
                   .addComment("********** " + name + " **********")
                   .newLine();
        if (inputs != null) {
            for (TestInput input : inputs) {
                input.buildTestClass(classWriter);
            }
        }

        if (tests != null) {
            for (AppTest test : tests) {
                test.buildTestClass(classWriter);
            }
        }

        // classWriter.endMethod();
    }
}