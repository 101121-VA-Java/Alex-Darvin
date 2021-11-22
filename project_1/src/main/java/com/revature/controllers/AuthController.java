package com.revature.controllers;

import com.revature.services.UserServices;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class AuthController {
	
	private static UserServices as = new UserServices();
	
	public static void login(Context ctx) {
		// leverages x-www-form-urlencoded which prevents the parameters from being displayed in the url
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		String token = null;
		
		if(username != null && password != null) {
			token = as.login(username, password);
		}
		
		if(token != null) {
			// pass in the the generated token as the value of the "authorization header"
			ctx.header("Authorization", token);
			ctx.status(HttpCode.OK);
		} else {
			ctx.status(HttpCode.NOT_FOUND);
		}
	}
	
}