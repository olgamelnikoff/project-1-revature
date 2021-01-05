package com.example.eval.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

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
	private DataSource ds;
	
	@Mock
	private DriverManager dm; 
	
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
	public static void setUpBeforeClass()  throws Exception {
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(dc.getDBConnection()).thenReturn(c);
		when(c.prepareStatement(any(String.class))).thenReturn(ps);
		testUser = new User(1, "spacexdragon", "hi", "Elon", "Musk", "musk@tesla.com", 1);
		when(rs.first()).thenReturn(true);
		when(rs.getInt(1)).thenReturn(testUser.getId());
		when(rs.getString(2)).thenReturn(testUser.getUsername());
		when(rs.getString(3)).thenReturn(testUser.getPassword());
		when(rs.getString(4)).thenReturn(testUser.getFirstName());
		when(rs.getString(5)).thenReturn(testUser.getLastName());
		when(rs.getString(6)).thenReturn(testUser.getEmail());
		when(rs.getInt(7)).thenReturn(testUser.getroleID());
		when(ps.executeQuery()).thenReturn(rs);
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testReturnUserByIdSuccess() {
		assertEquals(new UserDAOImpl(dc).returnUserById(1).getId(), testUser.getId());
		assertEquals(new UserDAOImpl(dc).returnUserById(1).getUsername(), testUser.getUsername());
		assertEquals(new UserDAOImpl(dc).returnUserById(1).getPassword(), testUser.getPassword());
		assertEquals(new UserDAOImpl(dc).returnUserById(1).getFirstName(), testUser.getFirstName());
		assertEquals(new UserDAOImpl(dc).returnUserById(1).getLastName(), testUser.getLastName());
		assertEquals(new UserDAOImpl(dc).returnUserById(1).getEmail(), testUser.getEmail());
		assertEquals(new UserDAOImpl(dc).returnUserById(1).getroleID(), testUser.getroleID());
		/*User actualUser = new UserDAOImpl(dc).returnUserById(1);
		User expectedUser = testUser;
		assertThat(actualUser, is(expectedUser));*/
	}


}
