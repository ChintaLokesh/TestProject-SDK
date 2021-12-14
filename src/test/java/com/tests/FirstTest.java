package com.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;

import io.testproject.sdk.DriverBuilder;
import io.testproject.sdk.drivers.web.ChromeDriver;
import io.testproject.sdk.drivers.web.EdgeDriver;

public class FirstTest {
	
	//public static ChromeDriver driver;
	public static EdgeDriver driver;
	static File src=null;
	static File dst=null;
	
	@BeforeAll
	static void configure() throws MalformedURLException
	{
		driver = new DriverBuilder<EdgeDriver>(
		            new EdgeOptions()
		    )
		           .withRemoteAddress(new URL("http://localhost:8585"))
		            .withToken("2pHVg66vxXkBzFgQDYa3T_lQyEbgD801s9G5vEKRezs")
		            .build(EdgeDriver.class);
		
		
//		 driver = new DriverBuilder<ChromeDriver>(new ChromeOptions())
//	                .withCapabilities(new ChromeOptions())
//	                .withToken("2pHVg66vxXkBzFgQDYa3T_lQyEbgD801s9G5vEKRezs")
//	                .build(ChromeDriver.class);
	}
	

	
	@Nested
	class WhenNoSearchResultAreFoundTest{
	@Test
	void shouldDisplayEmptySearchResult() throws IOException
	{
		System.out.println("Execution Started Successfully");
		String expectedValue="Search: noresults";
		driver.get("https://www.petrikainulainen.net/blog/");

		takesScreenshot("HomePage");
		FileUtils.copyFile(src, dst);
		WebElement searchField=driver.findElement(By.id("s"));
		searchField.sendKeys("noresults");
		takesScreenshot("SearchPage");
		FileUtils.copyFile(src, dst);
		searchField.sendKeys(Keys.ENTER);
		String actualValue=driver.findElement(By.xpath("//h1[@class='archive_title headline']")).getText();

		takesScreenshot("ResultsPage");
		FileUtils.copyFile(src, dst);
		assertEquals(actualValue, expectedValue);
	}
	}
	
	
	public static void takesScreenshot(String name)
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		dst= new File(System.getProperty("user.dir")+"/Screenshots/"+name+dtf.format(now)+".jpg");
	}
	
	@AfterAll
	static void tearDown()
	{
		driver.quit();
		System.out.println("Execution completed Successfully");
	}

}
