package com.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FinanceManagerDashboard {
	private WebDriver driver;
	private WebElement header;
	private WebElement continueAsEmployeeButton;
	private WebElement viewAllTicketsButton;
	
	public FinanceManagerDashboard(WebDriver driver) {
		this.driver = driver;
		this.navigateTo();
		
		this.header = driver.findElement(By.tagName("h2"));
		this.continueAsEmployeeButton = driver.findElement(By.id("continue-as-employee"));
		this.viewAllTicketsButton = driver.findElement(By.id("view-all-tickets"));
	}

	public String getHeader() {
		return this.header.getText();
	}
	
	public void continueAsEmployeeButtonClick() {
		this.continueAsEmployeeButton.click();
	}
	
	public void viewAllTicketsButtonClick() {
		this.viewAllTicketsButton.click();
	}
	
	public void navigateTo() {
		this.driver.get("http://localhost:7001/html/finance-manager-dashboard.html");
	}
}
