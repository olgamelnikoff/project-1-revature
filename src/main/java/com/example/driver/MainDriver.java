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
		System.out.println(uCon);

		Javalin app = Javalin.create(config -> {
			config.addStaticFiles("/frontend");
		});

		app.start(7001);
		
		app.post("/employees/login", uCon.postLogin);
		app.get("/employees/session", uCon.getSessUser);
		app.get("/employees/:id/view-requests", rCon.viewRequests);
		app.post("/employees/:id/new-request", rCon.newRequest);
		
		app.exception(NullPointerException.class, (e, ctx)->{
			ctx.status(404);
			ctx.result("User Does not exists");
		});

		/*		app.post("/villains/login", vCon.postLogin);
				app.get("/villains/session", vCon.getSessVill);*/

		/*app.exception(NullPointerException.class, (e, ctx) -> {
			ctx.status(404);
			ctx.result("User Does not exists");
		});*/

//---------------------------------------------------------
		/*UserDAO newDAO = new UserDAOImpl();
		ReimbursementDAO rDAO = new ReimbursementDAOImpl();
		
		UserService us = new UserService(newDAO);
		
		ReimbService rs = new ReimbService(rDAO);
		
		List<Reimbursement> thisList = new ArrayList<>();
		
		byte [] array = {0, 0, 127};*/

		/*		try {
					rs.verifyTicketSubmit(3000, "Visit of Alcatraz", 3, array, "Travel");
				} catch (SQLIntegrityConstraintViolationException e) {
					// TODO Auto-generated catch block
					System.out.println("The user does not exist.");
				}*/

		/*try {
			thisList = rs.getAllRecordsForUser(3);
		} catch (SQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			System.out.println("The user does not exist.");
		}*/

		// thisList = rs.getAllRecords();

		/*for (Reimbursement r : thisList) {
			System.out.println(r.toString());
		}*/

		/*		try {
					rs.verifyRequestApproval(3, 100);
				} catch (SQLIntegrityConstraintViolationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/

		/*boolean registerBool = us.registerUser("hello", "zRRgQCxQ", "Errol", "Jacobs", "jacobs@comcast.net", "Finance Manager");
		System.out.println(registerBool);*/

		/*boolean result = us.verifyLoginCredentials("spacexdragon", "*9A7631C5E06BE18A6AD998264C58559B0A6550D6");
				
				//boolean result2 = us.verifyLoginCredentials("spacexdragon", "ttt");
				
				*/

		/*		boolean result3 = us.verifyLoginCredentials("ttt", "ttt");
				
				System.out.println(result3);*/
		// newDAO.login("spacexdragon", "*9A7631C5E06BE18A6AD998264C58559B0A6550D6");
		// newDAO.register("carbon", "dMXYpvYs", "Barbara", "Chavez",
		// "bchavez@gmail.com", "Employee");

		/*ReimbursementDAO reimbDAO = new ReimbursementDAOImpl();
		byte [] byteArray = new byte [] {0, 0, 127, 127};*/
		// reimbDAO.submitTicket(1000, "Networking in Washington, DC", 3, byteArray,
		// "Lodging");

		// reimbDAO.viewPastTicketsForUser(3);

		// reimbDAO.viewPastTicketsAllUsers();
		// reimbDAO.rejectRequest(5, 90);
	}

}
