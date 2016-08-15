package com.civihr.test.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Commons extends Interactions {

	public Commons(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}
	
	public void goToUrl(String url){
		driver.get(url);
	}

	/**
	 * 
	 * @param userName
	 * @param password
	 */
	public void login(String userName, String password){
		
		driver.findElement(By.id("edit-name")).sendKeys(userName);
		driver.findElement(By.id("edit-pass")).sendKeys(password);
		driver.findElement(By.id("edit-submit")).click();
		
	}
	
}
