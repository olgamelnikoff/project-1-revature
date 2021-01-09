package com.example.eval.page;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.dao.DAOConnection;
import com.example.model.Reimbursement;
import com.example.page.AllTickets;
import com.example.page.EmployeeDashboard;
import com.example.page.FinanceManagerDashboard;
import com.example.page.LoginPage;
import com.example.page.SubmitRequestPage;

public class ApproveRequestTest {
	
	private LoginPage lp;
	private FinanceManagerDashboard fmd;
	private AllTickets allTicketsPage;
	private static WebDriver driver;
	private DAOConnection connection = new DAOConnection();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String filePath = "src/test/resources/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", filePath);
		
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.quit();
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		this.lp = new LoginPage(driver);
		Thread.sleep(1000);
		lp.setUsername("nectarine");
		Thread.sleep(1000);
		lp.setPassword("bye");
		Thread.sleep(1000);
		lp.submit();
		Thread.sleep(1000);
		fmd = new FinanceManagerDashboard(driver);
		Thread.sleep(1000);
		fmd.viewAllTicketsButtonClick();
		Thread.sleep(2000);
		allTicketsPage = new AllTickets(driver);
		Thread.sleep(2000);
		allTicketsPage.setTableSelection("Pending");
		Thread.sleep(2000);
	}
	@Test
	public void gotToAllTicketsTable() {
		assertEquals(1, 1);
	}
	
	/*@Test
	public void testSuccessfulSubmit() throws InterruptedException {
		page.setAmount("2000");
		Thread.sleep(1000);
		page.setDescriptionField("Test");
		Thread.sleep(1000);
		page.setTypeSelection("Travel");
		Thread.sleep(1000);
		assertEquals(page.getHeader(), "Submit a new request");
		page.submit();
		Thread.sleep(1000);
		
		WebDriverWait wait = new WebDriverWait(driver, 60);
		
		wait.until(ExpectedConditions.urlMatches("/employee-dashboard.html"));
		
		Reimbursement expectedReimb = new Reimbursement(2000, "Test", 1, 1, 2);
		
		Reimbursement actualReimb = new Reimbursement();
		
		try (Connection con = connection.getDBConnection()) {
			String sql = "SELECT REIMB_AMOUNT, REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID FROM ERS_REIMBURSEMENT WHERE REIMB_ID = (SELECT MAX(REIMB_ID) FROM ERS_REIMBURSEMENT)";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(!rs.first()) {
				actualReimb = null;
			}
			
			else {
				do {
					actualReimb = new Reimbursement(rs.getInt(1), rs.getString(2), rs.getInt(3), 
							rs.getInt(4), rs.getInt(5));
				} while(rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		assertEquals(expectedReimb, actualReimb);
	
	}*/}
