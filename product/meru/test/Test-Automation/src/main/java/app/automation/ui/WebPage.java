package app.automation.ui;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebPage {

	private WebDriver webDriver;

	public WebPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public WebPage load(String url) {

		webDriver.get(url);
		return this;
		// WebElement element = webDriver.findElement(By.name("userName"));
		// element.sendKeys(userName);
		// element = webDriver.findElement(By.name("password"));
		// element.sendKeys(password);
		// element.submit();
		
	}

	private WebElement getWebElement(String xpath) {
		List<WebElement> elems = webDriver.findElements(By.xpath(xpath));
		if (elems == null || elems.size() != 1) {
			throw new RuntimeException("Invalid nodes at " + xpath+" "+elems);
		}

		return elems.get(0);
	}

	public WebPage setInput(String xpath, String value) {

		WebElement elem = getWebElement(xpath);

		String name = elem.getTagName();
		if (name.equalsIgnoreCase("select")) {
			List<WebElement> allOptions = elem.findElements(By.tagName("option"));
			for (WebElement option : allOptions) {
				if (value.equals(option.getAttribute("value"))) {
					option.click();
				}
			}
		}
		else {
			elem.clear();
			elem.sendKeys(value);
		}
		return this;
	}

	public WebPage setSelectOption(String xpath, String value) {

		WebElement elem = getWebElement(xpath);
		elem.clear();
		elem.sendKeys(value);
		return this;
	}

	public WebPage clickAlertOK() {
		return clickAlertOK(4000);
	}

	public WebPage clickAlertOK(long wait) {

		Alert alert = webDriver.switchTo().alert();
		alert.accept();

		try {
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Wait<WebDriver> wdWait = new WebDriverWait(webDriver,
		// 10).ignoring(TimeoutException.class);
		// wdWait.until(ExpectedConditions.alertIsPresent());

		return this;
	}

	public WebPage clickAlertCancel() {

		Alert alert = webDriver.switchTo().alert();
		alert.dismiss();
		return this;
	}

	public WebPage click(String xpath) {
	    try {
   		   WebElement elem = getWebElement(xpath);
		   elem.click();
	    } catch (ElementNotVisibleException e) {
	        
	        WebElement element = webDriver.findElement(By.xpath(xpath));
	        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();", element);
	        
	    }
		return this;
	}

	public WebPage assertTitle(String title) {
		Assert.assertEquals("Invalid Title ", title, webDriver.getTitle());
		return this;
	}

	public WebPage assertNodeCountEquals(String xPath, int count) {

		List<WebElement> h1Elems = webDriver.findElements(By.xpath(xPath));
		Assert.assertNotNull(h1Elems);
		Assert.assertEquals("Invalid number of nodes at [" + xPath + "]",
							count,
							h1Elems.size());
		return this;
	}

	public	WebPage assertAttributeEquals(String xpath, String name, String value) {

		WebElement elem = getWebElement(xpath);

		String type = elem.getAttribute("type");
		if (type != null && type.equals("checkbox") && name.equals("checked")) {
			if (value.equals("true")) {
				if (!elem.isSelected()) {
					throw new RuntimeException("Invalid Value. Expected [checked=true]], Actual [checked=false]");
				}
			}
			else {
				if (elem.isSelected()) {
					throw new RuntimeException("Invalid Value. Expected [checked=false]], Actual [checked=true]");
				}
			}
		}
		else {
			if (!value.equals(elem.getAttribute(name).trim())) {
				throw new RuntimeException("Invalid Attribute. Expected ["
						+ value + "], Actual [" + elem.getAttribute(name) + "]");
			}
		}
		return this;
	}

	public	WebPage assertAttributeContains(String xpath, String name, String value) {
		WebElement elem = getWebElement(xpath);
		if (!elem.getAttribute(name).trim().contains(value)) {
			throw new RuntimeException("Invalid Attribute. Expected [* "
					+ value + " *], Actual [" + elem.getAttribute(name) + "]");
		}
		return this;
	}
	
	public WebPage assertAttributeNotNull(String xpath, String name) {

		WebElement elem = getWebElement(xpath);

		String value = elem.getAttribute(name);
		if (value == null || value.trim().length() == 0) {
			throw new RuntimeException("Non-Empty Attribute Expected for "
					+ xpath + "[@" + name + "]");
		}

		return this;
	}

	public WebPage assertTextEquals(String xpath, String text) {

		WebElement elem = getWebElement(xpath);

		if (!text.equals(elem.getText())) {
			throw new RuntimeException("Invalid Text Expected [" + text
					+ "], Actual [" + elem.getText() + "]");
		}
		return this;
	}

	public WebPage assertTextNotNull(String xpath) {

		WebElement elem = getWebElement(xpath);

		String text = elem.getText();
		if (text == null || text.trim().length() == 0) {
			throw new RuntimeException("Non-Empty Text Expected at " + xpath);
		}
		return this;
	}

}
