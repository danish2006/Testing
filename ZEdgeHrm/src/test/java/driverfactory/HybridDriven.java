package driverfactory;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonfunctions.OrangeFunctions;
import commonutilities.ExcelUtility;
import commonutilities.ScreenshotUtility;

public class HybridDriven {
	WebDriver driver;
	ExtentReports report;
	ExtentTest test;

	// start Test
	public void startTest() throws Throwable {
		ExcelUtility excel = new ExcelUtility();
		// iterate over all rows in MastertestCases
		for (int i = 1; i <= excel.rowCount("MasterTestCase"); i++) {
			String ModuleStatus = "";
			if (excel.getData("MasterTestCase", i, 2).equalsIgnoreCase("Y")) {
				// store Module into TCModule
				String TCModule = excel.getData("MasterTestCase", i, 1);
				//generate report
				report=new ExtentReports("./Reports/"+" "+TCModule+" "+OrangeFunctions.generateDate()+".html");
				// iterate all rows in TCModule
				for (int j = 1; j <= excel.rowCount(TCModule); j++) 
				{
					test=report.startTest(TCModule);
					//read all columns in TCModule testcase
					String Description=excel.getData(TCModule,j,0);
					String Object_Type=excel.getData(TCModule,j,1);
					String Locator_Type=excel.getData(TCModule,j,2);
					String Locator_Value=excel.getData(TCModule,j,3);
					String Test_Data=excel.getData(TCModule,j,4);
					try {
						if(Object_Type.equalsIgnoreCase("startBrowser"))
						{
							driver=OrangeFunctions.startBrowser(driver);
							test.log(LogStatus.INFO,Description);
						}
						else if(Object_Type.equalsIgnoreCase("openApplication"))
						{
							OrangeFunctions.openApplication(driver);
							test.log(LogStatus.INFO,Description);
						}
						else if(Object_Type.equalsIgnoreCase("waitForElement"))
						{
							OrangeFunctions.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
							test.log(LogStatus.INFO,Description);
						}
						else if(Object_Type.equalsIgnoreCase("typeAction"))
						{
							OrangeFunctions.typeAction(driver, Locator_Type, Locator_Value, Test_Data);
							test.log(LogStatus.INFO,Description);
						}
						else if(Object_Type.equalsIgnoreCase("clickAction"))
						{
							OrangeFunctions.clickAction(driver, Locator_Type, Locator_Value);
							test.log(LogStatus.INFO,Description);
						}
						else if(Object_Type.equalsIgnoreCase("closeBrowser"))
						{
							OrangeFunctions.closeBrowser(driver); 
							test.log(LogStatus.INFO,Description);
						}
						else if(Object_Type.equalsIgnoreCase("captureData"))
						{
							OrangeFunctions.captureData(driver,Locator_Type,Locator_Value);
							test.log(LogStatus.INFO,Description);
						}
						else if(Object_Type.equalsIgnoreCase("tableValidation"))
						{
							OrangeFunctions.tableValidation(driver,Test_Data);
							test.log(LogStatus.INFO,Description);
						}
						else if(Object_Type.equalsIgnoreCase("dropdownSelection"))
						{
							OrangeFunctions.dropdownSelection(driver, Locator_Type, Locator_Value, Test_Data);
							test.log(LogStatus.INFO,Description);
						}
						else if(Object_Type.equalsIgnoreCase("adminValidation"))
						{
							OrangeFunctions.adminValidation(driver, Test_Data);
							test.log(LogStatus.INFO,Description);
						}
						else if(Object_Type.equalsIgnoreCase("canValidation"))
						{
							OrangeFunctions.canValidation(driver, Test_Data);
							test.log(LogStatus.INFO,Description);
						}
						excel.setCellData(TCModule,j,5,"Pass");
						test.log(LogStatus.PASS,Description);
						ModuleStatus="true";
					}
					catch (Exception e) {
						System.out.println(e.getMessage());
						excel.setCellData(TCModule,j,5,"Fail");
						test.log(LogStatus.FAIL, Description);
						ScreenshotUtility.screenshot(driver,Description);
						ModuleStatus="false";
						break;
					}
					if(ModuleStatus.equalsIgnoreCase("true"))
					{
						excel.setCellData("MasterTestCase",i,3,"Pass");
					}
					else if(ModuleStatus.equals("fail")) 
					{
						excel.setCellData("MasterTestCase",i,3,"Fail");
					}
					report.flush();
					report.endTest(test);
				}
			}
			else {
				//write as not executed for testcases which are flag N
				excel.setCellData("MasterTestCase",i,3,"Not Executed");
			}
		}
	}
}
