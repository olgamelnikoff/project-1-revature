package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;

import io.javalin.http.Handler;

public class UserController {
	
	private UserService uServ;
	

	public Handler postLogin = (ctx) ->{
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
	};
	
	public Handler getSessUser = (ctx)->{
		User user = (User)ctx.sessionAttribute("user");
		ctx.json(user);
	};
	
	public Handler getNames = (ctx) ->{
		
		String this_Parameter = ctx.pathParam("id");
		int current_Param = Integer.parseInt(this_Parameter);
		
		User thisUser = uServ.getUserById(current_Param);
	
		ctx.json(thisUser);
		ctx.status(200);
	};
	
	public UserController() {
		// TODO Auto-generated constructor stub
	}

	public UserController(UserService uServ) {
		super();
		this.uServ = uServ;
	}

}
