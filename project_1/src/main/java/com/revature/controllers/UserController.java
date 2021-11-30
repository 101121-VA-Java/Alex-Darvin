package com.revature.controllers;

import java.util.List;

import com.revature.exceptions.EmployeeExistsException;
import com.revature.models.User;
import com.revature.services.AuthServices;
import com.revature.services.UserServices;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class UserController {

	private static UserServices us = new UserServices();
	private static AuthServices as = new AuthServices();

	public static void getUsers(Context ctx) {
		String token = ctx.header("Authorization");

		if (!as.checkPermission(token)) {
			ctx.status(HttpCode.UNAUTHORIZED);
			return;
		}
		List<User> users = us.getUsers();
		ctx.json(users);
		ctx.status(HttpCode.OK);
	}

	public static void registerUser(Context ctx) {
		User newUser = null;
		try {
			newUser = us.getUserById(us.addUser(ctx.bodyAsClass(User.class)));
		} catch (EmployeeExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (newUser == null) {
			ctx.status(HttpCode.BAD_REQUEST);
		} else {
			ctx.status(HttpCode.CREATED);
		}
	}

	public static void getUserById(Context ctx) {
		// pathParam("nameOfPathParam");

		int id = Integer.parseInt(ctx.pathParam("id"));

		User u = us.getUserById(id);

		if (u != null) {
			ctx.json(u);
			ctx.status(HttpCode.OK);
		} else {
//			ctx.status(404);
			ctx.status(HttpCode.NOT_FOUND);
		}
	}

	public static void updateUserInfo(Context ctx) {

		int id = Integer.parseInt(ctx.pathParam("id"));

		User u = ctx.bodyAsClass(User.class);

		u.setId(id);

		if (us.updateUser(u)) {
			ctx.status(HttpCode.OK);
		} else {
			ctx.status(400);
		}
	}
	
	public static void updateUserInfoManager(Context ctx) {

		String token = ctx.header("Authorization");

		if (!as.checkPermission(token)) {
			ctx.status(HttpCode.UNAUTHORIZED);
			return;
		}

		int id = Integer.parseInt(ctx.pathParam("id"));

		User u = ctx.bodyAsClass(User.class);

		u.setId(id);

		if (us.updateUser(u)) {
			ctx.status(HttpCode.OK);
		} else {
			ctx.status(400);
		}
	}

}