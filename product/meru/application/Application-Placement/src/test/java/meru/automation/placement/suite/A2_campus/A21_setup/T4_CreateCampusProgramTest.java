package meru.automation.placement.suite.A2_campus.A21_setup;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import app.automation.ui.WebTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T4_CreateCampusProgramTest extends WebTest {


  public T4_CreateCampusProgramTest() {
    super("localhost:8080/i2par/campus");

  }

  @Test

  public void pageTest() {

    //********** selectCampusProgramTreeItem **********

    webPage.click(".//*[@id='header']/div[1]/span[1]");
    webPage.click(".//*[@id='workArea']/div/div[1]/div[2]/div/div/li[2]/ul/li[4]/div/div[1]");
    //assertTitle
    webPage.assertTextEquals(".//*[@id='contentArea']/div/h1", "CAMPUS PROGRAM");

    //********** newCampusProgram1 **********

    webPage.click(".//*[@id='campusProgramTable']/div[1]/div[1]/div[1]");
    webPage.setInput(".//*[@id='CampusProgramForm']/div[1]/div[1]/div[2]/select","11");
    webPage.click(".//*[@id='campusProgramSubmit']");
    //assertTitle
    webPage.assertAttributeEquals(".//*[@id='CampusProgramForm']/div[1]/div[1]/div[2]/select", "value", "11");

    //********** newCampusProgram2 **********

    webPage.click(".//*[@id='campusProgramTable']/div[1]/div[1]/div[1]");
    webPage.setInput(".//*[@id='CampusProgramForm']/div[1]/div[1]/div[2]/select","14");
    webPage.click(".//*[@id='campusProgramSubmit']");
    //assertTitle
    webPage.assertAttributeEquals(".//*[@id='CampusProgramForm']/div[1]/div[1]/div[2]/select", "value", "14");

  }


}