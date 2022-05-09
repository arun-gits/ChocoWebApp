package com.model;

public class TransactionModel {
	public int id;
	public String chocolate;
	public int price;
	public String date;
	public String payMode;
	public TransactionModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TransactionModel(int id, String chocolate, int price, String date, String payMode) {
		super();
		this.id = id;
		this.chocolate = chocolate;
		this.price = price;
		this.date = date;
		this.payMode = payMode;
	}
	
}
