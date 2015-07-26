package meru.automation.placement.suite.A1_admin.setup;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import app.automation.ui.WebTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T3_CampusTest extends WebTest {


  public T3_CampusTest() {
    super("localhost:8080/i2par/admin");

  }

  @Test

  public void pageTest() {

    //********** selectCampusTreeItem **********

    webPage.click(".//*[@id='header']/div[1]/span[1]");
    webPage.click(".//*[@id='workArea']/div/div[1]/div[2]/div/div/li[3]/ul/li[2]/div/div[1]");
    //assertTitle
    webPage.assertTextEquals(".//*[@id='contentArea']/div/h1", "CAMPUS");

    //********** newCampus **********

    webPage.click(".//*[@id='campusTable']/div[1]/div[1]/div[1]");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[1]/div[2]/div[2]/input","BITSP");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[1]/div[3]/div[2]/input","Birla Institute Of Technology");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[1]/div[4]/div[2]/input","BITS Pilani");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[1]/div[5]/div[2]/select","1");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[1]/div[6]/div[2]/input","bitspilani.com");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[1]/div[7]/div[2]/input","01-05-1980");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[1]/div[8]/div[2]/input","01-05-1980");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[1]/div[9]/div[2]/input","1000");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[2]/div[2]/div[2]/input","Pilani");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[2]/div[3]/div[2]/select","1");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[2]/div[4]/div[2]/select","1");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[2]/div[5]/div[2]/select","18");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[2]/div[6]/div[2]/select","1");
    webPage.setInput(".//*[@id='CampusForm']/div[1]/div[2]/div[7]/div[2]/input","123456");
    webPage.click(".//*[@id='campusSubmit']");

  }


}