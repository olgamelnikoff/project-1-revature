package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.model.User;

public class UserDAOImpl implements UserDAO {

	private DAOConnection dc;

	public UserDAOImpl() {
		dc = new DAOConnection();
	}

	public UserDAOImpl(DAOConnection dc) {
		this.dc = dc;
	}

	public User returnUser(String username) {
		User thisUser = new User();
		try (Connection con = dc.getDBConnection()) {
			String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			if (!rs.first()) {
				return null;
			}

			thisUser = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getInt(7));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return thisUser;
	}

	public User returnUserById(int id) {
		User thisUser = new User();
		try (Connection con = dc.getDBConnection()) {
			String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERS_ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (!rs.first()) {
				return null;
			}

			thisUser = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getInt(7));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return thisUser;
	}

	public User returnUserByEmail(String email) {
		User thisUser = new User();
		try (Connection con = dc.getDBConnection()) {
			String sql = "SELECT * FROM ERS_USERS WHERE USER_EMAIL = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			if (!rs.first()) {
				return null;
			}

			thisUser = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getInt(7));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return thisUser;
	}

	@Override
	public boolean login(String username, String password) {

		try (Connection con = dc.getDBConnection()) {

			String sql1 = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME = ?";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1, username);
			ResultSet rs1 = ps1.executeQuery();

			if (!rs1.next()) {
				System.out.println("Username does not exist");
				return false;
			}

			String sql2 = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME = ? AND ERS_PASSWORD = ?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, username);
			ps2.setString(2, password);
			ResultSet rs2 = ps2.executeQuery();

			if (!rs2.next()) {
				System.out.println("The password is incorrect.");
				return false;
			}

			return true;

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public void register(String username, String password, String firstName, String lastName, String email,
			String role) {
		try (Connection con = dc.getDBConnection()) {
			int roleID;
			if (role.equalsIgnoreCase("Employee") || role.equalsIgnoreCase("Finance Manager")) {
				if (role.equalsIgnoreCase("Employee")) {
					roleID = 1;
				} else {
					roleID = 2;
				}

				String sql = "INSERT INTO ERS_USERS "
						+ "(ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID)"
						+ "VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, username);
				ps.setString(2, password);
				ps.setString(3, firstName);
				ps.setString(4, lastName);
				ps.setString(5, email);
				ps.setInt(6, roleID);
				ps.executeUpdate();
			}

			else {
				System.out.println("Incorrect role. Please enter \"Employee\" or \"Finance Manager\" for role");
			}

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
