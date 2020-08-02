package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import GenericUtilits.GenericPageActions;
import TestBaseB.BaseClass;

public class FBLogin_PageObjects extends BaseClass {

	GenericPageActions pageActs = new GenericPageActions();
	
	// define user name
	@FindBy(how = How.ID, using = "email")
	private WebElement email;

	// define password
	@FindBy(how = How.ID, using = "pass")
	private WebElement passWord;

	// submit button
	@FindBy(how = How.XPATH, using = "//input[@id='u_0_b']")
	private WebElement loginBtn;

	public void loginToFB(String uName, String pWord) {

		
		pageActs.sendText(driver, email, uName);
		pageActs.sendText(driver, passWord, pWord);
		pageActs.click(driver, loginBtn);

	}
}
