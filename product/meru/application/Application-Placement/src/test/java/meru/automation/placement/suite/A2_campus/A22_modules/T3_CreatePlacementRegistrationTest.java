package meru.automation.placement.suite.A2_campus.A22_modules;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import app.automation.ui.WebTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T3_CreatePlacementRegistrationTest extends WebTest {


  public T3_CreatePlacementRegistrationTest() {
    super("localhost:8080/i2par/campus");

  }

  @Test

  public void pageTest() {

    //********** selectPlacementRegistrationTreeItem **********

    webPage.click(".//*[@id='header']/div[1]/span[2]");
    webPage.click(".//*[@id='workArea']/div/div[1]/div[2]/div/div/li[2]/ul/li[2]/div/div[1]");
    //assertTitle
    webPage.assertTextEquals(".//*[@id='contentArea']/div/h1", "PLACEMENT REGISTRATION");

    //********** newRegistration **********

    webPage.click(".//*[@id='placementRegistrationTable']/div[1]/div[1]/div[1]");
    webPage.click(".//*[@id='regForm']/div[1]/div[1]/div[2]/input");
    webPage.click(".//*[@id='placementTable']/div[2]/table/tbody/tr");
    webPage.setInput(".//*[@id='regForm']/div[1]/div[2]/div[2]/input","31-10-2014");
    webPage.setInput(".//*[@id='maxOffers']","2");
    webPage.setInput(".//*[@id='regForm']/div[1]/div[4]/div[2]/select","61");
    webPage.click(".//*[@id='placementRegistrationSubmit']");
    //assertTitle
    webPage.assertAttributeEquals(".//*[@id='regForm']/div[1]/div[1]/div[2]/input", "value", "2015");
    webPage.assertTextEquals(".//*[@id='placementRegistrationTable']/div[3]/table/tbody/tr/td[2]/div", "2015");

    //********** newStudent1 **********

    webPage.click(".//*[@id='placementRegistration-StudentTab']/a");
    webPage.click(".//*[@id='stuTable']/div[1]/div[1]/div[1]/span");
    webPage.setInput(".//*[@id='stuForm']/div[1]/div[1]/div[2]/input","StudentId1");
    webPage.setInput(".//*[@id='stuForm']/div[1]/div[2]/div[2]/input","Student1");
    webPage.setInput(".//*[@id='stuForm']/div[1]/div[3]/div[2]/input","sivarajs@gmail.com");
    webPage.click(".//*[@id='stuForm']/div[1]/div[4]/div[2]/input");
    webPage.click(".//*[@id='course1Table']/div[2]/table/tbody/tr[1]");
    webPage.setInput(".//*[@id='stuCgpa']","7");
    webPage.click(".//*[@id='studentSubmit']");
    //assertTitle
    webPage.assertAttributeEquals(".//*[@id='degree1']", "value", "BE - Computer Science");
    webPage.assertTextEquals(".//*[@id='stuTable']/div[3]/table/tbody/tr/td[1]/div", "StudentId1");
    webPage.assertTextEquals(".//*[@id='stuTable']/div[3]/table/tbody/tr/td[2]/div", "Student1");
    webPage.assertTextEquals(".//*[@id='stuTable']/div[3]/table/tbody/tr/td[3]/div", "sivarajs@gmail.com");
    webPage.assertTextEquals(".//*[@id='stuTable']/div[3]/table/tbody/tr/td[4]/div", "");
    webPage.assertTextEquals(".//*[@id='stuTable']/div[3]/table/tbody/tr/td[5]/div", "New");

    //********** newStudent2 **********

    webPage.click(".//*[@id='placementRegistration-StudentTab']/a");
    webPage.click(".//*[@id='stuTable']/div[1]/div[1]/div[1]/span");
    webPage.setInput(".//*[@id='stuForm']/div[1]/div[1]/div[2]/input","StudentId2");
    webPage.setInput(".//*[@id='stuForm']/div[1]/div[2]/div[2]/input","Student2");
    webPage.setInput(".//*[@id='stuForm']/div[1]/div[3]/div[2]/input","sivarajsk@yahoo.com");
    webPage.click(".//*[@id='stuForm']/div[1]/div[4]/div[2]/input");
    webPage.click(".//*[@id='course1Table']/div[2]/table/tbody/tr[1]/td[1]/div");
    webPage.setInput(".//*[@id='stuCgpa']","8");
    webPage.click(".//*[@id='studentSubmit']");
    //assertTitle
    webPage.assertAttributeEquals(".//*[@id='degree1']", "value", "BE - Computer Science");
    webPage.assertTextEquals(".//*[@id='stuTable']/div[3]/table/tbody/tr[2]/td[1]/div", "StudentId2");
    webPage.assertTextEquals(".//*[@id='stuTable']/div[3]/table/tbody/tr[2]/td[2]/div", "Student2");
    webPage.assertTextEquals(".//*[@id='stuTable']/div[3]/table/tbody/tr[2]/td[3]/div", "sivarajsk@yahoo.com");
    webPage.assertTextEquals(".//*[@id='stuTable']/div[3]/table/tbody/tr[2]/td[4]/div", "");
    webPage.assertTextEquals(".//*[@id='stuTable']/div[3]/table/tbody/tr[2]/td[5]/div", "New");

    //********** generateI2parIds **********

    webPage.click(".//*[@id='stuTable']/div[1]/div[1]/div[4]/span");
    //assert I2Par Ids in the student table
    webPage.assertTextNotNull(".//*[@id='stuTable']/div[3]/table/tbody/tr[1]/td[4]/div");
    webPage.assertTextNotNull(".//*[@id='stuTable']/div[3]/table/tbody/tr[2]/td[4]/div");

    //********** rollOutRegistration **********

    webPage.click(".//*[@id='placementRegistrationTable']/div[1]/div[1]/div[4]");
    //assert Registration Form and State in the registration table

  }


}