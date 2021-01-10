package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Reimbursement;
import com.example.service.ReimbService;

import io.javalin.http.Handler;

public class ReimbController {
	private ReimbService rServ;

	public Handler viewTicket = (ctx) -> {

		String this_Parameter = ctx.pathParam("id");
		int current_Param = Integer.parseInt(this_Parameter);

		Reimbursement thisReimbursement = rServ.verifyReimbursement(current_Param);

		ctx.json(thisReimbursement);
		ctx.status(200);
	};

	public Handler viewEmplRequests = (ctx) -> {

		String this_Parameter = ctx.pathParam("id");
		int current_Param = Integer.parseInt(this_Parameter);

		List<Reimbursement> reimbList = new ArrayList<>();
		reimbList = rServ.getAllRecordsForUser(current_Param);
		ctx.json(reimbList);
		ctx.status(200);
	};

	public Handler newRequest = (ctx) -> {

		String this_Parameter = ctx.pathParam("id");
		int current_Param = Integer.parseInt(this_Parameter);

		String amountAsString = ctx.formParam("amount");
		Integer amount = Integer.parseInt(amountAsString);
		String description = ctx.formParam("description");
		String receiptAsString = ctx.formParam("receipt");
		byte[] receipt = receiptAsString.getBytes("utf-8");
		String type = ctx.formParam("type");
		
		rServ.verifyTicketSubmit(amount, description, current_Param, receipt, type);
		ctx.status(201);
		ctx.redirect("/html/employee-dashboard.html");
	};

	public Handler viewRequests = (ctx) -> {
		List<Reimbursement> reimbList = new ArrayList<>();
		reimbList = rServ.getAllRecords();
		ctx.json(reimbList);
		ctx.status(200);
	};

	public Handler approveRequest = (ctx) -> {

		String reimb_Parameter = ctx.pathParam("reimb");
		int reimb = Integer.parseInt(reimb_Parameter);

		String resolver_Parameter = ctx.pathParam("resolver");
		int resolver = Integer.parseInt(resolver_Parameter);

		rServ.verifyRequestApproval(resolver, reimb);
		ctx.status(200);
	};

	public Handler rejectRequest = (ctx) -> {

		String reimb_Parameter = ctx.pathParam("reimb");
		int reimb = Integer.parseInt(reimb_Parameter);

		String resolver_Parameter = ctx.pathParam("resolver");
		int resolver = Integer.parseInt(resolver_Parameter);

		rServ.verifyRequestRejection(resolver, reimb);
		ctx.status(200);
	};

	public ReimbController() {
		// TODO Auto-generated constructor stub
	}

	public ReimbController(ReimbService rServ) {
		super();
		this.rServ = rServ;
	}

}
