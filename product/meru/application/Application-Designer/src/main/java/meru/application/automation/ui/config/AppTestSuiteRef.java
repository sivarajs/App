package meru.application.automation.ui.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import meru.sys.IOSystem;

@XmlRootElement(name = "test-suite-ref")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppTestSuiteRef {

    @XmlAttribute(required=true)
    private String src;

    
    public String getSrc() {
    	return src;
    }
    
    public AppTestSuite getAppTestSuite(File configDir) throws IOException {
        AppTestSuite testSuite = null;
        FileInputStream inputStream = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(AppTestBag.class);
            Unmarshaller u = jc.createUnmarshaller();

            
            inputStream = new FileInputStream(new File(configDir,src));
            testSuite = (AppTestSuite) u.unmarshal(inputStream);
            
            testSuite.setParentDir(new File(src).getParent());
            
            return testSuite;
        } catch (JAXBException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOSystem.close(inputStream);
        }
   }
}