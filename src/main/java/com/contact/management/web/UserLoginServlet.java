package com.contact.management.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.contact.management.dao.UserDao;
import com.contact.management.model.MessageModel;
import com.contact.management.model.UserModel;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * Servlet implementation class UserLoginServlet
 */
//@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	userDao = new UserDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject(request.getReader().lines().collect(Collectors.joining()));
		String email = json.getString("email");
		String password = json.getString("password");
		UserModel user = new UserModel(null, null, email, null, password);
		UserModel userDetails = userDao.getUser(user);
		ObjectMapper mapper = new ObjectMapper();
		if(userDetails == null) {
			doOptions(request, response);
			MessageModel message = new MessageModel("Email or Password is wrong!!");
			response.setStatus(401);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			String jsonString = mapper.writeValueAsString(message);
			out.write(jsonString);
		}else {
			String jsonString = mapper.writeValueAsString(userDetails);
			out.write(jsonString);
		}
		doOptions(request, response);
	}
	
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");
		response.setHeader("Allow", "GET, HEAD, POST, TRACE, OPTIONS");
		response.setContentType("application/json");
	}

}

