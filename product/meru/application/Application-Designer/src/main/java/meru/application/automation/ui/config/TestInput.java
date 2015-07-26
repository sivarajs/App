package meru.application.automation.ui.config;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import meru.io.JavaClassWriter;

@XmlRootElement(name = "input")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestInput {

    @XmlAttribute
    private String type;
    @XmlAttribute
    private String xpath;
    @XmlValue
    private String value;
    
    public void buildTestClass(JavaClassWriter classWriter) throws IOException {

        if (type.equals("click")) {
            classWriter.addStatement("webPage.click(\""+xpath+"\");");

        }
        else if (type.equals("text")) {
            classWriter.addStatement("webPage.setInput(\""+xpath+"\",\""+value+"\");");

        }
        else if (type.equals("alert")) {
            if (value.equalsIgnoreCase("OK")) {
                classWriter.addStatement("webPage.clickAlertOK();");
            }
            else {
                classWriter.addStatement("webPage.clickAlertCancel();");
            }
        }

    }

}