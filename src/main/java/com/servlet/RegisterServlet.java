package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.candyshop.dao.UserDAO;
import com.candyshop.logic.RegistrationValidation;
import com.candyshop.model.User;
import com.google.gson.Gson;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String city = request.getParameter("city");
		
		int testName = 0;
		int testNumber = 0;
		int testMail = 0;
		int testPassword = 0;
		
		Gson gson = new Gson();
		String message = null;
		
		User user=new User();
		
		user.setName(name);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setAddress(city);
		user.setPassword(password);
		
		RegistrationValidation test=new RegistrationValidation();
		UserDAO validUser=new UserDAO();
		
		try {
			testName=test.validateName(user.getName());
			testNumber=test.validateMobile(user.getMobile());
			testMail=test.validateEmail(user.getEmail());
			testPassword=test.validatePassword(user.getPassword());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			message = e.getMessage();
			//e.printStackTrace();
		}
		if(testName+testNumber+testMail+testPassword==4) {
			System.out.println("Validation Passed");
			try {
				validUser.signUpMailValidation(user.getEmail());
				validUser.signUpNumberValidation(user.getMobile());
				System.out.println("Validation using Database passed");
				int rows = validUser.addUser(user);
				if(rows==1) {
					message = "Welcome to Candy shop "+name;
				}
				else {
					message = "Unknown error occurred";
				}
			}
			catch(Exception e) {
				
				System.out.println(e.getMessage());
				message = e.getMessage();
				//e.printStackTrace();
			}
		}
		
		String output = gson.toJson(message);
		
		PrintWriter out = response.getWriter();
		out.println(output);
		out.flush();
		
		
//		else {
//			System.exit(0);
//		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
