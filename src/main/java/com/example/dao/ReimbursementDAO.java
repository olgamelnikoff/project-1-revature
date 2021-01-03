package com.example.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.util.List;

import com.example.model.Reimbursement;
import com.example.model.User;

public interface ReimbursementDAO {
	public Reimbursement viewReimbursement (int reimb_id);
	
	public boolean submitTicket(int amount, String description, int UserID, byte [] receipt, String type);
	
	public List <Reimbursement> viewPastTicketsForUser(int userID);
	
	public List <Reimbursement> viewPastTicketsAllUsers();
	
	public boolean approveRequest(int resolverID, int requestID);
	
	public boolean rejectRequest(int resolverID, int requestID);

}
