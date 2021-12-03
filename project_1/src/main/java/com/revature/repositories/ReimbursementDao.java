package com.revature.repositories;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDao extends GenericDao<Reimbursement>{
	public List<Reimbursement> getByStatusId(int id,String filterName,String filterValue);
	public List<Reimbursement> getByAuthorId(int id);
}