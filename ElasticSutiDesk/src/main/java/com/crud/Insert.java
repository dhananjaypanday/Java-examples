package com.crud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/insert")
public class Insert extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String salary = request.getParameter("salary");
		String description = request.getParameter("description");
		ElasticCRUD elasticCRUD = new ElasticCRUD();
		System.out.println(id+" "+ name+" "+email+" "+phone+" "+salary+" "+ description);
		elasticCRUD.insertIndex(id, name, email, phone, salary, description);
		response.sendRedirect("index.jsp");
	}

}
