package meru.application.automation.ui.config;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import meru.io.JavaClassWriter;

@XmlRootElement(name = "assert")
@XmlAccessorType(XmlAccessType.FIELD)
public class Assert {

    @XmlAttribute
    private String entity;
    @XmlAttribute
    private String name;
    @XmlAttribute
    private String operation;
    @XmlAttribute
    private String xpath;
    @XmlAttribute
    private String comment;
    @XmlValue
    private String expectedValue;
    
    public void buildTestClass(JavaClassWriter classWriter) throws IOException {

        if (comment != null) {
            classWriter.addComment(comment);
        }
        
        StringBuilder stmtBuilder = new StringBuilder(40);
        stmtBuilder.append("webPage.assert").append(entity);
        if (entity.equals("Text")) {
            stmtBuilder.append(operation).append("(\"").append(xpath).append("\"");
            if (operation.equals("Equals")) {
                stmtBuilder.append(", \"").append(expectedValue).append("\"");
            }
            stmtBuilder.append(");");
        }
        else if (entity.equals("Attribute")) {
            stmtBuilder.append(operation).append("(\"").append(xpath).append("\", \"").append(name).append("\"");
            if (operation.equals("Contains")) {
            	System.out.println("####");
            }
            if (operation.equals("Equals") || operation.equals("Contains")) {
                stmtBuilder.append(", \"").append(expectedValue).append("\"");
            }
            stmtBuilder.append(");");
        }
        else if (entity.equals("NodeCount")) {
            stmtBuilder.append(operation).append("(\"").append(xpath).append("\"");
            if (expectedValue != null && expectedValue.trim().length() != 0) {
                stmtBuilder.append(", ").append(expectedValue);
            }
            stmtBuilder.append(");");
        }
        else if (entity.equals("Title")) {
            stmtBuilder.append("(\"").append(expectedValue).append("\");");
        }
        
        classWriter.addStatementln(stmtBuilder.toString());
    }

    
}