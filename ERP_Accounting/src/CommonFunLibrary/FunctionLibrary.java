package CommonFunLibrary;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.PropertyFileUtil;

public class FunctionLibrary {
	public static WebDriver driver;
	/**
	 * ProjectName:ERP_Stock
	 *  TesterName:Danish 
	 *  Module Name:startBrowser
	 * TesterName:Danish Creation Date:
	 */
	public static WebDriver startBrowser(WebDriver driver) throws Throwable {
		if (PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","./CommonJars/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox")) {

		} else if (PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("ie")) {

		}
		return driver;
	}
	/**
	 * ProjectName:ERP_Stock
	 *  TesterName:Danish 
	 *  Module Name:openApplication
	 * TesterName:Danish 
	 * Creation Date:
	 */
	public static void openApplication(WebDriver driver) throws Throwable
	{
		driver.get(PropertyFileUtil.getValueForKey("Url"));
		driver.manage().window().maximize();
	}
	/**
	 * ProjectName:ERP_Stock
	 *  TesterName:Danish 
	 *  Module Name:waitForElement
	 * TesterName:Danish 
	 * Creation Date:
	 */
	public static void waitForElement(WebDriver driver,String locatort,String locatorv,String mywait)
	{
		WebDriverWait wait=new WebDriverWait(driver,Integer.parseInt(mywait));
		if(locatort.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorv)));
		}
		else if(locatort.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorv)));
		}
		else if(locatort.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorv)));
		}
	}
	/**
	 * ProjectName:ERP_Stock
	 *  TesterName:Danish 
	 *  Module Name:waitForElement
	 * TesterName:Danish 
	 * Creation Date:
	 */
	public static void typeAction(WebDriver driver,String locatort,String locatorv,String testdata)
	{
		if(locatort.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorv)).clear();
			driver.findElement(By.id(locatorv)).sendKeys(testdata);
		}
		else if(locatort.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorv)).clear();
			driver.findElement(By.name(locatorv)).sendKeys(testdata);
		}
		else if(locatort.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorv)).clear();
			driver.findElement(By.xpath(locatorv)).sendKeys(testdata);
		}
	}
	/**
	 * ProjectName:ERP_Stock
	 *  TesterName:Danish 
	 *  Module Name:clickAction
	 * Creation Date:
	 */
	public static void clickAction(WebDriver driver,String locatort,String locatorv)
	{
		if(locatort.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorv)).click();
		}
		else if(locatort.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorv)).click();
		}
		else if(locatort.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(locatorv)).click();
		}
	}
	/**
	 * ProjectName:ERP_Stock
	 *  TesterName:Danish 
	 *  Module Name:closeBrowser
	 * Creation Date:
	 */
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	/**
	 * ProjectName:ERP_Stock
	 *  TesterName:Danish 
	 *  Module Name:dateGenerate
	 * Creation Date:
	 */
	public static String generateDate()
	{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
		return sdf.format(date);
	}
	
}
