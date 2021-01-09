package com.example.eval.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dao.DAOConnection;
import com.example.dao.UserDAOImpl;
import com.example.model.User;

public class UnitDAOTest {

	@Mock
	private DAOConnection dc;

	@Mock
	private Connection c;

	@Mock
	private PreparedStatement ps;

	@Mock
	private ResultSet rs;

	private User testUser;

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
		testUser = new User(1, "spacexdragon", "hi", "Elon", "Musk", "musk@tesla.com", 2);
		when(rs.first()).thenReturn(true);
		when(rs.getInt(1)).thenReturn(testUser.getId());
		when(rs.getString(2)).thenReturn(testUser.getUsername());
		when(rs.getString(3)).thenReturn(testUser.getPassword());
		when(rs.getString(4)).thenReturn(testUser.getFirstName());
		when(rs.getString(5)).thenReturn(testUser.getLastName());
		when(rs.getString(6)).thenReturn(testUser.getEmail());
		when(rs.getInt(7)).thenReturn(testUser.getroleID());
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testReturnUserByIdSuccess() {
		int actualId = new UserDAOImpl(dc).returnUserById(1).getId();
		assertEquals(actualId, testUser.getId());
	}

	@Test
	public void testReturnUserSuccess() {
		String actualUsername = new UserDAOImpl(dc).returnUser("spacedragon").getUsername();
		assertEquals(actualUsername, testUser.getUsername());
	}

	@Test
	public void testReturnUserByEmailSuccess() {
		String actualEmail = new UserDAOImpl(dc).returnUserByEmail("musk@tesla.com").getEmail();
		assertEquals(actualEmail, testUser.getEmail());
	}

	@Test
	public void testLoginSuccess() {
		testReturnUserSuccess();
		String actualPassword = new UserDAOImpl(dc).returnUser("spacedragon").getPassword();
		assertEquals(actualPassword, testUser.getPassword());
	}
}
