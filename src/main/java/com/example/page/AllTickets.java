package com.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class AllTickets {
	private WebDriver driver;
	private WebElement header;
	private Select tableSelection;
	
	public AllTickets(WebDriver driver) {
		this.driver = driver;
		this.navigateTo();
		
		this.header = driver.findElement(By.tagName("h1"));
		this.tableSelection = new Select (driver.findElement(By.id("filter")));
	}

	public String getHeader() {
		return this.header.getText();
	}
	
	public void setTableSelection(String tableSelection) {
		this.tableSelection.selectByVisibleText(tableSelection);
	}
	
	public void navigateTo() {
		this.driver.get("http://localhost:7001/html/all-tickets.html");
	}

}
