package com.contact.management.web;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.contact.management.dao.UserDao;
import com.contact.management.model.UserModel;
import com.contact.management.util.GenerateUUID;

/**
 * Servlet implementation class UserRegisterServlet
 */
//@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDao;
	GenerateUUID uuid;
       
    public UserRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	userDao = new UserDao();
    	uuid = new GenerateUUID();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json = new JSONObject(request.getReader().lines().collect(Collectors.joining()));
		String ownerId = uuid.getUuid();
		String name = json.getString("name");
		String email = json.getString("email");
		Long phone = json.getLong("phone");
		String password = json.getString("password");
		UserModel user = new UserModel(ownerId, name, email, phone, password);
		userDao.insertUser(user);
	}

}
