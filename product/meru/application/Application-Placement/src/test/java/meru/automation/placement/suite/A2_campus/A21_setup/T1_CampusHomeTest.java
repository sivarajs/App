package meru.automation.placement.suite.A2_campus.A21_setup;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import app.automation.ui.WebTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T1_CampusHomeTest extends WebTest {


  public T1_CampusHomeTest() {
    super("localhost:8080/i2par/");

  }

  @Test

  public void pageTest() {

    //********** afterLogin **********

    webPage.setInput(".//*[@id='ui']","BITSP_PO");
    webPage.setInput(".//*[@id='pw']","welcome");
    webPage.click("html/body/div/div/div/form/div[3]/input");
    //assertAdminPage
    webPage.assertTitle("Campus Admin");
    webPage.assertTextEquals(".//*[@id='header']/div[1]/span[1]/a", "Setup");
    webPage.assertAttributeEquals(".//*[@id='header']/div[1]/span[1]/a", "class", "menuLabel");
    webPage.assertTextEquals(".//*[@id='header']/div[1]/span[2]/a", "Modules");
    webPage.assertAttributeEquals(".//*[@id='header']/div[1]/span[2]/a", "class", "menuLabel");

    //********** clickMenuSetup **********

    webPage.click(".//*[@id='header']/div[1]/span[1]");
    //assertSetupContent
    webPage.assertTitle("Campus Admin");
    webPage.assertTextEquals(".//*[@id='workArea']/div/div[1]/div[1]/span", "Modules");
    webPage.assertTextEquals(".//*[@id='contentArea']", "");

  }


}