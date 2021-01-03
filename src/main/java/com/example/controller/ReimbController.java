package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Reimbursement;
import com.example.model.User;
import com.example.service.ReimbService;

import io.javalin.http.Handler;

public class ReimbController {
	private ReimbService rServ;
	
	public Handler viewRequests = (ctx) ->{
		
		String this_Parameter = ctx.pathParam("id");
		int current_Param = Integer.parseInt(this_Parameter);
		
		List<Reimbursement> reimbList = new ArrayList<>();
		reimbList = rServ.getAllRecordsForUser(current_Param);
		ctx.json(reimbList);
		ctx.status(200);
		//ctx.redirect("/html/employee-dashboard.html");
	};
	
public Handler newRequest = (ctx) ->{
		
	String this_Parameter = ctx.pathParam("id");
	int current_Param = Integer.parseInt(this_Parameter);
	System.out.println(current_Param);
		
		
		String amountAsString = ctx.formParam("amount");
		System.out.println(amountAsString);
		Integer amount = Integer.parseInt(amountAsString);
		String description = ctx.formParam("description");
		String receiptAsString = ctx.formParam("receipt");
		byte [] receipt = receiptAsString.getBytes("utf-8");
		String type = ctx.formParam("type");
		
		boolean submitted = rServ.verifyTicketSubmit(amount, description, current_Param, receipt, type);
		System.out.println(submitted);
		ctx.status(201);
		ctx.redirect("/html/employee-dashboard.html");
	};
	
	public Handler testFunction = (ctx) ->{
		System.out.println("Was in test function");
	};
		
	/*		System.out.println(ctx.formParam("username"));
			if(uServ.verifyLoginCredentials(ctx.formParam("username"), ctx.formParam("password"))) {
				System.out.println("Credentials are verified");
				ctx.sessionAttribute("user", uServ.getUserByUsername(ctx.formParam("username")));
				User user = (User)ctx.sessionAttribute("user");
				if (user.getroleID() == 1) {
					ctx.redirect("/html/employee-dashboard.html");
				}
				
				else {
					ctx.redirect("/html/finance-manager-dashboard.html");
				}
			}else {
				System.out.println("Credentials not verified");
				ctx.redirect("/html/unsuccessfullogin.html");
			}
		};*/
	
	public ReimbController() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ReimbController(ReimbService rServ) {
		super();
		this.rServ = rServ;
	}

}
