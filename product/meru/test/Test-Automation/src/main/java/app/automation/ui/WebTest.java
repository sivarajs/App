package app.automation.ui;

import org.openqa.selenium.WebDriver;

public class WebTest {

    protected static WebDriver webDriver;

    protected WebPage webPage;

    public WebTest(String url) {
        webPage = new WebPage(webDriver);
        webPage.load(url);
    }

    public static void init(WebDriver webDriver) {
        WebTest.webDriver =  webDriver;
    }

    public static void finish() {
       webDriver.quit();
    }

}
