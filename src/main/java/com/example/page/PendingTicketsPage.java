package com.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PendingTicketsPage {
	private WebDriver driver;
	private WebElement header;
	private WebElement approveButton;
	
	public PendingTicketsPage (WebDriver driver) {
		this.driver = driver;
		this.navigateTo();
		
		this.header = driver.findElement(By.tagName("h1"));
		this.approveButton = driver.findElement(By.xpath("//table/tbody/tr[4]/td[11]"));
	}

	public String getHeader() {
		return this.header.getText();
	}
	
	public String getButtonText() {
		return approveButton.getText();
	}
	
	public void approveButtonClick() {
		this.approveButton.click();
		//this.approveButton.sendKeys(Keys.RETURN);
		System.out.println("Was in button click");
	}
	
	public void navigateTo() {
		this.driver.get("http://localhost:7001/html/pending.html");
	}
}
