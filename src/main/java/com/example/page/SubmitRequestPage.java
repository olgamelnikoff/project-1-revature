package com.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SubmitRequestPage {
	private WebDriver driver;
	private WebElement header;
	private WebElement amountField;
	private WebElement descriptionField;
	private WebElement chooseFileButton;
	private Select typeSelection;
	private WebElement submitButton;
	
	public SubmitRequestPage (WebDriver driver) {
		this.driver = driver;
		this.navigateTo();
		
		this.header = driver.findElement(By.tagName("h3"));
		this.amountField = driver.findElement(By.id("amount"));
		this.descriptionField = driver.findElement(By.id("description"));
		this.chooseFileButton = driver.findElement(By.id("receipt"));
		this.typeSelection = new Select (driver.findElement(By.id("type")));
		this.submitButton = driver.findElement(By.id("requestSubmit"));
	}
	
	public void setAmount(String amount) {
		this.amountField.clear();
		this.amountField.sendKeys(amount);
	}
	
	public String getAmount() {
		return this.amountField.getAttribute("id");
	}
	
	public String getDescriptionField() {
		return this.descriptionField.getText();
	}

	public void setDescriptionField(String descriptionField) {
		this.descriptionField.clear();
		this.descriptionField.sendKeys(descriptionField);
	}

	public WebElement getChooseFileButton() {
		return chooseFileButton;
	}

	public void setChooseFileButton(WebElement chooseFileButton) {
		this.chooseFileButton = chooseFileButton;
	}

	public void setTypeSelection(String typeSelection) {
		this.typeSelection.selectByVisibleText(typeSelection);
	}

	public String getHeader() {
		return this.header.getText();
	}
	
	public void submit() {
		this.submitButton.click();
	}
	
	public void navigateTo() {
		this.driver.get("http://localhost:7001/html/submit-request.html");
	}

}
