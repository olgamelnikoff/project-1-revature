package com.example.eval.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dao.DAOConnection;
import com.example.dao.ReimbursementDAOImpl;
import com.example.model.Reimbursement;

public class ReimbursementDAOTest {
	@Mock
	private DAOConnection dc;

	@Mock
	private Connection c;

	@Mock
	private PreparedStatement ps;

	@Mock
	private ResultSet rs;

	private Reimbursement testReimbursement;

	private List<Reimbursement> testListForUser;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(dc.getDBConnection()).thenReturn(c);
		when(c.prepareStatement(any(String.class))).thenReturn(ps);
		when(ps.executeQuery()).thenReturn(rs);
		testReimbursement = new Reimbursement(20, 5000, "business trip to attend SpaceX launching", 1, 2);
		testListForUser = new ArrayList<Reimbursement>();
		testListForUser.add(testReimbursement);
		when(rs.first()).thenReturn(true);
		when(rs.getInt(1)).thenReturn(testReimbursement.getReimbID());
		when(rs.getInt(2)).thenReturn(testReimbursement.getAmount());
		when(rs.getString(5)).thenReturn(testReimbursement.getDescription());
		when(rs.getInt(7)).thenReturn(testReimbursement.getAuthor());
		when(rs.getInt(10)).thenReturn(testReimbursement.getType());
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void viewReimbursementSuccess() {
		Reimbursement actualReimb = new ReimbursementDAOImpl(dc).viewReimbursement(20);
		assertEquals(actualReimb, testReimbursement);
	}

	@Test
	public void viewPastTicketsForUserSuccess() {
		List<Reimbursement> ticketsForThisUser = new ArrayList<>();
		ticketsForThisUser = new ReimbursementDAOImpl(dc).viewPastTicketsForUser(1);
		assertEquals(ticketsForThisUser, testListForUser);
	}
}
