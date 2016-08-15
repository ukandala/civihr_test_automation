package com.civihr.test.automation;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddNewDocTest extends Commons {
	public static WebDriver driver = new FirefoxDriver();
	private String actLink;
	private boolean isDialogPresent;
	
	
	
	public AddNewDocTest() {
		
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Test
	public void step1_login(){		
		goToUrl("http://civihr-staging.civihrhosting.co.uk/welcome-page");
    	login("civihr_admin", "civihr_admin");
	}
    
    @Test
	public void step2_clickOnDocLink() throws InterruptedException{
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//a[@href='#/documents']")));
		driver.findElement(By.xpath(".//a[@href='#/documents']")).click();
		Thread.sleep(5000);
		actLink=driver.findElement(By.xpath("//*[@id='ct-dashboard']/div[1]/div/div[2]/div/a[1]")).getText();
		Assert.assertEquals("Add document link is present","Add Document",actLink.trim());
		
	}
    

	@Test
	public void step3_clickOnAddDocLink() throws InterruptedException{
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ct-dashboard']/div[1]/div/div[2]/div/a[1]")));
		driver.findElement(By.xpath("//*[@id='ct-dashboard']/div[1]/div/div[2]/div/a[1]")).click();
		Thread.sleep(5000);
		isDialogPresent=driver.findElement(By.xpath("//*[@class='form-horizontal form-document ng-scope ng-invalid ng-invalid-required ng-valid-date ng-dirty ng-valid-parse']")).isDisplayed();
		Assert.assertTrue("Add details Dialog is displayed", isDialogPresent);
	}
    
	@Test
	public void step4_enterDetailsSave(){
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='civitasks']/div/div[1]/div/div/form/div[1]/div/div[1]/div/div/span/span[1]")));
		driver.findElement(By.xpath(".//*[@id='civitasks']/div/div[1]/div/div/form/div[1]/div/div[1]/div/div/span/span[1]")).click();
		driver.findElement(By.xpath("//*[@id='civitasks']/div/div[1]/div/div/form/div[1]/div/div[1]/div/input[1]")).sendKeys("scotts@infomail.org");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.className("ui-select-choices-row-inner")));
		driver.findElement(By.className("ui-select-choices-row-inner")).click();
		Select select=new Select(driver.findElement(By.xpath(".//*[@id='civitasks']/div/div[1]/div/div/form/div[2]/div/div[1]/div/div/select")));
		select.selectByIndex(1);
		driver.findElement(By.id("ct-document-due")).click();		
		driver.findElement(By.xpath(".//*[contains(@id,'16')]/button")).click();
		driver.findElement(By.xpath(".//*[@id='civitasks']/div/div[1]/div/div/form/div[2]/div/div[4]/div[1]/span/a")).click();
		driver.findElement(By.xpath(".//*[@id='civitasks']/div/div[1]/div/div/form/div[2]/div/div[4]/div[2]/div/div/span/span[1]")).click();
		driver.findElement(By.xpath(".//*[@id='civitasks']/div/div[1]/div/div/form/div[2]/div/div[4]/div[2]/div/input[1]")).sendKeys("scotts@infomail.org");
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.className("ui-select-choices-row-inner")));
		driver.findElement(By.className("ui-select-choices-row-inner")).click();
		driver.findElement(By.xpath(".//*[@id='civitasks']/div/div[1]/div/div/form/div[3]/button[3]")).click();
		
		List<WebElement> docs=driver.findElements(By.xpath("//table[contains(@class,'ct-table-documents')]/tbody/tr"));
		for(WebElement doc:docs){
			if(doc.findElement(By.xpath("//td[2]/a")).getText().equalsIgnoreCase("Joining Document 1") 
					&& doc.findElement(By.xpath("//td[3]/a")).getText().equalsIgnoreCase("scotts@infomail.org")
					&& doc.findElement(By.xpath("//td[5]/a")).getText().equalsIgnoreCase("17/08/2016")
					){
				
				Assert.assertTrue("Document created successfully",true);
			}
				
		}
	}
    
    
    

}