package com.servlet;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.candyshop.dao.ConnectionUtil;

import com.candyshop.model.User;
import com.model.TransactionModel;

public class ConnectionDAO {
	public static Connection getConnection() throws Exception,SQLException {
		Connection connect = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect=DriverManager.getConnection("jdbc:mysql://101.53.133.59:3306/revature_training_db","rev_user","rev_user");
		System.out.println("Processing...");
		return connect;
	}
	public static int sessionId(String email) throws Exception {
		int id = 0;
		Connection connect = getConnection();
		String query = "select * from candy_users where user_mail='"+email+"'";
		
		Statement statement = connect.createStatement();
		ResultSet rs = statement.executeQuery(query);
		
		if(rs.next()) {
			id = rs.getInt("user_id");
		}
		
		return id;
		
	}
	public static void transactionInserter(int sid,int id,String paymentMode) throws Exception
	{
		Connection connection=ConnectionUtil.getConnection();
		PreparedStatement statement=null;
//		ResultSet result=null;
	    Date date=Date.valueOf(LocalDate.now());
		String query="insert into candy_trans(user_id,choco_id,purchased_on,payment_mode)values(?,?,?,?)";
		statement=connection.prepareStatement(query);
		statement.setInt(1, sid);
		statement.setInt(2, id);
		statement.setDate(3, date);
		statement.setString(4, paymentMode);
		statement.executeUpdate();
	}
	public static int sessionNId(String number) throws Exception {
		int id = 0;
		Connection connect = getConnection();
		String query = "select * from candy_users where user_mobile='"+number+"'";
		
		Statement statement = connect.createStatement();
		ResultSet rs = statement.executeQuery(query);
		
		if(rs.next()) {
			id = rs.getInt("user_id");
		}
		
		return id;
		
	}
	public static User userDetails(String mail) throws Exception {
		Connection connect = null;
		PreparedStatement show = null;
		ResultSet data = null;

		connect = ConnectionUtil.getConnection();
		String query = "select * from candy_users where user_mail=?";
		show = connect.prepareStatement(query);
		show.setString(1, mail);
		data = show.executeQuery();
		User user = new User();
		while (data.next()) {
			String name = data.getString("user_name");
			String mobile = data.getString("user_mobile");
			String email = data.getString("user_mail");
			String address = data.getString("user_address");

			user.setAddress(address);
			user.setName(name);
			user.setMobile(mobile);
			user.setEmail(email);

		}
		connect.close();
		return user;
	}
	public static User userNDetails(String mail) throws Exception {
		Connection connect = null;
		PreparedStatement show = null;
		ResultSet data = null;

		connect = ConnectionUtil.getConnection();
		String query = "select * from candy_users where user_mobile=?";
		show = connect.prepareStatement(query);
		show.setString(1, mail);
		data = show.executeQuery();
		User user = new User();
		while (data.next()) {
			String name = data.getString("user_name");
			String mobile = data.getString("user_mobile");
			String email = data.getString("user_mail");
			String address = data.getString("user_address");

			user.setAddress(address);
			user.setName(name);
			user.setMobile(mobile);
			user.setEmail(email);

		}
		connect.close();
		return user;
	}

	public static List<TransactionModel> transactionDetails(int sessionId) throws Exception{
		Connection connect = getConnection();
		List<TransactionModel> transactions = new ArrayList<TransactionModel>();
		Statement statement = connect.createStatement();
		int id = 0;
		String chocolate = null;
		int price = 0;
		String purchasedOn = null;
		String payMode = null;
		String query = "SELECT candy_trans.id,candies_list.choco_name,candies_list.price,candy_trans.purchased_on,candy_trans.payment_mode FROM candy_trans INNER JOIN candies_list ON candy_trans.choco_id=candies_list.choco_id WHERE candy_trans.user_id="+sessionId;
		ResultSet data = statement.executeQuery(query);
		while(data.next()) {
			id = data.getInt("id");
			chocolate = data.getString("choco_name");
			price = data.getInt("price");
			purchasedOn = data.getString("purchased_on");
			payMode = data.getString("payment_mode");
			TransactionModel object = new TransactionModel(id,chocolate,price,purchasedOn,payMode);
			transactions.add(object);
		}
		return transactions;
		
	}
}
