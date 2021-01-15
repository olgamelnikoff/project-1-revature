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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.dao.DAOConnection;
import com.example.model.Reimbursement;
import com.example.page.AllTickets;
import com.example.page.FinanceManagerDashboard;
import com.example.page.LoginPage;
import com.example.page.PendingTicketsPage;

public class ApproveRequestTest {

	private LoginPage lp;
	private FinanceManagerDashboard fmd;
	private AllTickets allTicketsPage;
	private PendingTicketsPage pendingPage;
	private static WebDriver driver;
	private DAOConnection connection = new DAOConnection();
	int buttonID;

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
		lp.setPassword("password");
		Thread.sleep(1000);
		lp.submit();
		Thread.sleep(1000);
		fmd = new FinanceManagerDashboard(driver);
		Thread.sleep(1000);
		fmd.viewAllTicketsButtonClick();
		Thread.sleep(1000);
		allTicketsPage = new AllTickets(driver);
		Thread.sleep(2000);
		pendingPage = new PendingTicketsPage(driver);
		Thread.sleep(2000);
	}

	@Test
	public void approveRequestSuccess() throws InterruptedException {
		assertEquals(pendingPage.getHeader(), "Pending Tickets For All Employees");
		Thread.sleep(3000);
		System.out.println (pendingPage.getButtonText());
		WebDriver driver = pendingPage.getDriver();
		/*JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", pendingPage.getButton());*/
		WebElement currentButton = driver.findElement(By.xpath("//table/tbody/tr[3]/td[11]"));
		System.out.println(currentButton.isEnabled());
		WebElement my_element = new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(pendingPage.getButton()));
		my_element.click();
		Thread.sleep(3000);

		Reimbursement expectedReimb = new Reimbursement(2000, "Test", 1, 1, 2);

		Reimbursement actualReimb = new Reimbursement();

		try (Connection con = connection.getDBConnection()) {
			String sql = "SELECT REIMB_AMOUNT, REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_STATUS_ID, REIMB_TYPE_ID FROM ERS_REIMBURSEMENT WHERE REIMB_ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, 50130);
			ResultSet rs = ps.executeQuery();

			if (!rs.first()) {
				actualReimb = null;
			}

			else {
				do {
					actualReimb = new Reimbursement(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),
							rs.getInt(5));
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(expectedReimb.getStatus(), actualReimb.getStatus());
	}
}
