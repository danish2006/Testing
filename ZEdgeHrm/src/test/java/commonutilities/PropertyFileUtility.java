package commonutilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtility {
	public static String getValueForKey(String key)
	{
		Properties configprop=null;
		try {
		 configprop=new Properties();
		FileInputStream fis=new FileInputStream("./PropertyFile/OR.properties");
		configprop.load(fis);	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return configprop.getProperty(key);
		
	}

}
