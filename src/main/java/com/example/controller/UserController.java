package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;

import io.javalin.http.Handler;

public class UserController {
	
	private UserService uServ;
	

	public Handler postLogin = (ctx) ->{
		System.out.println(ctx.formParam("username"));
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
		System.out.println((User)ctx.sessionAttribute("user"));
		User user = (User)ctx.sessionAttribute("user");
		ctx.json(user);
	};
	
	public UserController() {
		// TODO Auto-generated constructor stub
	}

	public UserController(UserService uServ) {
		super();
		this.uServ = uServ;
	}

}
