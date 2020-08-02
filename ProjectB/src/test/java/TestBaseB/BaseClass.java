package TestBaseB;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import ExcelUtils.Read_Excel;

public class BaseClass {

	public static RemoteWebDriver driver = null;
	// protected ThreadLocal<RemoteWebDriver> threadedDriver;
	public static String AplBrowser, AplUrl;
	public static Properties configProp;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentHtmlReporter htmlReporter;

	public static String fileLocation = System.getProperty("user.dir") + "./TestData/DataDrivenExcel.xlsx";
	public static String sheetName = "TestSheet";
	public static Read_Excel readExcel = new Read_Excel(fileLocation);

	@BeforeSuite
	public void runBeforeEverything() throws IOException {
		configProp = new Properties();
		File fileConfig = new File("C:\\config.properties");
		FileInputStream fileInputConfig = new FileInputStream(fileConfig);
		configProp.load(fileInputConfig);

		try {
			AplBrowser = configProp.getProperty("browser");
			AplUrl = configProp.getProperty("url");
		} catch (Exception e) {
			e.printStackTrace();
		}

		extent = new ExtentReports();
		htmlReporter = new ExtentHtmlReporter("./Reports/" + "ExtentReports" + " - " + timeStamp() + ".html");
		extent.attachReporter(htmlReporter);
	}

	@BeforeClass
	public void initializeBrowser() throws MalformedURLException {
		// threadedDriver = new ThreadLocal<RemoteWebDriver>();

		if (AplBrowser.equalsIgnoreCase("chrome")) {
			DesiredCapabilities dc = DesiredCapabilities.chrome();
			URL url = new URL("http://localhost:4444/wd/hub");
			driver = new RemoteWebDriver(url, dc);
		} else if (AplBrowser.equalsIgnoreCase("ie")) {
			DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
			URL url = new URL("http://localhost:4444/wd/hub");
			driver = new RemoteWebDriver(url, dc);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(AplUrl);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void runBeforeTest(Method method) {
		test = extent.createTest(method.getName());
	}

	@AfterMethod
	public void runAfterTest(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, "Test case PASSED is" + result.getMethod().getMethodName() + timeStamp() + ".png");
			test.info(result.getMethod().getMethodName());

			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File dest = new File(System.getProperty("user.dir") + "./ScreenShots_Pass/" + timeStamp() + ".png");

			try {
				FileHandler.copy(src, dest);
				test.addScreenCaptureFromPath(
						System.getProperty("user.dir") + "./ScreenShots_Pass/" + timeStamp() + ".png");
			} catch (IOException e) {
				System.out.println("could not write screen shots " + e.getMessage());
			}
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, "Test case FAILED is" + result.getMethod().getMethodName() + timeStamp() + ".png");
			test.info(result.getMethod().getMethodName());

			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File dest = new File(System.getProperty("user.dir") + "./ScreenShots_Fail/" + timeStamp() + ".png");

			try {
				FileHandler.copy(src, dest);
				test.addScreenCaptureFromPath(
						System.getProperty("user.dir") + "./ScreenShots_Fail/" + timeStamp() + ".png");
			} catch (IOException e) {
				System.out.println("could not write screen shots " + e.getMessage());
			}
		} else {
			test.log(Status.PASS, "Test case SKIPPED is" + result.getMethod().getMethodName() + timeStamp() + ".png");
			test.info(result.getMethod().getMethodName());
		}
	}

	@AfterClass
	public void afterClass() {
		driver.close();
		driver.quit();

	}

	@AfterSuite
	public void tearDown() {
		extent.flush();

	}

	public String timeStamp() {
		return new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date());
	}

	public static void waitForPageToLoad(RemoteWebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);

	}

	public static void waitForPageToLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);

	}

}
