package PageActionImplementations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectFromDropDown {

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

}