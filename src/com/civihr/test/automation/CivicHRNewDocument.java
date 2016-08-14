package com.civihr.test.automation;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class CivicHRNewDocument {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  System.setProperty("webdriver.gecko.driver", "./exe/geckodriver.exe");
	  DesiredCapabilities capabilities = DesiredCapabilities.firefox();
	  capabilities.setCapability("marionette", true);
	  driver = new FirefoxDriver(capabilities);  
    //driver = new FirefoxDriver();
    baseUrl = "http://civihr-staging.civihrhosting.co.uk";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCivicHRNewDocument() throws Exception {
    driver.get(baseUrl + "/welcome-page");
    Thread.sleep(10000);//driver.manage().timeouts().wait(25000);//implicitlyWait(25, TimeUnit.SECONDS);
    
    driver.findElement(By.id("edit-name")).clear();
    driver.findElement(By.id("edit-name")).sendKeys("civihr_admin");
    driver.findElement(By.id("edit-pass")).clear();
    driver.findElement(By.id("edit-pass")).sendKeys("civihr_admin");
    driver.findElement(By.id("edit-submit")).click();
    
    Thread.sleep(10000);//driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
    driver.get(baseUrl + "/index.php?q=civicrm/tasksassignments/dashboard#/documents");
    Thread.sleep(10000);
    
    driver.findElement(By.linkText("Add Document")).click();
    Thread.sleep(3000);
    
    new Select(driver.findElement(By.xpath("//div[@id='civitasks']/div/div/div/div/form/div[2]/div/div/div/div/select"))).selectByVisibleText("Joining Document 1");
    Thread.sleep(2000);
    driver.findElement(By.cssSelector("option[value=\"string:78\"]")).click();
    driver.findElement(By.id("ct-document-due")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("(//button[@type='button'])[35]")).click();
    driver.findElement(By.linkText("Add Assignee")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//div[@id='civitasks']/div/div/div/div/form/div[2]/div/div[4]/div[2]/div/div/span/span")).click();
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).clear();
    driver.findElement(By.xpath("(//input[@type='text'])[5]")).sendKeys("erik");
    Thread.sleep(2000);
    driver.findElement(By.cssSelector("div.ng-binding.ng-scope")).click();
    driver.findElement(By.xpath("//div[@id='civitasks']/div/div/div/div/form/div/div/div/div/div/span/span")).click();
    driver.findElement(By.xpath("//input[@type='text']")).clear();
    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("erik");
    Thread.sleep(2000);
    driver.findElement(By.xpath("(//div[@id='ui-select-choices-row-37-']/a/small)[2]")).click();
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    
    Thread.sleep(5000);
    
    assertEquals("Dr. Erik Prentice", driver.findElement(By.linkText("Dr. Erik Prentice")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
