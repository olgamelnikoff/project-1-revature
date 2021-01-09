package com.example.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.example.dao.ReimbursementDAO;
import com.example.dao.UserDAO;
import com.example.dao.UserDAOImpl;
import com.example.model.Reimbursement;

public class ReimbService {
	private ReimbursementDAO ReimbServiceDAO;
	private UserDAO UserDAOInReimbService = new UserDAOImpl();
	
	public ReimbService() {
		// TODO Auto-generated constructor stub
	}

	public ReimbService(ReimbursementDAO reimbServiceDAO) {
		super();
		ReimbServiceDAO = reimbServiceDAO;
	}
	
	public Reimbursement verifyReimbursement(int id) throws SQLIntegrityConstraintViolationException {
		Reimbursement thisReimbursement = new Reimbursement();
		if (ReimbServiceDAO.viewReimbursement(id) == null) {
			System.out.println("Reimbursement does not exist.");
			throw new SQLIntegrityConstraintViolationException();
			
		}
		else {
			thisReimbursement = ReimbServiceDAO.viewReimbursement(id);
		}
		return thisReimbursement;
	}
	
	public boolean verifyTicketSubmit(int amount, String description, int userID, byte [] receipt, String type) throws SQLIntegrityConstraintViolationException {
		boolean ticketSubmitVerified = false;
		
		if (UserDAOInReimbService.returnUserById(userID) == null) {
			System.out.println("The user does not exist in the database");
			throw new SQLIntegrityConstraintViolationException();
		}
		
		else if (ReimbServiceDAO.submitTicket(amount, description, userID, receipt, type)) {
			ticketSubmitVerified = true;
		}
		else {
			System.out.println("Incorrect reimbursement type. "
					+ "Please enter \"Lodging\", \"Travel\", \"Food\" or \"Other\" for reimbursement type.");
		}
		return ticketSubmitVerified;
	}
	
	public List<Reimbursement> getAllRecordsForUser (int userID) throws SQLIntegrityConstraintViolationException {
		List<Reimbursement> newList = new ArrayList<>();
		
		if (UserDAOInReimbService.returnUserById(userID) == null) {
			System.out.println("The user does not exist in the database");
			throw new SQLIntegrityConstraintViolationException();
		}
		
		else {
			newList = ReimbServiceDAO.viewPastTicketsForUser(userID);
			
			if (newList == null) {
				System.out.println("No records found for this user.");
			}
			
		}
		
		return newList;
	}
	
	public List<Reimbursement> getAllRecords () {
		List<Reimbursement> newList = ReimbServiceDAO.viewPastTicketsAllUsers();
		
		if (newList == null) {
			System.out.println("The table is empty.");
		}
		
		return newList;
	}
	
	public boolean verifyRequestApproval(int resolverID, int requestID) throws SQLIntegrityConstraintViolationException {
		boolean approvalVerified = false;
		
		if (UserDAOInReimbService.returnUserById(resolverID) == null) {
			System.out.println("The resolver does not exist in the database");
			throw new SQLIntegrityConstraintViolationException();
		}
		
		else if (!ReimbServiceDAO.approveRequest(resolverID, requestID)) {
			System.out.println("You cannot approve your own request.");
		}
		else {
			approvalVerified = true;
		}
		
		return approvalVerified;
	}
	
	public boolean verifyRequestRejection(int resolverID, int requestID) throws SQLIntegrityConstraintViolationException {
		boolean rejectionVerified = false;
		
		if (UserDAOInReimbService.returnUserById(resolverID) == null) {
			System.out.println("The resolver does not exist in the database");
			throw new SQLIntegrityConstraintViolationException();
		}
		
		else if (!ReimbServiceDAO.rejectRequest(resolverID, requestID)) {
			System.out.println("You cannot reject your own request.");
		}
		else {
			rejectionVerified = true;
		}
		
		return rejectionVerified;
	}
}
