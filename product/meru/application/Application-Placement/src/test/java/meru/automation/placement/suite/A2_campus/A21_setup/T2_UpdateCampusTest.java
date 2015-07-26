package meru.automation.placement.suite.A2_campus.A21_setup;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import app.automation.ui.WebTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T2_UpdateCampusTest extends WebTest {


  public T2_UpdateCampusTest() {
    super("localhost:8080/i2par/campus");

  }

  @Test

  public void pageTest() {

    //********** selectCampusTreeItem **********

    webPage.click(".//*[@id='header']/div[1]/span[1]");
    webPage.click(".//*[@id='workArea']/div/div[1]/div[2]/div/div/li[2]/ul/li[1]/div/div[1]");
    //assertTitle
    webPage.assertTextEquals(".//*[@id='contentArea']/div/h1", "CAMPUS");

    //********** selectCampus **********

    webPage.click(".//*[@id='campusTable']/div[2]/table/tbody/tr");
    //assertTitle
    webPage.assertAttributeEquals(".//*[@id='CampusForm']/div[1]/div[1]/div[2]/div[2]/input", "disabled", "true");

    //********** updateCampus **********

    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[1]/div[9]/div[2]/select","1");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[1]/div[10]/div[2]/select","22");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[3]/div[2]/div[2]/select","41");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[3]/div[3]/div[2]/select","51");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[3]/div[4]/div[2]/select","71");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[4]/div[2]/div[2]/input","6");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[4]/div[3]/div[2]/select","93");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[4]/div[4]/div[2]/select","81");
    webPage.click(".//*[@id='campusSubmit']");
    //assertTitle
    webPage.assertAttributeEquals(".//*[@id='CampusForm']/div[1]/div[1]/div[2]/div[2]/input", "disabled", "true");
    webPage.assertAttributeEquals(".//*[@id='CampusForm']/div[1]/div[4]/div[2]/div[2]/input", "value", "6");

  }


}