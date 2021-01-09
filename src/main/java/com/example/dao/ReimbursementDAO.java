package com.example.dao;

import java.util.List;
import com.example.model.Reimbursement;

public interface ReimbursementDAO {
	public Reimbursement viewReimbursement (int reimb_id);
	
	public boolean submitTicket(int amount, String description, int UserID, byte [] receipt, String type);
	
	public List <Reimbursement> viewPastTicketsForUser(int userID);
	
	public List <Reimbursement> viewPastTicketsAllUsers();
	
	public boolean approveRequest(int resolverID, int requestID);
	
	public boolean rejectRequest(int resolverID, int requestID);

}
