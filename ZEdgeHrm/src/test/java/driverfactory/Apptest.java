package driverfactory;

import org.testng.annotations.Test;

public class Apptest
{
	@Test
	public void kickStart()
	{
		
		try {
			HybridDriven obj=new HybridDriven();
			obj.startTest();
		} catch (Throwable e) {
			
		System.out.println(e.getMessage());
		}
	}
}
