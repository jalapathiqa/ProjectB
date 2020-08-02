package TestCases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.FBLogin_PageObjects;
import TestBaseB.BaseClass;

public class LoginToFB extends BaseClass{
	String testCaseName = "FBLogin";
	
	@DataProvider(name="FBLogin")
	public Object[][] getData(){
		return readExcel.retrieveTestData(sheetName, testCaseName);
	}
	
	@Test(dataProvider = "FBLogin")
	public void loginFB(String execute, String name, String pwd) {
		
		FBLogin_PageObjects fb = PageFactory.initElements(driver, FBLogin_PageObjects.class);
		fb.loginToFB(name, pwd);
		
	}

}
