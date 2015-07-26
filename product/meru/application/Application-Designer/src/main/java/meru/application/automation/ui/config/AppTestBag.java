package meru.application.automation.ui.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import meru.sys.IOSystem;

@XmlRootElement(name = "test-app")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppTestBag {

    @XmlAttribute(required=true)
    private String baseURL;
 
    @XmlAttribute(required=true)
    private String basePackage;
    
    @XmlElement(name = "test-suite-ref")
    private List<AppTestSuiteRef> testSuiteRefs;
    
    @XmlElement(name = "test-suite")
    private List<AppTestSuite> testSuites;
    
    public void buildTestClass(File targetDir) throws IOException {
        
    	if (!baseURL.endsWith("/")) {
    		baseURL +=  "/";
    	}
    	
    	
    	File srcDir = (basePackage == null) ? targetDir : new File(targetDir,basePackage.replace('.', '/'));
    	IOSystem.delete(srcDir);
        for (AppTestSuite testSuite : testSuites) {
            testSuite.buildTestClass(baseURL, basePackage, srcDir);
        }
        
    }
    
    public static AppTestBag createAppTestBag(File testConfigFile, File targetDir) {
        AppTestBag testConfig = null;

        FileInputStream inputStream = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(AppTestBag.class);
            Unmarshaller u = jc.createUnmarshaller();

            
            inputStream = new FileInputStream(testConfigFile);
            testConfig = (AppTestBag) u.unmarshal(inputStream);
            if (!targetDir.exists()) {
                targetDir.mkdirs();
            }
            
            if (testConfig.testSuites == null) {
                testConfig.testSuites = new ArrayList<AppTestSuite>(5);
            }
            
            if (testConfig.testSuiteRefs != null) {
                for (AppTestSuiteRef testSuiteRef : testConfig.testSuiteRefs) {
                    testConfig.testSuites.add(testSuiteRef.getAppTestSuite(testConfigFile.getParentFile()));
                }
            }
            
            
            testConfig.buildTestClass(targetDir);
            
            
            
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOSystem.close(inputStream);
        }
        
        return testConfig;
    }
}