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

import com.candyshop.dao.ShoppingDAO;
import com.google.gson.JsonParser;



/**
 * Servlet implementation class BuyChocolates
 */
@WebServlet("/BuyChocolates")
public class BuyChocolatesServlet extends HttpServlet {
	List<Integer> list = new ArrayList();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyChocolatesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String id = request.getParameter("id");
		String mode = request.getParameter("mode");
		String sessionEmail = request.getParameter("sessionEmail");
		int sessionId = 0;
		String message = null;
		System.out.println(mode);
//		int price = 0;
//		int totalPrice = 0;
		PrintWriter out = response.getWriter();
		
//		ShoppingDAO shop = new ShoppingDAO();
		
		if(mode.equals("add")) {
		
		if(list.contains(null)) {
			List<Integer> list = new ArrayList<Integer>();
		}
		int chocoid = Integer.parseInt(id);
		list.add(chocoid);
		
		message = "added";
		out.println(message);
		out.flush();
		}
		
		if(mode.equals("buy")) {
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
//			for(Integer cid:list)
//			{
//				 try {
//					price=shop.totalPrice(cid);
//					totalPrice=totalPrice+price;
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					message = e.getMessage();
//				}
//				
//			}
			if(sessionId!=0) {
			for(Integer cid: list) {
				try {
					ConnectionDAO.transactionInserter(sessionId, cid, "COD");
					message = "Added Successfully";
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			}
			list.clear();
			out.println(message);
			out.flush();
		
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
