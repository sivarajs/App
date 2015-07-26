package meru.automation.placement.suite.A1_admin.setup;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import app.automation.ui.WebTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T1_AdminTest extends WebTest {


  public T1_AdminTest() {
    super("localhost:8080/i2par/");

  }

  @Test

  public void pageTest() {

    //********** afterPageLoad **********

    //assertTitle
    webPage.assertTitle("Account");
    webPage.assertTextEquals("html/body/div/div/h2", "Login");

    //********** afterLogin **********

    webPage.setInput(".//*[@id='ui']","super");
    webPage.setInput(".//*[@id='pw']","super");
    webPage.click("html/body/div/div/div/form/div[3]/input");
    //assertAdminPage
    webPage.assertTitle("I2Par Admin");
    webPage.assertTextEquals(".//*[@id='header']/div[1]/span[1]/a", "Setup");
    webPage.assertAttributeEquals(".//*[@id='header']/div[1]/span[1]/a", "class", "menuLabel");
    webPage.assertTextEquals(".//*[@id='header']/div[1]/span[2]/a", "Modules");
    webPage.assertAttributeEquals(".//*[@id='header']/div[1]/span[2]/a", "class", "menuLabel");

    //********** clickMenuSetup **********

    webPage.click(".//*[@id='header']/div[1]/span[1]");
    //assertSetupContent
    webPage.assertTitle("I2Par Admin");
    webPage.assertTextEquals(".//*[@id='workArea']/div/div[1]/div[1]/span", "Modules");
    webPage.assertTextEquals(".//*[@id='contentArea']", "");

    //********** clickSetupItem **********

    webPage.click(".//*[@id='workArea']/div/div[1]/div[2]/div/div/li[1]/ul/li[1]/div");
    //assertSetupItemClick
    webPage.assertTitle("I2Par Admin");
    webPage.assertTextEquals(".//*[@id='workArea']/div/div[1]/div[1]/span", "Modules");
    webPage.assertTextEquals(".//*[@id='contentArea']/div/h1", "PLACEMENT CONFIGURATION");
    webPage.assertNodeCountEquals(".//*[@id='contentArea']", 1);
    webPage.assertNodeCountEquals(".//*[@id='propertyTable']/div[3]/table/tbody/tr", 9);

  }


}