package com.example.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPage {
	
	private WebDriver driver;
	private WebElement header;
	private WebElement usernameField;
	private WebElement passwordField;
	private WebElement submitButton;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.navigateTo();
		
		this.header = driver.findElement(By.tagName("h1"));
		this.usernameField = driver.findElement(By.name("username"));
		this.passwordField = driver.findElement(By.name("password"));
		this.submitButton = driver.findElement(By.name("login-submit"));
	}
	
	public void setUsername(String name) {
		this.usernameField.clear();
		this.usernameField.sendKeys(name);
	}
	
	public String getUsername() {
		return this.usernameField.getAttribute("name");
	}
	
	public void setPassword(String password) {
		this.passwordField.clear();
		this.passwordField.sendKeys(password);
	}
	
	public String getPassword() {
		return this.passwordField.getAttribute("name");
	}
	
	public String getHeader() {
		return this.header.getText();
	}
	
	public void submit() {
		this.submitButton.click();
	}
	
	public void navigateTo() {
		this.driver.get("http://localhost:7001/html/login.html");
	}

}
