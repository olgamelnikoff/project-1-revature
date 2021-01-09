package com.example.model;

import java.sql.Timestamp;
import java.util.Arrays;

public class Reimbursement {
	private int reimbID;
	private int amount;
	private Timestamp submitted;
	private Timestamp resolved;
	private String description;
	private byte [] receipt;
	private int author;
	private int resolver;
	private int status;
	private int type;
	
	public Reimbursement() {
		// TODO Auto-generated constructor stub
	}

	public Reimbursement(int reimbID, int amount, Timestamp submitted, Timestamp resolved, String description,
			byte[] receipt, int author, int resolver, int status, int type) {
		super();
		this.reimbID = reimbID;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}
	public Reimbursement(int reimbID, int amount, String description, int author, int type) {
		super();
		this.reimbID = reimbID;
		this.amount = amount;
		this.description = description;
		this.author = author;
		this.type = type;
	}

	public Reimbursement(int amount, String description, int author, byte[] receipt, int type) {
		super();
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.type = type;
	}
	
	public Reimbursement(int amount, String description, int author, int type) {
		super();
		this.amount = amount;
		this.description = description;
		this.author = author;
		this.type = type;
	}
	
	public Reimbursement(int amount, String description, int author, int status, int type) {
		super();
		this.amount = amount;
		this.description = description;
		this.author = author;
		this.status = status;
		this.type = type;
	}

	public Reimbursement(int amount, String description, byte[] receipt, int author, int status, int type) {
		super();
		this.amount = amount;
		this.description = description;
		this.receipt = receipt;
		this.author = author;
		this.status = status;
		this.type = type;
	}
	
	public Reimbursement(int amount, byte[] receipt, int author, int status, int type) {
		super();
		this.amount = amount;
		this.receipt = receipt;
		this.author = author;
		this.status = status;
		this.type = type;
	}

	public int getReimbID() {
		return reimbID;
	}

	public void setReimbID(int reimbID) {
		this.reimbID = reimbID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	public Timestamp getResolved() {
		return resolved;
	}

	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	public int getAuthor() {
		return author;
	}

	public void setAuthor(int author) {
		this.author = author;
	}

	public int getResolver() {
		return resolver;
	}

	public void setResolver(int resolver) {
		this.resolver = resolver;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Reimbursement [id =" + reimbID + ", amount =" + amount +  ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", receipt =" + receipt + ", author=" + author + ", resolver=" + resolver + ", status="
				+ status + ", type=" + type + "]";
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
		if (amount != other.amount)
			return false;
		if (author != other.author)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (!Arrays.equals(receipt, other.receipt))
			return false;
		if (reimbID != other.reimbID)
			return false;
		if (resolved == null) {
			if (other.resolved != null)
				return false;
		} else if (!resolved.equals(other.resolved))
			return false;
		if (resolver != other.resolver)
			return false;
		if (status != other.status)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
}
