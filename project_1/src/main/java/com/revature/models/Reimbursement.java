package com.revature.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Reimbursement {
	
	private int id;
	private double amount;
	private boolean submitted;
	private boolean resolved;
	private String description;
	private String receipt;	
	private User author;	
	private User resolver;
	private Status status_id;
	private Type type_id;
	
	
	public Reimbursement(int id, double amount, boolean submitted, boolean resolved, String description,
			String receipt, User author, User resolver, Status status_id, Type type_id) {
		super();
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.status_id = status_id;
		this.type_id = type_id;
	}

	public Reimbursement(int id, double amount, boolean submitted, boolean resolved, boolean description, User user, Status status, Type type) {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public boolean isSubmitted() {
		return submitted;
	}
	
	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}
	
	public boolean isResolved() {
		return resolved;
	}
	
	public void setResolved(boolean resolved) {
		this.resolved = resolved;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getReceipt() {
		return receipt;
	}
	
	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}
	
	public User getAuthor() {
		return author;
	}
	
	public void setAuthor(User author) {
		this.author = author;
	}
	
	public User getResolver() {
		return resolver;
	}
	
	public void setResolver(User resolver) {
		this.resolver = resolver;
	}
	
	public Status getStatus_id() {
		return status_id;
	}
	
	public void setStatus_id(Status status_id) {
		this.status_id = status_id;
	}
	
	public Type getType_id() {
		return type_id;
	}
	
	public void setType_id(Type type_id) {
		this.type_id = type_id;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(amount, author, description, id, receipt, resolved, resolver, status_id, submitted,
				type_id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		return Double.doubleToLongBits(amount) == Double.doubleToLongBits(other.amount)
				&& Objects.equals(author, other.author) && description == other.description && id == other.id
				&& Objects.equals(receipt, other.receipt) && resolved == other.resolved
				&& Objects.equals(resolver, other.resolver) && Objects.equals(status_id, other.status_id)
				&& submitted == other.submitted && Objects.equals(type_id, other.type_id);
	}
	
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", receipt=" + receipt + ", author=" + author + ", resolver="
				+ resolver + ", status_id=" + status_id + ", type_id=" + type_id + "]";
	}
	
}