package commonfunctions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import commonutilities.PropertyFileUtility;

public class OrangeFunctions 
{
	public WebDriver driver;
	/*
	 * startBrowser
	 */
	public static WebDriver startBrowser(WebDriver driver)
	{
		if(PropertyFileUtility.getValueForKey("Browser").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","./CommonJars/chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(PropertyFileUtility.getValueForKey("Browser").equalsIgnoreCase("firefox"))
		{
			
		}
		else if(PropertyFileUtility.getValueForKey("Browser").equalsIgnoreCase("ie"))
		{
			
		}
		return driver;
	}
	/*
	 * openApplication
	 */
	public static void openApplication(WebDriver driver)
	{
		driver.get(PropertyFileUtility.getValueForKey("Url"));
		driver.manage().window().maximize();
	}
	/*
	 * waitForElement
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
	/*
	 * typeAction
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
	/*
	 * clickAction
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
	/*
	 * closeBrowser
	 */
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	/*
	 * generateDate
	 */
	public static String generateDate()
	{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
		return sdf.format(date);
	}
	/*
	 * captureData
	 */
	public static void captureData(WebDriver driver,String locatort,String locatorv) throws Throwable
	{
		String empid="";
		if(locatort.equalsIgnoreCase("id"))
		{
			empid=driver.findElement(By.id(locatorv)).getAttribute("value");
		}
		else if(locatort.equalsIgnoreCase("xpath"))
		{
			empid=driver.findElement(By.xpath(locatorv)).getAttribute("value");
		}
		else if(locatort.equalsIgnoreCase("name"))
		{
			 empid=driver.findElement(By.name(locatorv)).getAttribute("value");
		}
		FileWriter fw=new FileWriter("E:\\AutoSelenium\\ZEdgeHrm\\CaptureData\\empid.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(empid);
		bw.flush();
		bw.close();
	}
	/*
	 * tableValidation
	 */
	public static void tableValidation(WebDriver driver,String columndata) throws Throwable
	{
		FileReader fr=new FileReader("E:\\AutoSelenium\\ZEdgeHrm\\CaptureData\\empid.txt");
		BufferedReader bw=new BufferedReader(fr);
		String Exp_data=bw.readLine();
		int column=Integer.parseInt(columndata);
		if(driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("id-searcbox"))).isDisplayed())
		{
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("id-searcbox"))).clear();
			Thread.sleep(3000);
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("id-searcbox"))).sendKeys(Exp_data);
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("search-button"))).click();
			Thread.sleep(3000);
		}
		else
		{
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("toggle-tip"))).click();
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("id-searcbox"))).clear();
			Thread.sleep(3000);
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("id-searcbox"))).sendKeys(Exp_data);
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("search-button"))).click();
			Thread.sleep(3000);
		}
		WebElement table=driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("search-table")));
		List<WebElement> rows=table.findElements(By.tagName("tr"));
		for(int i=1;i<=rows.size();i++)
		{
			String act_data=driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td["+column+"]")).getText();
			System.out.println("No of rows are::"+rows.size());
			Thread.sleep(3000);
			System.out.println(Exp_data+"---------"+act_data);
			Assert.assertEquals(Exp_data,act_data,"snumber is not matching");
			break;
		}
		
	}
	/*
	 * dropdownSelection
	 */
	public static void dropdownSelection(WebDriver driver,String locatort,String locatorv,String testd)
	{
		if(locatort.equalsIgnoreCase("id"))
		{
			Select role=new Select(driver.findElement(By.id(locatorv)));
			role.selectByVisibleText(testd);
		}
		else if(locatort.equalsIgnoreCase("xpath"))
		{
			Select role=new Select(driver.findElement(By.xpath(locatorv)));
			role.selectByVisibleText(testd);
		}
		else if(locatort.equalsIgnoreCase("name"))
		{
			Select role=new Select(driver.findElement(By.name(locatorv)));
			role.selectByVisibleText(testd);
		}
	}
	/*
	 * adminValidation
	 */
	public static void adminValidation(WebDriver driver,String expct_data) throws Throwable
	{
		if(driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("uname"))).isDisplayed())
		{
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("uname"))).clear();
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("uname"))).sendKeys(expct_data);
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("search-bttn"))).click();
		}
		else {
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("tog-tip"))).click();
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("uname"))).clear();
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("uname"))).sendKeys(expct_data);
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("search-bttn"))).click();
		}
		WebElement table=driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("table"))); 
		List<WebElement> rows=table.findElements(By.tagName("tr"));
		System.out.println("No of rows::"+rows.size()); 
		for(int i=1;i<=rows.size();i++)
		{
			String act_data=driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+i+"]/td[2]")).getText();
			Thread.sleep(3000);
			System.out.println(expct_data+"----"+act_data); 
			Assert.assertEquals(expct_data,act_data,"userid is not matching");
			break;
		}
	}
	/*
	 * canValidation
	 */
	public static void canValidation(WebDriver driver,String expct1_data) throws Throwable
	{
		if(driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("can-searchBox"))).isDisplayed())
		{
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("can-searchBox"))).clear();
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("can-searchBox"))).sendKeys(expct1_data);
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("can-search"))).click();
		}
		else {
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("tog1-tip"))).click();
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("can-searchBox"))).clear();
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("can-searchBox"))).sendKeys(expct1_data);
			Thread.sleep(2000);
			driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("can-search"))).click();
		}
		WebElement table=driver.findElement(By.xpath(PropertyFileUtility.getValueForKey("can-table"))); 
		List<WebElement> rows=table.findElements(By.tagName("tr"));
		System.out.println("No of rows::"+rows.size()); 
		for(int i=1;i<=rows.size();i++)
		{
			String act_data=driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+i+"]/td[3]")).getText();
			Thread.sleep(3000);
			System.out.println(expct1_data+"----"+act_data); 
			Assert.assertEquals(expct1_data,act_data,"userid is not matching");
			break;
		}
	}
}
