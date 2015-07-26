package meru.automation.placement.suite.A2_campus.A22_modules;

import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import app.automation.ui.WebTest;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class T2_CreatePlacementTest extends WebTest {


  public T2_CreatePlacementTest() {
    super("localhost:8080/i2par/campus");

  }

  @Test

  public void pageTest() {

    //********** selectPlacementHierarchyTreeItem **********

    webPage.click(".//*[@id='header']/div[1]/span[2]");
    webPage.click(".//*[@id='workArea']/div/div[1]/div[2]/div/div/li[2]/ul/li[1]/div/div[1]");
    //assertTitle
    webPage.assertTextEquals(".//*[@id='contentArea']/div/h1", "PLACEMENT HIERARCHY");

    //********** newPlacement1 **********

    webPage.click(".//*[@id='placementTable']/div[1]/div[1]/div[1]");
    webPage.setInput(".//*[@id='PlacementForm']/div[1]/div[1]/div[2]/input","2015");
    webPage.setInput(".//*[@id='PlacementForm']/div[1]/div[2]/div[2]/input","01-06-2015");
    webPage.setInput(".//*[@id='PlacementForm']/div[1]/div[3]/div[2]/input","31-04-2016");
    webPage.setInput(".//*[@id='PlacementForm']/div[1]/div[4]/div[2]/input","01-06-2015");
    webPage.setInput(".//*[@id='PlacementForm']/div[1]/div[5]/div[2]/input","31-10-2015");
    webPage.setInput(".//*[@id='PlacementForm']/div[1]/div[6]/div[2]/input","01-11-2015");
    webPage.setInput(".//*[@id='PlacementForm']/div[1]/div[7]/div[2]/input","01-06-2016");
    webPage.click(".//*[@id='placementSubmit']");
    //assertTitle
    webPage.assertAttributeEquals(".//*[@id='PlacementForm']/div[1]/div[1]/div[2]/input", "value", "2015");

    //********** newPlacement2 **********

    webPage.click(".//*[@id='placementTable']/div[1]/div[1]/div[1]");
    webPage.setInput(".//*[@id='PlacementForm']/div[1]/div[1]/div[2]/input","2015");
    webPage.setInput(".//*[@id='PlacementForm']/div[1]/div[2]/div[2]/input","01-06-2015");
    webPage.setInput(".//*[@id='PlacementForm']/div[1]/div[3]/div[2]/input","31-04-2016");
    webPage.setInput(".//*[@id='PlacementForm']/div[1]/div[4]/div[2]/input","01-01-2016");
    webPage.setInput(".//*[@id='PlacementForm']/div[1]/div[5]/div[2]/input","28-02-2016");
    webPage.setInput(".//*[@id='PlacementForm']/div[1]/div[6]/div[2]/input","01-01-2016");
    webPage.setInput(".//*[@id='PlacementForm']/div[1]/div[7]/div[2]/input","01-06-2016");
    webPage.click(".//*[@id='placementSubmit']");
    //assertTitle
    webPage.assertAttributeEquals(".//*[@id='PlacementForm']/div[1]/div[1]/div[2]/input", "value", "2015");

  }


}