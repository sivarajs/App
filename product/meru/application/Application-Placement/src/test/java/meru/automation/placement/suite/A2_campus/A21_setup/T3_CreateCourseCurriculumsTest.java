package meru.automation.placement.suite.A2_campus.A21_setup;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import app.automation.ui.WebTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T3_CreateCourseCurriculumsTest extends WebTest {


  public T3_CreateCourseCurriculumsTest() {
    super("localhost:8080/i2par/campus");

  }

  @Test

  public void pageTest() {

    //********** selectCourseCurriculumTreeItem **********

    webPage.click(".//*[@id='header']/div[1]/span[1]");
    webPage.click(".//*[@id='workArea']/div/div[1]/div[2]/div/div/li[2]/ul/li[3]/div/div[1]");
    //assertTitle
    webPage.assertTextEquals(".//*[@id='contentArea']/div/h1", "COURSE CURRICULUM");

    //********** newCourseCurriculum1 **********

    webPage.click(".//*[@id='courseTable']/div[1]/div[1]/div[1]");
    webPage.setInput(".//*[@id='CourseForm']/div[1]/div[1]/div[2]/select","351");
    webPage.setInput(".//*[@id='CourseForm']/div[1]/div[2]/div[2]/select","371");
    webPage.setInput(".//*[@id='CourseForm']/div[1]/div[3]/div[2]/select","31");
    webPage.setInput("//*[@id='CourseForm']/div[1]/div[4]/div[2]/input","4");
    webPage.setInput(".//*[@id='CourseForm']/div[1]/div[5]/div[2]/select","94");
    webPage.click(".//*[@id='courseSubmit']");
    //assertTitle
    webPage.assertAttributeEquals(".//*[@id='CourseForm']/div[1]/div[4]/div[2]/input", "value", "4");

    //********** newCourseCurriculum2 **********

    webPage.click(".//*[@id='courseTable']/div[1]/div[1]/div[1]");
    webPage.setInput(".//*[@id='CourseForm']/div[1]/div[1]/div[2]/select","351");
    webPage.setInput(".//*[@id='CourseForm']/div[1]/div[2]/div[2]/select","372");
    webPage.setInput(".//*[@id='CourseForm']/div[1]/div[3]/div[2]/select","31");
    webPage.setInput("//*[@id='CourseForm']/div[1]/div[4]/div[2]/input","4");
    webPage.setInput(".//*[@id='CourseForm']/div[1]/div[5]/div[2]/select","94");
    webPage.click(".//*[@id='courseSubmit']");
    //assertTitle
    webPage.assertAttributeEquals(".//*[@id='CourseForm']/div[1]/div[4]/div[2]/input", "value", "4");

  }


}