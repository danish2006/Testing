package DriverFactory;

import org.openqa.selenium.WebDriver;

import CommonFunLibrary.FunctionLibrary;
import Utilities.ExcelFileUtil;

public class DriverScript
{
	WebDriver driver;
	public void startTest() throws Throwable
	{
		//create reference object for util methods
		ExcelFileUtil excel=new ExcelFileUtil();
		//iterate all row in master test cases
		for(int i=1;i<=excel.rowCount("MasterTestCases");i++)
		{
			String ModuleStatus="";
			if(excel.getCellData("MasterTestCases",i,2).equalsIgnoreCase("Y"))
			{
				//store module name into TCModule
				String TCModule=excel.getCellData("MasterTestCases",i,1);
				//iterate all rows in TCMOdule
				for(int j=1;j<=excel.rowCount(TCModule);j++)
				{
					//read all columns in TCModule testcase
					String Description=excel.getCellData(TCModule,j,0);
					String Object_Type=excel.getCellData(TCModule,j,1);
					String Locator_Type=excel.getCellData(TCModule,j,2);
					String Locator_Value=excel.getCellData(TCModule,j,3);
					String Test_Data=excel.getCellData(TCModule,j,4);
					try
					{
						if(Object_Type.equalsIgnoreCase("startBrowser"))
						{
							driver=FunctionLibrary.startBrowser(driver);
						}
						else if(Object_Type.equalsIgnoreCase("openApplication"))
						{
							FunctionLibrary.openApplication(driver);
						}
						else if(Object_Type.equalsIgnoreCase("waitForElement"))
						{
							FunctionLibrary.waitForElement(driver, Locator_Type, Locator_Value, Test_Data);
						}
						else if(Object_Type.equalsIgnoreCase("typeAction"))
						{
							FunctionLibrary.typeAction(driver, Locator_Type, Locator_Value, Test_Data); 
						}
						else if(Object_Type.equalsIgnoreCase("clickAction"))
						{
							FunctionLibrary.clickAction(driver, Locator_Type, Locator_Value); 
						}
						else if(Object_Type.equalsIgnoreCase("closeBrowser"))
						{
							FunctionLibrary.closeBrowser(driver); 
						}
						//write as pass into TCModule status column
						excel.setCellData(TCModule,j,5,"Pass");
						ModuleStatus="true";
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
						//write as Fail into TCModule status column
						excel.setCellData(TCModule, j, 5,"Fail");
						ModuleStatus="false";
						break;
					}
					if(ModuleStatus.equalsIgnoreCase("True"))
					{
						excel.setCellData("MasterTestCases",i,3,"Pass");
					}else if(ModuleStatus.equalsIgnoreCase("False"))
					{
						excel.setCellData("MasterTestCases",i,3,"Fail");
					}
				}
			}
			else
			{
				//write as not executed for testcases which are flag N
				excel.setCellData("MasterTestCases",i,3,"Not Executed");
			}
		}
	}
}
