package meru.automation.placement.suite.A2_campus.A22_modules;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import app.automation.ui.WebTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T6_AcceptRFPGmailEmployerTest extends WebTest {


  public T6_AcceptRFPGmailEmployerTest() {
    super("localhost:8080/i2par/p/employer/rfp.xhtml?eid=1");

  }

  @Test

  public void pageTest() {

    //********** loginToStudent1Home **********

    webPage.click(".//*[@id='rfpResponseTable']/div[2]/table/tbody/tr");

    //********** acceptRFP **********

    webPage.setInput(".//*[@id='RfpResponseForm']/div[1]/div[1]/div[2]/select","121");
    webPage.setInput(".//*[@id='RfpResponseForm']/div[1]/div[2]/div[2]/select","411");
    webPage.setInput(".//*[@id='RfpResponseForm']/div[1]/div[3]/div[2]/input","6");
    webPage.click(".//*[@id='rfpResponseSubmit']");

    //********** createInterviewRounds **********

    webPage.click(".//*[@id='rfpResponse-Interview_RoundsTab']/a");
    webPage.click(".//*[@id='interviewRoundTable']/div[1]/div[1]/div[1]/span");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[1]/div[2]/select","751");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[2]/div[2]/input","PPT Presentation");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[3]/div[2]/input","31-11-2015");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[4]/div[2]/input","1");
    webPage.click(".//*[@id='interviewRoundSubmit']");
    webPage.click(".//*[@id='interviewRoundTable']/div[1]/div[1]/div[1]/span");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[1]/div[2]/select","752");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[2]/div[2]/input","Test");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[3]/div[2]/input","31-11-2015");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[4]/div[2]/input","1");
    webPage.click(".//*[@id='interviewRoundSubmit']");
    webPage.click(".//*[@id='interviewRoundTable']/div[1]/div[1]/div[1]/span");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[1]/div[2]/select","753");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[2]/div[2]/input","Shortlisting");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[3]/div[2]/input","31-11-2015");
    webPage.setInput(".//*[@id='InterviewRoundForm']/div[1]/div[4]/div[2]/input","1");
    webPage.click(".//*[@id='interviewRoundSubmit']");

    //********** createEmployeeContacts **********

    webPage.click(".//*[@id='rfpResponse-Employer_ContactsTab']/a");
    webPage.click(".//*[@id='employerContactTable']/div[1]/div[1]/div[1]/span");
    webPage.setInput(".//*[@id='EmployerContactForm']/div[1]/div[1]/div[2]/input","Gmail HR First Name");
    webPage.setInput(".//*[@id='EmployerContactForm']/div[1]/div[2]/div[2]/input","Gmail HR Last Name");
    webPage.setInput(".//*[@id='EmployerContactForm']/div[1]/div[3]/div[2]/input","sivarajs@gmail.com");
    webPage.setInput(".//*[@id='EmployerContactForm']/div[1]/div[4]/div[2]/input","12345678");
    webPage.setInput(".//*[@id='EmployerContactForm']/div[1]/div[5]/div[2]/select","401");
    webPage.click(".//*[@id='employerContactSubmit']");

  }


}