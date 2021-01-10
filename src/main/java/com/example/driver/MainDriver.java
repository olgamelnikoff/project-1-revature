package com.example.driver;

import com.example.controller.ReimbController;
import com.example.controller.UserController;
import com.example.dao.DAOConnection;
import com.example.dao.ReimbursementDAOImpl;
import com.example.dao.UserDAOImpl;
import com.example.service.ReimbService;
import com.example.service.UserService;

import io.javalin.Javalin;

public class MainDriver {

	public static void main(String[] args) {

				UserController uCon = new UserController(new UserService(new UserDAOImpl(new DAOConnection())));
				ReimbController rCon = new ReimbController(new ReimbService(new ReimbursementDAOImpl(new DAOConnection())));
		
				Javalin app = Javalin.create(config -> {
					config.addStaticFiles("/frontend");
				});
		
				app.start(7001);
				
				app.post("/employees/login", uCon.postLogin);
				app.get("/employees/session", uCon.getSessUser);
				app.get("/employees/:id/get-first-and-last-name", uCon.getNames);
				app.get("/employees/:id/view-requests", rCon.viewEmplRequests);
				app.post("/employees/:id/new-request", rCon.newRequest);
				app.get("/employees/view-requests", rCon.viewRequests);
				app.post("/employees/:reimb/:resolver/approve", rCon.approveRequest);
				app.post("/employees/:reimb/:resolver/reject", rCon.rejectRequest);
				app.get("/reimbursements/:id", rCon.viewTicket);
				
				app.exception(NullPointerException.class, (e, ctx)->{
					ctx.status(404);
					ctx.result("User does not exists");
				});
				
				app.exception(IllegalArgumentException.class, (e, ctx)->{
					ctx.status(404);
					ctx.result("User does not exists");
				});
	}
}
