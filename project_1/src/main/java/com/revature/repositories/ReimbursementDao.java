package com.revature.repositories;

import com.revature.models.Reimbursement;
import com.revature.models.User;

public interface ReimbursementDao {
	
	//create reimbursement dao for employees
	boolean submitReimbRequest(Reimbursement r);	
	Reimbursement viewPendingReimb(User u);	
	Reimbursement viewResolvedReimb(User u);
	
	//create reimbursement dao for managers
	Reimbursement viewPendingRequests();
	boolean updateRequest(Reimbursement r);
	Reimbursement viewResolvedRequests();
	Reimbursement viewRequest(User u);
	Reimbursement add(Reimbursement u);

}