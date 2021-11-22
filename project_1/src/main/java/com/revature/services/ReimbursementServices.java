package com.revature.services;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.repositories.ReimbursementDao;
import com.revature.repositories.ReimbursementPostgres;

public class ReimbursementServices {
	
	private ReimbursementDao rd = new ReimbursementPostgres();
	
	public List<Reimbursement> getAllReimb(){
		return rd.getAllReimb();
		
	}
	
	public Reimbursement addReimb(Reimbursement reimb) {
		return rd.addReimb(reimb);
	}
	
	public int getReimbById(int id) {
		return id;
		
	}
	
}