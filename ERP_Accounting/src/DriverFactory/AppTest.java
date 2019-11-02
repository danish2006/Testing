package DriverFactory;

import org.testng.annotations.Test;

public class AppTest {
	@Test
	public void kickStart() {
		try {
			DriverScript obj = new DriverScript();
			obj.startTest();
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
	}

}
