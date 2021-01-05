package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Reimbursement;

public class ReimbursementDAOImpl implements ReimbursementDAO {
	private DAOConnection dc;
	
	UserDAO uDAO = new UserDAOImpl(dc);
	
	public ReimbursementDAOImpl() {
		dc = new DAOConnection();
	}
	
	public ReimbursementDAOImpl(DAOConnection dc) {
		//this.ds = ds;
		this.dc = dc;
	}
	
	@Override
	public Reimbursement viewReimbursement(int reimb_id) {
		Reimbursement thisReimb = new Reimbursement ();
		
		try (Connection con = dc.getDBConnection()) {
			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, reimb_id);
			ResultSet rs = ps.executeQuery();
			
			if(!rs.first()) {
				thisReimb = null;
			}
			
			else {
				do {
					 thisReimb = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3),
							rs.getTimestamp(4), rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8),
							rs.getInt(9), rs.getInt(10));
				} while(rs.next());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return thisReimb;
	}

	@Override
	public boolean submitTicket (int amount, String description, int userID, byte [] receipt, String type) {
		
		try (Connection con = dc.getDBConnection()) {
			int typeAsInt = 0;
			if (type.equalsIgnoreCase("Lodging") || type.equalsIgnoreCase("Travel") ||
					type.equalsIgnoreCase("Food") || type.equalsIgnoreCase("Other")) {
				switch(type) {
					case "Lodging":
						typeAsInt = 1;
						break;
					case "Travel":
						typeAsInt = 2;
						break;
					case "Food":
						typeAsInt = 3;
						break;
					case "Other":
						typeAsInt = 4;
						break;
				}
				
				String sql = "INSERT INTO ERS_REIMBURSEMENT (REIMB_AMOUNT, "
						+ "REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_RECEIPT, REIMB_TYPE_ID) "
						+ "VALUES (?, ?, ?, ?, ?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, amount);
				ps.setString(2, description);
				ps.setInt(3, userID);
				ps.setBytes(4, receipt);
				ps.setInt(5, typeAsInt);
				ps.executeUpdate();
				
				return true;
			}
			
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public List<Reimbursement> viewPastTicketsForUser(int userID) {
		List <Reimbursement> listForUser = new ArrayList<Reimbursement>();
		
		try (Connection con = dc.getDBConnection()) {
			String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, userID);
			ResultSet rs = ps.executeQuery();
			
			if(!rs.first()) {
				listForUser = null;
			}
			
			else {
				do {
					Reimbursement thisReimb = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3),
							rs.getTimestamp(4), rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8),
							rs.getInt(9), rs.getInt(10));
					listForUser.add(thisReimb);
				} while(rs.next());
			}
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listForUser;
	}

	@Override
	public List<Reimbursement> viewPastTicketsAllUsers() {
		List <Reimbursement> listForAllUsers = new ArrayList<Reimbursement>();
		
		try (Connection con = dc.getDBConnection()) {
			String sql = "SELECT * FROM ERS_REIMBURSEMENT";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			if(!rs.next()) {
				listForAllUsers = null;
			}
			
			do {
				Reimbursement thisReimb = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getTimestamp(3),
						rs.getTimestamp(4), rs.getString(5), rs.getBytes(6), rs.getInt(7), rs.getInt(8),
						rs.getInt(9), rs.getInt(10));
				listForAllUsers.add(thisReimb);
			} while(rs.next());
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listForAllUsers;
	}

	@Override
	public boolean approveRequest(int resolverID, int requestID) {
		try(Connection con = dc.getDBConnection()) {
			
			if (viewReimbursement(requestID).getAuthor() == resolverID) {
				return false;
			}
			
			String sql = "UPDATE ERS_REIMBURSEMENT SET REIMB_RESOLVED = ?, REIMB_RESOLVER = ?, "
					+ "REIMB_STATUS_ID = ? WHERE REIMB_ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			Timestamp ts = new Timestamp (System.currentTimeMillis());
			ps.setTimestamp(1, ts);
			ps.setInt(2, resolverID);
			ps.setInt(3, 2);
			ps.setInt(4, requestID);
			ps.executeUpdate();
			
			
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

	@Override
	public boolean rejectRequest(int resolverID, int requestID) {
		try(Connection con = dc.getDBConnection()) {
			
			if (viewReimbursement(requestID).getAuthor() == resolverID) {
				return false;
			}
			
			String sql = "UPDATE ERS_REIMBURSEMENT SET REIMB_RESOLVED = ?, REIMB_RESOLVER = ?, "
					+ "REIMB_STATUS_ID = ? WHERE REIMB_ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			Timestamp ts = new Timestamp (System.currentTimeMillis());
			ps.setTimestamp(1, ts);
			ps.setInt(2, resolverID);
			ps.setInt(3, 3);
			ps.setInt(4, requestID);
			ps.executeUpdate();
			
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

}
