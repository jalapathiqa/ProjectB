package PageActionImplementations;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasicPageActions {

	public WebElement findWebElemnt(WebDriver driver, By by) {
		WaitForItemVisibility(driver, by);
		WebElement element = driver.findElement(by);

		return element;
	}

	public boolean isElementPresent(WebDriver driver, String elementToValidate) {

		boolean exists = false;
		try {
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
			exists = driver.findElements(By.xpath("//*[contains(text(),'" + elementToValidate + "')]")).size() != 0;
		} catch (Exception e) {
		}
		return exists;
	}

	public boolean isElementPresent(WebDriver driver, By by) {

		boolean exists = false;
		try {
			driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
			exists = driver.findElements(by).size() != 0;
		} catch (Exception e) {

		}
		return exists;
	}

	public boolean isElementPresent(WebDriver driver, WebElement element) {

		boolean textToBePresent = true;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			textToBePresent = wait.until(ExpectedConditions.invisibilityOf(element));
		} catch (Exception e) {
		}
		return textToBePresent;

	}

	public void WaitForItemVisibility(WebDriver webDriver, By by) {
		WebDriverWait wait = new WebDriverWait(webDriver, 20, 5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void WaitForelementToBeClickable(WebDriver webDriver, By by) {

		WebDriverWait wait = new WebDriverWait(webDriver, 20, 5000);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public void WaitForelementToBeClickable(WebDriver webDriver, WebElement element) {

		WebDriverWait wait = new WebDriverWait(webDriver, 20, 5000);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void WaitForelementToBeClickable(WebDriver webDriver, WebElement element, int retryTime, int timeOut) {

		WebDriverWait wait = new WebDriverWait(webDriver, retryTime, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void WaitForelementToBeClickable(WebDriver webDriver, WebElement element, int time) {

		WebDriverWait wait = new WebDriverWait(webDriver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void WaitForelementToBeClickable(WebDriver webDriver, By by, int time) {

		WebDriverWait wait = new WebDriverWait(webDriver, time);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public void WaitForItemVisibility(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20, 5000);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void WaitForItemVisibility(WebDriver webDriver, WebElement element, int time) {
		WebDriverWait wait = new WebDriverWait(webDriver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void pageScrollUp(WebDriver driver, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		// jse.executeScript("window.scrollBy(0,-1000)", "");
		// js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void pageScrollDown(WebDriver driver, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		// jse.executeScript("window.scrollBy(0,1000)", "");
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void scrollIntoView(WebDriver driver, WebElement element) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		// jse.executeScript("window.scrollBy(0,1000)", "");
		// js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void pageScrollDown(WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		// jse.executeScript("window.scrollBy(0,1000)", "");
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		// js.executeScript("arguments[0].scrollIntoView(true);", by);
	}

	public void pageScrollUp(WebDriver driver, WebElement element, int height) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollBy(0," + height + ")", "");

	}

	public void pageScrollDown(WebDriver driver, WebElement element, int height) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("window.scrollBy(0," + height + ")", "");

	}

	public void click(WebDriver driver, By by) {
		WaitForelementToBeClickable(driver, by);
		driver.findElement(by).click();
	}

	public void click(WebDriver driver, WebElement element) {
		WaitForelementToBeClickable(driver, element);
		element.click();
	}

	public void type(WebDriver driver, By by, String text) {
		WaitForItemVisibility(driver, by);
		driver.findElement(by).sendKeys(text);
	}

	public void typeBySize(WebDriver driver, By by, String text) {
		int element_size = driver.findElements(by).size();
		driver.findElements(by).get(element_size - 1).sendKeys(text);
	}

	public void typeTextByActions(WebDriver driver, WebElement element, String text) {

		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.click();
		actions.sendKeys(text);
		actions.build().perform();
	}

	public void clickKeys(WebDriver driver, By by, Keys key) {
		WaitForItemVisibility(driver, by);
		driver.findElement(by).sendKeys(key);
	}

	public void clearAndType(WebDriver driver, By by, String text) {
		WaitForItemVisibility(driver, by);
		driver.findElement(by).clear();
		WaitForItemVisibility(driver, by);
		driver.findElement(by).sendKeys(text);
	}

	public String getText(WebDriver driver, By by) {
		WaitForItemVisibility(driver, by);
		return driver.findElement(by).getText();
	}

	public String getText(WebDriver driver, WebElement element) {
		WaitForItemVisibility(driver, element);
		return element.getText();
	}

	public String getText(WebDriver driver, String attribute) {
		WaitForItemVisibility(driver, By.xpath("//*[contains(text(),'" + attribute + "')]"));
		return driver.findElement(By.xpath("//*[contains(text(),'" + attribute + "')]")).getText();
	}

	public void doubleClick(WebDriver driver, By by) {
		// String hoursXpath = "//*[contains(text(),'" + text + "')]";
		WaitForelementToBeClickable(driver, by);
		WebElement ele = driver.findElement(by);
		Actions action = new Actions(driver);
		action.moveToElement(ele).doubleClick().build().perform();
	}

	public void hoverOver(WebDriver driver, By by) {
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(by);
		action.moveToElement(we).build().perform();
	}

	public void switchToNewWindow(WebDriver driver) {
		// Store the current window handle
		// String winHandleBefore = driver.getWindowHandle();
		// Perform the click operation that opens new window
		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			System.out.println("Current window:" + winHandle);
		}

		driver.switchTo().defaultContent();

	}

	public void switchTab(WebDriver driver) {

		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		driver.close();
		driver.switchTo().window(tabs2.get(0));
	}

	public void selectDropDown(WebDriver driver, By by, int valueIndex) {
		WebElement dropDown = driver.findElement(by);
		Select valueDropdown = new Select(dropDown);
		valueDropdown.selectByIndex(valueIndex);
	}

	public void deselectDropDown(WebDriver driver, By by, int valueIndex) {
		WebElement dropDown = driver.findElement(by);
		Select valueDropdown = new Select(dropDown);
		valueDropdown.deselectByIndex(valueIndex);
	}

	public void selectDropDown(WebDriver driver, By by, String value) {
		WebElement dropDown = driver.findElement(by);
		Select valueDropdown = new Select(dropDown);
		valueDropdown.selectByVisibleText(value);
	}

	public void deselectDropDown(WebDriver driver, By by, String value) {
		WebElement dropDown = driver.findElement(by);
		Select valueDropdown = new Select(dropDown);
		valueDropdown.deselectByVisibleText(value);
	}

	public void clickElementBySize(WebDriver driver, By by) {
		int element_size = driver.findElements(by).size();
		driver.findElements(by).get(element_size - 1).click();
	}

	public String getTextBySize(WebDriver driver, By by) {
		int element_size = driver.findElements(by).size();
		return driver.findElements(by).get(element_size - 1).getText();
	}

	public void takeScreenShot(WebDriver driver, String location, String screenShotName) {
		try {
			File screenshots = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(screenshots, new File(location + screenShotName + ".png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void robotPageScroll() {

		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_PAGE_DOWN);
			robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
			robot.keyPress(KeyEvent.VK_PAGE_DOWN);
			robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean elementVisible(WebDriver driver, By by) {

		WebElement element = driver.findElement(by);

		return element.isDisplayed();

	}

	public void implicitlyWait(WebDriver driver, int time) {

		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);

	}

	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public String getCurrentUrl(WebDriver driver) {

		String currentUrl = driver.getCurrentUrl();

		return currentUrl;
	}

	public synchronized String takeScreenShot(WebDriver driver, String screenShotName) {
		String path = System.getProperty("user.dir") + "\\screenshots\\" + "" + screenShotName + ".png";
		// System.out.println("path:"+ path);
		try {
			File screenshots = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(screenshots, new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return path;
	}

	protected synchronized String takeScreenShot(String methodName, RemoteWebDriver driver) {
		String path = System.getProperty("user.dir") + "/screenshots/" + methodName + ".png";
		try {
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(screenshotFile, new File(path));
		} catch (Exception e) {
			System.out.print("Could not write screenshot" + e);
		}
		return path;
	}

}