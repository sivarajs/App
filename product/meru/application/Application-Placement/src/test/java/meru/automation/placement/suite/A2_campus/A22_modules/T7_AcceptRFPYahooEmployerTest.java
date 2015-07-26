package meru.automation.placement.suite.A2_campus.A22_modules;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import app.automation.ui.WebTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T7_AcceptRFPYahooEmployerTest extends WebTest {


  public T7_AcceptRFPYahooEmployerTest() {
    super("localhost:8080/i2par/p/employer/rfp.xhtml?eid=2");

  }

  @Test

  public void pageTest() {

    //********** loginToStudent2Home **********

    webPage.click(".//*[@id='rfpResponseTable']/div[2]/table/tbody/tr");

    //********** acceptRFP **********

    webPage.setInput(".//*[@id='RfpResponseForm']/div[1]/div[1]/div[2]/select","121");
    webPage.setInput(".//*[@id='RfpResponseForm']/div[1]/div[2]/div[2]/select","411");
    webPage.setInput(".//*[@id='RfpResponseForm']/div[1]/div[3]/div[2]/input","4");
    webPage.click(".//*[@id='rfpResponseSubmit']");

    //********** createInterviewRounds **********

    webPage.click(".//*[@id='rfpResponse-Interview_RoundsTab']/a");
    webPage.click(".//*[@id='interviewRoundTable']/div[1]/div[1]/div[1]/span");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[1]/div[2]/select","752");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[2]/div[2]/input","Test and Interview");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[3]/div[2]/input","31-11-2015");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[4]/div[2]/input","1");
    webPage.click(".//*[@id='interviewRoundSubmit']");
    webPage.click(".//*[@id='interviewRoundTable']/div[1]/div[1]/div[1]/span");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[1]/div[2]/select","753");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[2]/div[2]/input","Selection");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[3]/div[2]/input","31-11-2015");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[4]/div[2]/input","1");
    webPage.click(".//*[@id='interviewRoundSubmit']");

    //********** createEmployeeContacts **********

    webPage.click(".//*[@id='rfpResponse-Employer_ContactsTab']/a");
    webPage.click(".//*[@id='employerContactTable']/div[1]/div[1]/div[1]/span");
    webPage.setInput(".//*[@id='EmployerContactForm']/div[1]/div[1]/div[2]/input","Yahoo HR First Name");
    webPage.setInput(".//*[@id='EmployerContactForm']/div[1]/div[2]/div[2]/input","Yahoo HR Last Name");
    webPage.setInput(".//*[@id='EmployerContactForm']/div[1]/div[3]/div[2]/input","sivarajsk@yahoo.com");
    webPage.setInput(".//*[@id='EmployerContactForm']/div[1]/div[4]/div[2]/input","1112345678");
    webPage.setInput(".//*[@id='EmployerContactForm']/div[1]/div[5]/div[2]/select","401");
    webPage.click(".//*[@id='employerContactSubmit']");

  }


}