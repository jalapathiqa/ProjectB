package PageActionImplementations;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitActionsOnPage {

	public void waitForItemVisibility(WebDriver driver, By by) {
		WebDriverWait wait = new WebDriverWait(driver, 20, 9000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForElementToBeClickable(WebDriver driver, By by) {

		WebDriverWait wait = new WebDriverWait(driver, 20, 5000);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public void waitForElementToBeClickable(WebDriver driver, WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 20, 5000);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementToBeClickable(WebDriver driver, WebElement element, int retryTime, int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, retryTime, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementToBeClickable(WebDriver driver, WebElement element, int time) {

		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementToBeClickable(WebDriver driver, By by, int time) {

		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	public void waitForItemVisibility(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 20, 9000);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForItemVisibility(WebDriver driver, WebElement element, int time) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void implicitlyWait(WebDriver driver, int time) {

		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);

	}

	public void longImplicitlyWait_1000(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

	}

	public void shortImplicitlyWait_500(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(500, TimeUnit.SECONDS);

	}

	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void longSleep_1000() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void shortSleep_500() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	public void waitForItemInVisibility(WebDriver driver, By by, int time) {
		WebDriverWait wait = new WebDriverWait(driver, 20, time);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));

	}

}