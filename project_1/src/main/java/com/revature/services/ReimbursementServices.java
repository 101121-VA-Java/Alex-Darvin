package com.revature.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.revature.models.Reimbursement;
import com.revature.models.Status;
import com.revature.models.User;
import com.revature.repositories.DaoFactory;
import com.revature.repositories.ReimbursementDao;
import com.revature.repositories.ReimbursementPostgres;

public class ReimbursementServices {
	private ReimbursementDao rd;

	public ReimbursementServices() {
		rd = DaoFactory.getDAOFactory().getReimbursementDao();
	}
	
	public List<Reimbursement> getReimbursements() {
		List<Reimbursement> reimb = rd.getAll().stream().map(u -> {
			return u;
		}).collect(Collectors.toList());
		return reimb;
	}
	
	public Reimbursement getReimbById(int id){
		Reimbursement r = rd.getById(id);
		return r;
	}
	
	public List<Reimbursement> getReimbByAuthorId(int userId) {
		List<Reimbursement> r = rd.getByAuthorId(userId);
		return r;
	}
	
	public List<Reimbursement> getReimbByStatusId(int statusId,String filterName,String filterValue) {
		List<Reimbursement> r = rd.getByStatusId(statusId,filterName,filterValue);
		return r;
	}
	
	public int addReimb(String token, Reimbursement r) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		r.setStatus(new Status(1, token));
		r.setAuthor(new User(Integer.parseInt(token.split(":")[0])));
		
		r.setSubmit(timestamp);

		return rd.add(r);
	}
	
	public boolean updateReimb(Reimbursement r) {
		return rd.edit(r);
	}
	
	public boolean deleteReimbById(int id) {
		return rd.deleteById(id);
	}
}