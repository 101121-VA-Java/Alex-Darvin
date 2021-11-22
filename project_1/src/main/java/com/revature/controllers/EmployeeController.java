package com.revature.controllers;

import com.revature.models.User;
import com.revature.services.UserServices;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class EmployeeController {
	
	private static UserServices es = new UserServices();
	
	
	public static void getEmployee(Context ctx) {

//		String token = ctx.header("Authorization");
		
		// pathParam("nameOfPathParam");
		int id = Integer.parseInt(ctx.pathParam("id"));
		User u = es.getEmployeeById(id);
		
		if (u != null) {
			ctx.json(u);
			ctx.status(HttpCode.OK);
		} else {
			ctx.status(404);
			ctx.status(HttpCode.NOT_FOUND);
		}
		
	}
	
	
	
}