package app.automation.ui;

import java.util.concurrent.TimeUnit;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AppTestListener extends RunListener {

    @Override
    public void testRunStarted(Description description) throws Exception {
    	
    	String webDriverClass = FirefoxDriver.class.getName();
    	WebDriver webDriver = null;
    	try {
            webDriver = (WebDriver) Class.forName(webDriverClass)
                                         .newInstance();
            webDriver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    	webDriver.manage().window().maximize();
    	WebTest.init(webDriver);
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
       // AppTest.finish();
        
    }

    
}
