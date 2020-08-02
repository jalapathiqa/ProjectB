package PageActionImplementations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GetTextOnPage {

	WaitActionsOnPage waitOnPage = new WaitActionsOnPage();

	public String getText(WebDriver driver, By by) {
		waitOnPage.waitForItemVisibility(driver, by);
		return driver.findElement(by).getText();
	}

	public String getText(WebDriver driver, String attribute) {
		waitOnPage.waitForItemVisibility(driver, By.xpath("//*[contains(text(),'" + attribute + "')]"));
		return driver.findElement(By.xpath("//*[contains(text(),'" + attribute + "')]")).getText();
	}

	public String getTextBySize(WebDriver driver, By by) {
		int element_size = driver.findElements(by).size();
		return driver.findElements(by).get(element_size - 1).getText();
	}

	public String getCurrentUrl(WebDriver driver) {
		
		String currentUrl = driver.getCurrentUrl();
		
		return currentUrl;
	}
}