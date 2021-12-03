package com.revature.controllers;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.Role;
import com.revature.services.AuthServices;
import com.revature.services.ReimbursementServices;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class ReimbursementController {

	private static ReimbursementServices rs = new ReimbursementServices();
	private static AuthServices as = new AuthServices();

	public static void getReimbursements(Context ctx) {
		String token = ctx.header("Authorization");
		
		System.out.println("getReimbursements");

		if (!as.checkPermission(token, new Role(1))) {
			ctx.status(HttpCode.UNAUTHORIZED);
			return;
		}
		List<Reimbursement> r = rs.getReimbursements();
		ctx.json(r);
		ctx.status(HttpCode.OK);
	}

	public static void getReimbById(Context ctx) {

		String token = ctx.header("Authorization");

		if (!as.checkPermission(token)) {
			ctx.status(HttpCode.UNAUTHORIZED);
			return;
		}

		int rId = Integer.parseInt(ctx.pathParam("id"));
		Reimbursement r = rs.getReimbById(rId);
		if (r != null) {
			ctx.json(r);
			ctx.status(HttpCode.OK);
		} else {
//			ctx.status(404);
			ctx.status(HttpCode.NOT_FOUND);
		}
	}

	public static void getReimbByAuthorId(Context ctx) {
		String token = ctx.header("Authorization");

		if (!as.checkPermission(token)) {
			ctx.status(HttpCode.UNAUTHORIZED);
			return;
		}

		int id = Integer.parseInt(ctx.pathParam("id"));
		Reimbursement r = rs.getReimbById(id);
		if (r != null) {
			ctx.json(r);
			ctx.status(HttpCode.OK);
		} else {
//			ctx.status(404);
			ctx.status(HttpCode.NOT_FOUND);
		}
	}

	public static void getReimbByStatusId(Context ctx) {

		String authorId = ctx.queryParam("author_id");
		
		String filter=ctx.queryParam("filterBy");
		String filterValue=ctx.queryParam("filterValue");
		
		List<Reimbursement> r = null;
		List<Reimbursement> statusAndAuthorList = new ArrayList<>();
//		System.out.println("this is a test: " + authorId);
		Integer statusId = 0;
		if(null!=ctx.pathParam("id"))
			statusId = Integer.parseInt(ctx.pathParam("id"));
		
		if (authorId != null) {
			int authorNumber = Integer.parseInt(authorId.trim());
			r = rs.getReimbByAuthorId(authorNumber);
			
			if(null == statusId || statusId.compareTo(0) == 0)
				statusAndAuthorList= getAllReimbursementData(r);
			else
				statusAndAuthorList= getAllReimbursementDataByStatus(r,statusId);
			
			ctx.json(statusAndAuthorList);
			ctx.status(HttpCode.OK);
//			System.out.println("this is a test2: " + r);
		} else {
			r = rs.getReimbByStatusId(statusId,filter,filterValue);
			if (r != null) {
				ctx.json(r);
				ctx.status(HttpCode.OK);
			} else {
				ctx.status(HttpCode.NOT_FOUND);
			}
		}
	}
	
	public static List<Reimbursement> getAllReimbursementData(List<Reimbursement> r){
		List<Reimbursement> statusAndAuthorList = new ArrayList<>();
		for (Reimbursement statusAndAuthor : r) {
			statusAndAuthorList.add(statusAndAuthor);
		}
		return statusAndAuthorList;
	}

    public static List<Reimbursement> getAllReimbursementDataByStatus(List<Reimbursement> r,Integer statusId){
    	List<Reimbursement> statusAndAuthorList = new ArrayList<>();
    	for (Reimbursement statusAndAuthor : r) {
			if (statusAndAuthor.getStatus().getId() == statusId) {
				statusAndAuthorList.add(statusAndAuthor);
			}
		}	
    	return statusAndAuthorList;
	}
	
	public static void addReimb(Context ctx) {
		String token = ctx.header("Authorization");
		Reimbursement newReimb = null;
		
		newReimb = rs.getReimbById(rs.addReimb(token, ctx.bodyAsClass(Reimbursement.class)));
		
		if (newReimb == null) {
			ctx.status(HttpCode.BAD_REQUEST);
		} else {
			ctx.status(HttpCode.CREATED);
		}
	}

	public static void updateReimb(Context ctx) {
		String token = ctx.header("Authorization");

		if (!as.checkPermission(token)) {
			ctx.status(HttpCode.UNAUTHORIZED);
			return;
		}
		int id = Integer.parseInt(ctx.pathParam("id"));

		Reimbursement r = ctx.bodyAsClass(Reimbursement.class);

		r.setReimId(id);

		if (rs.updateReimb(r)) {
			ctx.status(HttpCode.OK);
		} else {
			ctx.status(400);
		}
	}

	public static void deleteReimbById(Context ctx) {

	}
}