package DDT;

import static org.testng.Assert.assertTrue;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import lib.ExcelDataConfig;

public class WordpressLoginExcel 
{
WebDriver driver;
	@Test(dataProvider="wordpressData")
	public void loginToWordpress(String username,String password) throws Exception
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Sindu\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://s1.demo.opensourcecms.com/wordpress/wp-login.php");
		driver.findElement(By.id("user_login")).sendKeys(username);
		driver.findElement(By.id("user_pass")).sendKeys(password);
		driver.findElement(By.xpath("//*[@id='wp-submit']")).click();
		Thread.sleep(5000);
		//System.out.println(driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains("Dashboard"),"User is not able to login-Invalid credentials");
		System.out.println("Successfully page verified!!!!");
	
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@DataProvider(name="wordpressData")
	public Object[][] passData()
	{
		
		ExcelDataConfig config=new ExcelDataConfig("C:\\Project\\eclipse\\This PC\\Desktop\\eclipse-workspace\\LearnAutomationDDT\\TestData\\InputData.xlsx");
		int rows=config.getRowCount(0);//SHEET 1 with index 0
		
		Object[][] data=new Object[rows][2];
		
		for(int i=0;i<rows;i++)
		{
			data[i][0]=config.getData(0,i,0);//first sheet,first row,first column
			data[i][1]=config.getData(0,i,1);//first sheet,first row,second column
		}
		
		return data;
	}
}
