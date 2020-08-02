package GenericUtilits;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestBaseB.BaseClass;

public class GenericPageActions {

	public static void click(RemoteWebDriver driver, WebElement element) {
		BaseClass.waitForPageToLoad(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10, 5000);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		highlightElement(driver, element);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		unhighlightElement(driver, element);
	}

	public static void sendText(RemoteWebDriver driver, WebElement element, String text) {
		BaseClass.waitForPageToLoad(driver);
		highlightElement(driver, element);
		WebDriverWait wait = new WebDriverWait(driver, 10, 5000);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.clear();
		BaseClass.waitForPageToLoad(driver);
		element.sendKeys(text);
		BaseClass.waitForPageToLoad(driver);
		unhighlightElement(driver, element);
	}

	public static void highlightElement(RemoteWebDriver driver, WebElement element) {

		try {
			if (isElementVisible(element)) {
				JavascriptExecutor js = driver;
				js.executeScript("arguments[0].style.border='3.3px solid red'", element);
			}
		} catch (Exception e) {
		}
	}

	public static void unhighlightElement(RemoteWebDriver driver, WebElement element) {
		try {
			if (isElementVisible(element)) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].style.border='0px'", element);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isElementVisible(WebElement element) {
		try {
			return element.isDisplayed();

		} catch (Exception e) {

			return false;
		}

	}

	public static void clickByActionClass(WebElement element,RemoteWebDriver driver){
			 Actions action = new Actions(driver);
			 action.moveToElement(element).click().build().perform();
			 }
		 
	public static void scrollToElement(WebElement element,RemoteWebDriver driver)	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public static boolean isAlertPresent(RemoteWebDriver driver){
	try{ 
		driver.switchTo().alert(); 
		return true;
		}catch (NoAlertPresentException Ex){
			Ex.printStackTrace();
			}
	return false;
	}

}
