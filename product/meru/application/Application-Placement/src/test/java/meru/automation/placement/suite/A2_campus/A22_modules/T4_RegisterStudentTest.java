package meru.automation.placement.suite.A2_campus.A22_modules;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import app.automation.ui.WebTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T4_RegisterStudentTest extends WebTest {


  public T4_RegisterStudentTest() {
    super("localhost:8080/i2par/student");

  }

  @Test

  public void pageTest() {

    //********** loginToStudent1Home **********

    webPage.click(".//*[@id='header']/div[2]/a");
    webPage.setInput(".//*[@id='ui']","BITSP1500001");
    webPage.setInput(".//*[@id='pw']","welcome");
    webPage.click("html/body/div/div/div/form/div[3]/input");
    webPage.click(".//*[@id='workArea']/div/div[1]/div[2]/div/div/li/ul/li[1]/div/div[1]");
    webPage.click(".//*[@id='regButtons']/input[1]");
    webPage.click(".//*[@id='header']/div[2]/a");

    //********** loginToStudent21Home **********

    webPage.setInput(".//*[@id='ui']","BITSP1500002");
    webPage.setInput(".//*[@id='pw']","welcome");
    webPage.click("html/body/div/div/div/form/div[3]/input");
    webPage.click(".//*[@id='workArea']/div/div[1]/div[2]/div/div/li/ul/li[1]/div/div[1]");
    webPage.click(".//*[@id='regButtons']/input[1]");
    webPage.click(".//*[@id='header']/div[2]/a");
    webPage.setInput(".//*[@id='ui']","BITSP_PO");
    webPage.setInput(".//*[@id='pw']","welcome");
    webPage.click("html/body/div/div/div/form/div[3]/input");

  }


}