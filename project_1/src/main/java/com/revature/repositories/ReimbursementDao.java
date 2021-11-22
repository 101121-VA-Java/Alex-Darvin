package com.revature.repositories;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementDao {
List<Reimbursement> getAllReimb();
Reimbursement getReimbById(int id);
Reimbursement addReimb(Reimbursement reimb);
}