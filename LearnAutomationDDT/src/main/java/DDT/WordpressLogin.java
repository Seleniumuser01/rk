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

public class WordpressLogin 
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
		Object[][] data=new Object[3][2];//3 rows and 2 cols
		
		data[0][0]="admin1";//1st row,1st col
		data[0][1]="demo1";//1st row,2nd col
		
		data[1][0]="opensourcecms";//2nd row,1st col//opensourcecms-correct
		data[1][1]="opensourcecms";//2nd row,2nd col//opensourcecms-correct password
		
		data[2][0]="admin2";//3rd row,1st col
		data[2][1]="demo1234";//3rd row,2nd col
		
		return data;
	}
}
