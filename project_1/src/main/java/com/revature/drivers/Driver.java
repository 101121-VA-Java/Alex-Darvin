package com.revature.drivers;

import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

import com.revature.controllers.AuthController;
import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;

import io.javalin.Javalin;

public class Driver {

	public static void main(String[] args) {

		// Creating an instance of javalin and starting on port 8080
		Javalin app = Javalin.create((config) -> {
			config.enableCorsForAllOrigins();
			/*
			 * Enables CORS: Cross Origin Resource Sharing - protective mechanism built into
			 * most browsers - restricts resources to be only be allowed by webpages on the
			 * same domain
			 */
			config.defaultContentType = "application/json";
		});
		app.start(8080);

		/*
		 * Headers to tell the browser that the Authorization header that we're using for our "token" 
		 * in the Response body is safe to use, otherwise the browser may not accept it
		 */
		app.before(ctx -> {
		    ctx.header("Access-Control-Allow-Headers", "Authorization");
		    ctx.header("Access-Control-Expose-Headers", "Authorization");
		});
		
		app.routes(() -> {
			path("users", () -> {
				post(UserController::registerUser);
				get(UserController::getUsers);
				

				path("{id}", () -> {
					get(UserController::getUserById);
					put(UserController::updateUserInfo);
				});
			});
			
			path("reimbursements", () -> {
				post(ReimbursementController::addReimb);
				get(ReimbursementController::getReimbursements);
				
				path("{id}", () -> {
					get(ReimbursementController::getReimbById);
					put(ReimbursementController::updateReimb);


				});
				
				path("author", () -> {
					path("{id}", () -> {
						get(ReimbursementController::getReimbByAuthorId);
						put(ReimbursementController::updateReimb);
					});
				});
				
				path("status", () -> {
					path("{id}", () -> {
						get(ReimbursementController::getReimbByStatusId);
						put(ReimbursementController::updateReimb);
					});
				});
			});
			
			path("auth", () -> {
				post(AuthController::login);
			});
		});
		
	}

}