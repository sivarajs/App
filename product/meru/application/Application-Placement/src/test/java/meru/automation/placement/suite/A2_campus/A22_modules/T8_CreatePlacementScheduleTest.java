package meru.automation.placement.suite.A2_campus.A22_modules;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import app.automation.ui.WebTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T8_CreatePlacementScheduleTest extends WebTest {


  public T8_CreatePlacementScheduleTest() {
    super("localhost:8080/i2par/campus");

  }

  @Test

  public void pageTest() {

    //********** login **********

    webPage.click(".//*[@id='header']/div[2]/a");
    webPage.setInput(".//*[@id='ui']","BITSP_PO");
    webPage.setInput(".//*[@id='pw']","welcome");
    webPage.click("html/body/div/div/div/form/div[3]/input");

    //********** selectPlacementScheduleTreeItem **********

    webPage.click(".//*[@id='header']/div[1]/span[2]");
    webPage.click(".//*[@id='workArea']/div/div[1]/div[2]/div/div/li[3]/ul/li[1]/div/div[1]");
    //assertTitle
    webPage.assertTextEquals(".//*[@id='contentArea']/div/h1", "PLACEMENT SCHEDULE");

    //********** newPlacementSchedule **********

    webPage.click(".//*[@id='placementScheduleTable']/div[1]/div[1]/div[1]");
    webPage.click(".//*[@id='scheduleNewPopup']/div[2]/div/div/a[2]");
    webPage.click(".//*[@id='placementScheduleForm']/div[1]/div[1]/div[2]/input");
    webPage.click(".//*[@id='rfpTable']/div[2]/table/tbody/tr");
    webPage.setInput(".//*[@id='placementScheduleForm']/div[1]/div[2]/div[2]/input","31-10-2015");
    webPage.click(".//*[@id='placementScheduleSubmit']");
    //assertTitle
    webPage.assertAttributeEquals(".//*[@id='placementScheduleForm']/div[1]/div[1]/div[2]/input", "value", "BITSPRF0000000001");
    webPage.assertTextEquals(".//*[@id='placementScheduleTable']/div[4]/table/tbody/tr/td[1]/div", "BITSPSH0000000001");
    webPage.assertTextEquals(".//*[@id='placementScheduleTable']/div[4]/table/tbody/tr/td[2]/div", "BITSPRF0000000001");
    webPage.assertTextEquals(".//*[@id='placementScheduleTable']/div[4]/table/tbody/tr/td[3]/div", "BITSPRG0000000001");
    webPage.assertTextEquals(".//*[@id='placementScheduleTable']/div[4]/table/tbody/tr/td[4]/div", "New");

    //********** updateEmployerInterviewSchedulePPTVenue **********

    webPage.click(".//*[@id='null-Employer_Interview_ScheduleTab']/a");
    webPage.click(".//*[@id='employerScheduleTable']/div[2]/table/tbody/tr[1]");
    webPage.click(".//*[@id='employerInterviewTable']/div[3]/table/tbody/tr[1]");
    webPage.setInput(".//*[@id='EmployerInterviewForm']/div[1]/div[2]/div[2]/select","1");
    webPage.click(".//*[@id='employerInterviewSubmit']");
    //assertTitle
    webPage.assertTextEquals(".//*[@id='employerInterviewTable']/div[3]/table/tbody/tr[1]/td[3]/div", "Venue1");

    //********** updateEmployerInterviewScheduleInterviewVenue **********

    webPage.click(".//*[@id='employerInterviewTable']/div[3]/table/tbody/tr[2]");
    webPage.setInput(".//*[@id='EmployerInterviewForm']/div[1]/div[2]/div[2]/select","2");
    webPage.click(".//*[@id='employerInterviewSubmit']");
    //assertTitle
    webPage.assertTextEquals(".//*[@id='employerInterviewTable']/div[3]/table/tbody/tr[2]/td[3]/div", "Venue2");

    //********** updateEmployerInterviewScheduleShortListingVenue **********

    webPage.click(".//*[@id='employerInterviewTable']/div[3]/table/tbody/tr[3]");
    webPage.setInput(".//*[@id='EmployerInterviewForm']/div[1]/div[2]/div[2]/select","1");
    webPage.click(".//*[@id='employerInterviewSubmit']");
    //assertTitle
    webPage.assertTextEquals(".//*[@id='employerInterviewTable']/div[3]/table/tbody/tr[3]/td[3]/div", "Venue1");

    //********** updateEmployerInterviewSchedulePPTVenue **********

    webPage.click(".//*[@id='employerScheduleTable']/div[2]/table/tbody/tr[2]");
    webPage.click(".//*[@id='employerInterviewTable']/div[3]/table/tbody/tr[1]");
    webPage.setInput(".//*[@id='EmployerInterviewForm']/div[1]/div[2]/div[2]/select","1");
    webPage.click(".//*[@id='employerInterviewSubmit']");
    //assertTitle
    webPage.assertTextEquals(".//*[@id='employerInterviewTable']/div[3]/table/tbody/tr[1]/td[3]/div", "Venue1");

    //********** updateEmployerInterviewScheduleInterviewVenue **********

    webPage.click(".//*[@id='employerInterviewTable']/div[3]/table/tbody/tr[2]");
    webPage.setInput(".//*[@id='EmployerInterviewForm']/div[1]/div[2]/div[2]/select","2");
    webPage.click(".//*[@id='employerInterviewSubmit']");
    //assertTitle
    webPage.assertTextEquals(".//*[@id='employerInterviewTable']/div[3]/table/tbody/tr[2]/td[3]/div", "Venue2");

    //********** dispatchPlacementSchedule **********

    webPage.click(".//*[@id='placementScheduleTable']/div[1]/div[1]/div[4]/span");

  }


}