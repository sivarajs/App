package meru.automation.placement.suite.A2_campus.A22_modules;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import app.automation.ui.WebTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T5_CreateRFPTest extends WebTest {


  public T5_CreateRFPTest() {
    super("localhost:8080/i2par/campus");

  }

  @Test

  public void pageTest() {

    //********** selectRFPTreeItem **********

    webPage.click(".//*[@id='header']/div[1]/span[2]");
    webPage.click(".//*[@id='workArea']/div/div[1]/div[2]/div/div/li[2]/ul/li[4]/div/div[1]");
    //assertTitle
    webPage.assertTextEquals(".//*[@id='contentArea']/div/h1", "RFP");

    //********** newRFP **********

    webPage.click(".//*[@id='rfpTable']/div[1]/div[1]/div[1]");
    webPage.click(".//*[@id='rfpNewPopup']/div[2]/div/div/div/a[1]");
    webPage.click(".//*[@id='RfpForm']/div[1]/div[1]/div[2]/input");
    webPage.click(".//*[@id='placementRegistrationTable']/div[2]/table/tbody/tr");
    webPage.setInput(".//*[@id='RfpForm']/div[1]/div[2]/div[2]/input","31-10-2015");
    webPage.setInput(".//*[@id='RfpForm']/div[1]/div[3]/div[2]/select","121");
    webPage.setInput(".//*[@id='RfpForm']/div[1]/div[4]/div[2]/select","61");
    webPage.setInput(".//*[@id='RfpForm']/div[1]/div[5]/div[2]/input","10");
    webPage.setInput(".//*[@id='RfpForm']/div[1]/div[6]/div[2]/input","1000");
    webPage.setInput(".//*[@id='RfpForm']/div[1]/div[7]/div[2]/input","999");
    webPage.click(".//*[@id='rfpSubmit']");
    //assertTitle
    webPage.assertAttributeEquals(".//*[@id='RfpForm']/div[1]/div[1]/div[2]/input", "value", "BITSPRG0000000001");
    webPage.assertTextEquals(".//*[@id='rfpTable']/div[4]/table/tbody/tr/td[1]/div", "BITSPRF0000000001");
    webPage.assertTextEquals(".//*[@id='rfpTable']/div[4]/table/tbody/tr/td[2]/div", "2015");

    //********** dispatchRFP **********

    webPage.click(".//*[@id='rfpTable']/div[1]/div[1]/div[4]/span");
    webPage.setInput(".//*[@id='empEmails']","sivarajs@gmail.com,sivarajsk@yahoo.com");
    webPage.click(".//*[@id='rfpDispatchSubmit']");

  }


}