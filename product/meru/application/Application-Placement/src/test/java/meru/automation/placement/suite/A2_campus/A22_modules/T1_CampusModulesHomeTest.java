package meru.automation.placement.suite.A2_campus.A22_modules;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import app.automation.ui.WebTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T1_CampusModulesHomeTest extends WebTest {


  public T1_CampusModulesHomeTest() {
    super("localhost:8080/i2par/campus");

  }

  @Test

  public void pageTest() {

    //********** afterLogin **********

    webPage.click(".//*[@id='header']/div[1]/span[2]");
    //assertModulesContent
    webPage.assertTitle("Campus Admin");
    webPage.assertTextEquals(".//*[@id='workArea']/div/div[1]/div[1]/span", "Modules");
    webPage.assertTextEquals(".//*[@id='contentArea']", "");

  }


}