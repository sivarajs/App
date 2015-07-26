package meru.application.automation.ui;

import java.io.File;
import java.io.IOException;

import meru.application.automation.ui.config.AppTestBag;

public class TestAppBuilder {

    public TestAppBuilder() {
    }

    public void build(File appRoot) {
        File configFile = new File(appRoot,
                                   "metadata/automation/test-config.xml");
        
        if (!configFile.exists()) {
            return;
        }
        
        File targetDir = new File(appRoot, "src/test/java");
        build(configFile,
              targetDir);
    }

    void build(File testConfigFile, File targetDir) {
        AppTestBag testConfig = AppTestBag.createAppTestBag(testConfigFile,
                                                            targetDir);

        try {
            testConfig.buildTestClass(targetDir);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String s = "D:/dev/mydev/project/kaga/application/" + args[0]
                + "/metadata/automation/test-config.xml";
        String targetDir = "D:/dev/mydev/project/kaga/application/" + args[0]
                + "/src/test/java/kaga/automation/" + args[1];
        File file = new File(s);
        TestAppBuilder testAppBuilder = new TestAppBuilder();
        testAppBuilder.build(file,
                             new File(targetDir));
    }

}
