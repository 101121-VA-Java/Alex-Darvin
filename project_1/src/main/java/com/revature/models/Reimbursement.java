package com.revature.models;

import java.util.Objects;

public class Reimbursement {
	
	private int id;
	private double amount;
	private boolean submitted;
	private boolean resolved;
	private boolean description;
	private String receipt;	
	private User author;	
	private User resolver;
	
	
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
	public boolean isDescription() {
		return description;
	}
	public void setDescription(boolean description) {
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
	
	@Override
	public int hashCode() {
		return Objects.hash(amount, author, description, id, receipt, resolved, resolver, submitted);
	}
	
	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", receipt=" + receipt + ", author=" + author + ", resolver="
				+ resolver + "]";
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
				&& Objects.equals(resolver, other.resolver) && submitted == other.submitted;
	}
}