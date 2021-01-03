package com.example.service;

import com.example.dao.UserDAO;
import com.example.model.User;

public class UserService extends Exception {
	
	private UserDAO UserServiceDAO;
	
	public UserService() {
		// TODO Auto-generated constructor stub
	}

	public UserService(UserDAO userServiceDAO) {
		super();
		this.UserServiceDAO = userServiceDAO;
	}
	
	
	public User getUserByUsername(String username) {
		User thisUser = UserServiceDAO.returnUser(username);
		return thisUser;
	}
	
	public boolean getUserByEmail(String email) {
		User thisUser = UserServiceDAO.returnUserByEmail(email);
		
		if (thisUser == null) {
			return false;
		}
		return true;
	}
	
	/*public boolean verifyLoginCredentials (String username, String password) {
		
		boolean validCredentials = UserServiceDAO.login(username, password);
	
		return validCredentials;
	}*/
	
	public boolean verifyLoginCredentials(String name, String password) {
		 boolean isVerified = false;
		 
		 try {
		 User thisUser = getUserByUsername(name);
			 if(thisUser.getPassword().equals(password)) {
				 isVerified=true;
			 }
		 }
		 catch (NullPointerException e) {
			 System.out.println("User does not exist");
		 }
		 
		 return isVerified;
	}
	
	public boolean registerUser (String username, String password, String firstName, String lastName, String email,
			String role) {
		if (UserServiceDAO.returnUser(username) != null) {
			System.out.println("This username is taken; please enter another username.");
			return false;
		}
		
		else if (getUserByEmail(email) == true) {
			System.out.println("This email is taken; please enter another email.");
			return false;
		}
		
		else {
			UserServiceDAO.register(username, password, firstName, lastName, email, role); 
			return true;
		}
	
	}
}
