package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.candyshop.model.Chocolates;
import com.google.gson.Gson;
import com.model.TransactionModel;

/**
 * Servlet implementation class TransactionServlet
 */
@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransactionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String sessionEmail = request.getParameter("sessionEmail");
		int sessionId = 0;
		List<TransactionModel> transactions = new ArrayList<TransactionModel>();
		String message = null;
		if(sessionEmail.contains("@")) {
			try {
				sessionId = ConnectionDAO.sessionId(sessionEmail);
				System.out.println("sessionid "+sessionId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				message = e.getMessage();
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			}
		else {
				try {
					sessionId = ConnectionDAO.sessionNId(sessionEmail);
					System.out.println("sessionid "+sessionId);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					message = e.getMessage();
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		if(sessionId!=0) {
			try {
				transactions = ConnectionDAO.transactionDetails(sessionId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		Gson gson = new Gson();
		message = gson.toJson(transactions);
//		response.getWriter().write("HTML code");
		PrintWriter out = response.getWriter();
		System.out.println(message);
		out.println(message);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
