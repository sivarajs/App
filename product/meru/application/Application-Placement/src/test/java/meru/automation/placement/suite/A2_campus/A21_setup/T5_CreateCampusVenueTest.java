package meru.automation.placement.suite.A2_campus.A21_setup;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import app.automation.ui.WebTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T5_CreateCampusVenueTest extends WebTest {


  public T5_CreateCampusVenueTest() {
    super("localhost:8080/i2par/campus");

  }

  @Test

  public void pageTest() {

    //********** selectCampusProgramTreeItem **********

    webPage.click(".//*[@id='header']/div[1]/span[1]");
    webPage.click(".//*[@id='workArea']/div/div[1]/div[2]/div/div/li[2]/ul/li[5]/div/div[1]");
    //assertTitle
    webPage.assertTextEquals(".//*[@id='contentArea']/div/h1", "CAMPUS VENUE");

    //********** newCampusVenue1 **********

    webPage.click(".//*[@id='campusVenueTable']/div[1]/div[1]/div[1]");
    webPage.setInput(".//*[@id='CampusVenueForm']/div[1]/div[1]/div[2]/input","Venue1");
    webPage.click(".//*[@id='campusVenueSubmit']");
    //assertTitle
    webPage.assertAttributeEquals(".//*[@id='CampusVenueForm']/div[1]/div[1]/div[2]/input", "value", "Venue1");

    //********** newCampusVenue2 **********

    webPage.click(".//*[@id='campusVenueTable']/div[1]/div[1]/div[1]");
    webPage.setInput(".//*[@id='CampusVenueForm']/div[1]/div[1]/div[2]/input","Venue2");
    webPage.click(".//*[@id='campusVenueSubmit']");
    //assertTitle
    webPage.assertAttributeEquals(".//*[@id='CampusVenueForm']/div[1]/div[1]/div[2]/input", "value", "Venue2");

  }


}