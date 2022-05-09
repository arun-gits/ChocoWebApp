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

import com.candyshop.dao.CandiesDAO;
import com.candyshop.model.Chocolates;
import com.google.gson.Gson;

/**
 * Servlet implementation class DisplayChocolates
 */
@WebServlet("/DisplayChocolates")
public class DisplayChocolates extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayChocolates() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Chocolates> list = new ArrayList<Chocolates>();
		CandiesDAO candies = new CandiesDAO();
		
		Gson gson = new Gson();
		String json = null;
		PrintWriter out = response.getWriter();
		try {
			list = candies.listAllChocolates();
			json = gson.toJson(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			json = gson.toJson(e.getMessage());
		}
//		for(Chocolates c:list) {
//			System.out.println(c.getId()+" "+c.getName()+" "+c.getPrice());
//		}
		out.println(json);
		out.flush();
		
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
