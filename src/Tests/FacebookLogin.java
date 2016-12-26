package Tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FacebookLogin
{
	static WebDriver wd=null;
	public static void main(String[] args) throws Exception
	{
		String brname[]=new String[3];
		brname[0]="IE";
		brname[1]="Chrome";
		brname[2]="Firefox";
		String url="http://www.facebook.com";
		for (int i = 0; i < brname.length; i++)
		{
			if (brname[i].equalsIgnoreCase("IE"))
			{
				System.out.println("Initialising "+brname[i]+" driver");
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\IEDriverServer.exe");
				DesiredCapabilities dc=DesiredCapabilities.internetExplorer();
				dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				wd=new InternetExplorerDriver(dc);
			}
			if (brname[i].equalsIgnoreCase("Chrome"))
			{
				System.out.println("Initialising "+brname[i]+" driver");
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
				wd = new ChromeDriver();
			}
			if (brname[i].equalsIgnoreCase("Firefox"))
			{
				System.out.println("Initialising "+brname[i]+" driver");
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+ "\\geckodriver.exe");
		        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		        capabilities.setCapability("marionette", true);
		        wd = new FirefoxDriver(capabilities);
			}
			fblogintest(wd,url,brname[i]);
			wd=null;
		}
	}
	public static void fblogintest(WebDriver driver,String fburl,String brwnm) throws IOException
	{
		driver.get(fburl);
		driver.manage().window().maximize();
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("xyz@gmail.com");
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("pass");
		File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File(""+System.getProperty("user.dir")+"\\"+brwnm+".png"));
		driver.findElement(By.xpath(".//*[@id='u_0_l']")).click();
		driver.quit();
	}
}
