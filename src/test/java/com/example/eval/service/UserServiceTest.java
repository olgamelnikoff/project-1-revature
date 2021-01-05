package com.example.eval.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.dao.UserDAO;
import com.example.model.User;
import com.example.service.UserService;

public class UserServiceTest {
	
	@Mock
	private UserDAO mockedDAO;
	private UserService testService =  new UserService(mockedDAO);
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
		testService =  new UserService(mockedDAO);
		testUser = new User(20, "goldfish", "MKGjJfYc", "Rocky", "Skinner", "rskinner@yahoo.com", 1);
		when(mockedDAO.login("goldfish", "MKGjJfYc")).thenReturn(true);
		when(mockedDAO.returnUser("goldfish")).thenReturn(testUser);
		when(mockedDAO.returnUserByEmail("rskinner@yahoo.com")).thenReturn(testUser);
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void verifyLoginCredentialsSuccess() {
		assertTrue(testService.verifyLoginCredentials("goldfish", "MKGjJfYc"));
	}
	
	@Test
	public void verifyLoginCredentialsUserDoesNotExist() {
		assertFalse(testService.verifyLoginCredentials("ttt", "MKGjJfYc"));
	}
	
	@Test
	public void verifyLoginCredentialsWrongPassword() {
		assertFalse(testService.verifyLoginCredentials("goldfish", "ttt"));
	}
	
	@Test
	public void registerUserSuccess() {
		assertTrue(testService.registerUser("snowstorm", "pgaPTYeM", "Kate", "Preston", "kpreston@gmail.com", "Employee"));
	}
	
	@Test
	public void registerUserUsernameExists() {
		assertFalse(testService.registerUser("goldfish", "pgaPTYeM", "Kate", "Preston", "kpreston@gmail.com", "Employee"));
	}
	
	@Test
	public void registerUserEmailExists() {
		assertFalse(testService.registerUser("theminions", "pgaPTYeM", "Roy", "Skinner", "rskinner@yahoo.com", "Employee"));
	}

}
