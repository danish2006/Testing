package DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunLibrary.ERP_Functions;
import Utilities.ExcelFileUtil;

public class DataDrivenFramework 
{
	WebDriver driver;
	ERP_Functions erp=new ERP_Functions();
	@BeforeTest
	public void launchApp() throws Throwable
	{
		String launch=erp.launchUrl("http://webapp.qedge.com/");
		System.out.println(launch);
		String login=erp.login("admin","master");
		System.out.println(login);
	}
	@Test
	public void stock() throws Throwable
	{
		ExcelFileUtil xl=new ExcelFileUtil();
		int rc=xl.rowCount("Supplier");
		int cc=xl.colCount("Supplier");
		Reporter.log("No of Rows::"+rc+" "+"No of columns::"+cc,true); 
		for(int i=1;i<=rc;i++)
		{
			String sname=xl.getCellData("Supplier",i,0);
			String address=xl.getCellData("Supplier",i,1);
			String city=xl.getCellData("Supplier",i,2);
			String country=xl.getCellData("Supplier",i,3);
			String cperson=xl.getCellData("Supplier",i,4);
			String pnumber=xl.getCellData("Supplier",i,5);
			String email=xl.getCellData("Supplier",i,6);
			String mobile=xl.getCellData("Supplier",i,7);
			String notes=xl.getCellData("Supplier",i,8);
			String result=erp.verifySupplier(sname, address, city, country, cperson, pnumber, email, mobile, notes);
			xl.setCellData("Supplier",i, 9,result);
		}
	}
	@AfterTest
	public void logoutApp() throws Throwable
	{
		String logoutapp=erp.verifyLogout();
		System.out.println(logoutapp); 
		
	}
}