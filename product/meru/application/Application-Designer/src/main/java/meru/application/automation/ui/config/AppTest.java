package meru.application.automation.ui.config;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import meru.io.JavaClassWriter;

@XmlRootElement(name = "test")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppTest {

	@XmlAttribute(required = true)
	private String name;

	@XmlElement(name = "assert", required = true)
	private List<Assert> asserts;

	public void buildTestClass(JavaClassWriter classWriter) throws IOException {
		if (name != null) {
			classWriter.addStatement("//" + name);
		}
		if (asserts != null) {
			for (Assert asert : asserts) {
				asert.buildTestClass(classWriter);
			}
		}
	}
}