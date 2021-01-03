package com.example.dao;

import com.example.model.User;

public interface UserDAO {
	
	public User returnUser(String username);
	
	public User returnUserByEmail (String email);
	
	public boolean login(String username, String password);
	
	public void register (String username, String password, String firstName, String lastName, String email, String role);

	public User returnUserById(int userID);

}
