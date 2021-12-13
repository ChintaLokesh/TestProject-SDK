package com.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeOptions;

import io.testproject.sdk.DriverBuilder;
import io.testproject.sdk.drivers.web.EdgeDriver;

public class FirstTest {
	
	public static EdgeDriver driver;
	
	@BeforeAll
	static void configure() throws MalformedURLException
	{
		driver = new DriverBuilder<EdgeDriver>(
		            new EdgeOptions()
		    )
		           .withRemoteAddress(new URL("http://localhost:8585"))
		            .withToken("2pHVg66vxXkBzFgQDYa3T_lQyEbgD801s9G5vEKRezs")
		            .build(EdgeDriver.class);
	}
	

	
	@Nested
	class WhenNoSearchResultAreFound{
	@Test
	void shouldDisplayEmptySearchResult()
	{
		String expectedValue="Search: noresults";
		driver.get("https://www.petrikainulainen.net/blog/");
		WebElement searchField=driver.findElement(By.id("s"));
		searchField.sendKeys("noresults");
		searchField.sendKeys(Keys.ENTER);
		String actualValue=driver.findElement(By.xpath("//h1[@class='archive_title headline']")).getText();
		assertEquals(actualValue, expectedValue);
	}
	}
	
	@AfterAll
	static void tearDown()
	{
		driver.quit();
	}

}
