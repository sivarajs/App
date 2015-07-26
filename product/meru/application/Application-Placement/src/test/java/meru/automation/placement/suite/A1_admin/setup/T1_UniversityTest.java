package meru.automation.placement.suite.A1_admin.setup;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import app.automation.ui.WebTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T1_UniversityTest extends WebTest {


  public T1_UniversityTest() {
    super("localhost:8080/i2par/admin");

  }

  @Test

  public void pageTest() {

    //********** selectUniversityTreeItem **********

    webPage.click(".//*[@id='header']/div[1]/span[1]");
    webPage.click(".//*[@id='workArea']/div/div[1]/div[2]/div/div/li[3]/ul/li[1]/div");
    //assertTitle
    webPage.assertTextEquals(".//*[@id='contentArea']/div/h1", "UNIVERSITY");

    //********** newUniversity **********

    webPage.click(".//*[@id='universityTable']/div[1]/div[1]/div[1]");
    webPage.setInput(".//*[@id='UniversityForm']/div[1]/div[1]/div[2]/div[2]/input","Birla Institute Of Technology and Science");
    webPage.setInput(".//*[@id='UniversityForm']/div[1]/div[2]/div[2]/div[2]/input","Pilani");
    webPage.setInput(".//*[@id='UniversityForm']/div[1]/div[2]/div[3]/div[2]/select","1");
    webPage.setInput(".//*[@id='UniversityForm']/div[1]/div[2]/div[4]/div[2]/select","1");
    webPage.setInput(".//*[@id='UniversityForm']/div[1]/div[2]/div[5]/div[2]/select","18");
    webPage.setInput(".//*[@id='UniversityForm']/div[1]/div[2]/div[6]/div[2]/select","1");
    webPage.setInput(".//*[@id='UniversityForm']/div[1]/div[2]/div[7]/div[2]/input","123456");
    webPage.click(".//*[@id='universitySubmit']");

  }


}