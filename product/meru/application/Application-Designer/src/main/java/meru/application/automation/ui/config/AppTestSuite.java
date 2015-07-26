package meru.application.automation.ui.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import meru.io.JavaClassWriter;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@XmlRootElement(name = "test-suite")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppTestSuite {

	@XmlAttribute(required = true)
	private String name;

	@XmlAttribute(required = true)
	private String relativeUrl;

	@XmlElement(name = "test-case")
	private List<AppTestCase> testCases;

	private String parentDir;

	void setParentDir(String parentDir) {
		this.parentDir = parentDir;
	}

	public void buildTestClass(	String baseURL,
								String basePackage,
								File targetDir) throws IOException {

		String packageName = basePackage;
		
		if (parentDir != null) {
			
			packageName += "."+parentDir.replaceAll("[/\\\\]", ".");
			
			targetDir = new File(targetDir, parentDir);
			if (!targetDir.exists()) {
				targetDir.mkdirs();
			}
		}

		File classFile = new File(targetDir, name + ".java");

		try (FileWriter fileWriter = new FileWriter(classFile)) {

			JavaClassWriter classWriter = new JavaClassWriter(fileWriter);

			classWriter.addPackage(packageName)
						.addImport(Test.class.getName())
						.addImport(FixMethodOrder.class.getName())
						.addImport(MethodSorters.class.getName())
						.addImport("app.automation.ui.WebTest")
						.newLine()
						.addClassAnnotration(FixMethodOrder.class.getSimpleName()
								+ "("
								+ MethodSorters.class.getSimpleName()
								+ ".NAME_ASCENDING)")
						.startClass(name, "WebTest");

			classWriter.newLine()
						.startConstructor()
						.addStatement("super(\"" + baseURL + relativeUrl
								+ "\");")
						.endConstructor();

			if (testCases != null) {

				classWriter.addFieldMethodAnnotration("Test")
							.startMethod("pageTest");

				int index = 0;
				for (AppTestCase testCase : testCases) {
					testCase.buildTestClass(classWriter, ++index);
				}

				classWriter.endMethod();
			}

			classWriter.endClass();
		}
	}
}