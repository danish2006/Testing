package CommonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ERP_Functions {
	WebDriver driver;

	/*ProjectName:ERP_Stock_Accounting
	 *ModuleName:Launching Url
	 *TesterName:Danish Equbal
	 */
	public String launchUrl(String url) throws Throwable {
		String res = "";
		System.setProperty("webdriver.chrome.driver", "./CommonJars/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		Thread.sleep(4000);
		if (driver.findElement(By.id("btnsubmit")).isDisplayed()) {
			res = "Launch is successful";
		} else {
			res = "Launch is Failed";
		}
		return res;
	}
	/*ProjectName:ERP_Stock_Accounting
	 * ModuleName:Login
	 * TesterName:Danish Equbal
	 */
	public String login(String username,String password) throws Throwable
	{
		String res="";
		WebElement user=driver.findElement(By.xpath("//input[@id='username']"));
		WebElement pass=driver.findElement(By.xpath("//input[@id='password']"));
		user.clear();
		user.sendKeys(username);
		Thread.sleep(4000);
		pass.clear();
		pass.sendKeys(password);
		Thread.sleep(4000); 
		driver.findElement(By.xpath("//button[@id='btnsubmit']")).click();
		if(driver.findElement(By.xpath("//li[@id='mi_logout']//a[contains(text(),'Logout')]")).isDisplayed())
		{
			res="Login is Successful";
		}else{
			res="Login is Failed";
		}
		return res;
	}
	/*ProjectName:ERP_Stock_Accounting
	 * ModuleName:Logout
	 * TesterName:Danish Equbal
	 */
	public String verifyLogout()throws Throwable
	{
		String res="";
		driver.findElement(By.xpath("//li[@id='mi_logout']//a[contains(text(),'Logout')]")).click();
		Thread.sleep(4000);
		//driver.findElement(By.xpath("//button[@class='ajs-button btn btn-primary']")).click();
		if(driver.findElement(By.name("btnsubmit")).isDisplayed())
		{
			res="Application Logout is successful";
		}
		else
		{
			res="Application logout is failed";
		}	
		driver.close();
		return res;
	}
	/*ProjectName:ERP_Stock_Accounting
	 * ModuleName:Supplier Creation
	 * TesterName:Danish Equbal
	 */
	public String verifySupplier(String sname,String address,String city,String country,String cperson,String pnumber,String email,String mobile,String note) throws Throwable
	{
		String res="";
		driver.findElement(By.linkText("Suppliers")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//div[@class='panel-heading ewGridUpperPanel']//span[@class='glyphicon glyphicon-plus ewIcon']")).click();
		Thread.sleep(4000);
		//get supplier number
		String exp_data=driver.findElement(By.name("x_Supplier_Number")).getAttribute("value");
		driver.findElement(By.id("x_Supplier_Name")).sendKeys(sname);
		driver.findElement(By.id("x_Address")).sendKeys(address);
		driver.findElement(By.id("x_City")).sendKeys(city);
		driver.findElement(By.id("x_Country")).sendKeys(country);
		driver.findElement(By.id("x_Contact_Person")).sendKeys(cperson);
		driver.findElement(By.id("x_Phone_Number")).sendKeys(pnumber);
		driver.findElement(By.id("x__Email")).sendKeys(email);
		driver.findElement(By.id("x_Mobile_Number")).sendKeys(mobile);
		driver.findElement(By.id("x_Notes")).sendKeys(note);
		driver.findElement(By.id("btnAction")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		driver.findElement(By.xpath("//*[text()='OK!']")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//*[text()='OK'])[6]")).click();
		Thread.sleep(4000);
		if(driver.findElement(By.id("psearch")).isDisplayed())
		{
			driver.findElement(By.id("psearch")).clear();
			Thread.sleep(3000);
			driver.findElement(By.id("psearch")).sendKeys(exp_data);
			driver.findElement(By.id("btnsubmit")).click();
			Thread.sleep(3000);
		}
		else
		{
			driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-search ewIcon']")).click();
			driver.findElement(By.id("psearch")).clear();
			Thread.sleep(3000);
			driver.findElement(By.id("psearch")).sendKeys(exp_data);
			driver.findElement(By.id("btnsubmit")).click();
			Thread.sleep(3000);
		}
		
		String act_data=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]/div/span/span")).getText();
		System.out.println(exp_data+"    "+act_data);
		if(exp_data.equals(act_data))
		{
			res="Pass";
		}else{
			res="Fail";
		}
		return res;
	}
	
}
