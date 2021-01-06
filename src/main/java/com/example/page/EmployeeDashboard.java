package com.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class EmployeeDashboard {
	
	private WebDriver driver;
	private WebElement header;
	private WebElement addReimbursementRequestButton;
	private WebElement viewPastTicketsButton;
	
	public EmployeeDashboard(WebDriver driver) {
		this.driver = driver;
		this.navigateTo();
		
		this.header = driver.findElement(By.tagName("h2"));
		this.addReimbursementRequestButton = driver.findElement(By.id("add-request"));
		this.viewPastTicketsButton = driver.findElement(By.id("view-tickets"));
	}

	public String getHeader() {
		return this.header.getText();
	}
	
	public void submitRequestButtonClick() {
		this.addReimbursementRequestButton.click();
	}
	
	public void viewTicketsButtonClick() {
		this.viewPastTicketsButton.click();
	}
	
	public void navigateTo() {
		this.driver.get("http://localhost:7001/html/employee-dashboard.html");
	}

}