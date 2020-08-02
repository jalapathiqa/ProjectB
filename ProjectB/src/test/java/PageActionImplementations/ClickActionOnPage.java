package PageActionImplementations;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClickActionOnPage {

	static WaitActionsOnPage waitOnPage = new WaitActionsOnPage();

	public void click(WebDriver driver, By by) {
		waitOnPage.waitForElementToBeClickable(driver, by);
		driver.findElement(by).click();
	}

	public static void click(WebDriver driver, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 60, 5000);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		// element.click();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void clickKeys(WebDriver driver, By by, Keys key) {
		waitOnPage.waitForItemVisibility(driver, by);
		driver.findElement(by).sendKeys(key);
	}

	public void clickElementBySize(WebDriver driver, By by) {
		int element_size = driver.findElements(by).size();
		driver.findElements(by).get(element_size - 1).click();
	}

	public void doubleClick(WebDriver driver, By by) {
		// String hoursXpath = "//*[contains(text(),'" + text + "')]";
		waitOnPage.waitForElementToBeClickable(driver, by);
		WebElement ele = driver.findElement(by);
		Actions action = new Actions(driver);
		action.moveToElement(ele).doubleClick().build().perform();
	}

}