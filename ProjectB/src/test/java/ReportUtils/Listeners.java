package ReportUtils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {

	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentHtmlReporter htmlReporter;

	public void onStart() {
		extent = new ExtentReports();
		htmlReporter = new ExtentHtmlReporter("./Reports/" + "ListenersReports" + " - " + ".html");
		htmlReporter.config().setTheme(Theme.DARK);
		extent.attachReporter(htmlReporter);
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test case PASSED is" + result.getMethod().getMethodName() + ".png");
		test.info(result.getMethod().getMethodName());

		try {
			test.addScreenCaptureFromPath(System.getProperty("user.dir") + "./ScreenShots_Pass/" + ".png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case FAILED is" + result.getName() + ".png");
		test.info(result.getMethod().getMethodName());
		try {
			test.addScreenCaptureFromPath(System.getProperty("user.dir") + "./ScreenShots_Fail/" + ".png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case SKIPPED is" + result.getName() + ".png");
		test.info(result.getMethod().getMethodName());
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}
