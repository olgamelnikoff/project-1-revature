package com.example.eval;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dao.ReimbursementDAO;
import com.example.model.Reimbursement;
import com.example.service.ReimbService;

public class ReimbServiceTest {
	
	@Mock
	private ReimbursementDAO mockedDAO;
	private ReimbService testService =  new ReimbService(mockedDAO);
	private Reimbursement testReimbursement;
	private List<Reimbursement> list;
	
	@BeforeClass
	public static void setUpBeforeClass()  throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		testService =  new ReimbService(mockedDAO);
		byte [] array = {0, 0, 127};
		testReimbursement = new Reimbursement (2000, "trip to Lake Tahoe", 1, array, 1);
		list = new ArrayList<Reimbursement>();
		list.add(testReimbursement);
		
		when(mockedDAO.submitTicket(2000, "trip to Lake Tahoe", 1, array, "Lodging")).thenReturn(true);
		when(mockedDAO.viewPastTicketsForUser(3)).thenReturn(list);
		when(mockedDAO.viewPastTicketsForUser(7)).thenReturn(null);
		when(mockedDAO.approveRequest(2, 10)).thenReturn(true);
		when(mockedDAO.approveRequest(2, 20)).thenReturn(false);
		when(mockedDAO.rejectRequest(3, 30)).thenReturn(true);
		when(mockedDAO.rejectRequest(3, 40)).thenReturn(false);
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void verifyTicketSubmitSuccess() {
		byte [] array = {0, 0, 127};
		try {
			assertTrue(testService.verifyTicketSubmit(2000, "trip to Lake Tahoe", 1, array, "Lodging"));
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void verifyTicketSubmitFailure() throws SQLIntegrityConstraintViolationException {
		byte [] array = {0, 0, 127};
		assertFalse(testService.verifyTicketSubmit(2000, "trip to Lake Tahoe", 1, array, "Eating out"));
	}
	
	@Test
	public void getListOfAllTicketsForUserSuccess() throws SQLIntegrityConstraintViolationException {
		assertEquals(testService.getAllRecordsForUser(3), list);
	}
	
	@Test
	public void getListOfAllTicketsForUserFailure() throws SQLIntegrityConstraintViolationException {
		assertEquals(testService.getAllRecordsForUser(7), null);
	}
	
	@Test
	public void getListOfAllTicketsSuccess() {
		when(mockedDAO.viewPastTicketsAllUsers()).thenReturn(list);
		assertEquals(testService.getAllRecords(), list);
	
	}
	
	@Test
	public void getListOfAllTicketsTableEmpty() {
		when(mockedDAO.viewPastTicketsAllUsers()).thenReturn(null);
		assertEquals(testService.getAllRecords(), null);
	
	}
	
	@Test
	public void verifyApprovalSuccess() {
		try {
			assertTrue(testService.verifyRequestApproval(2, 10));
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void verifyApprovalFailure() {
		try {
			assertEquals(testService.verifyRequestApproval(2, 20), false);
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void verifyRejectionSuccess() {
		try {
			assertTrue(testService.verifyRequestRejection(3, 30));
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void verifyRejectionFailure() {
		try {
			assertEquals(testService.verifyRequestRejection(3, 40), false);
		} catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
		}
	}
}
