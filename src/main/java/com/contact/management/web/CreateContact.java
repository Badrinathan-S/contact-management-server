package com.contact.management.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.contact.management.dao.ContactDao;
import com.contact.management.model.ContactModel;
import com.contact.management.util.GenerateUUID;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class CreateContact
 */
//@WebServlet("/CreateContact")
public class CreateContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContactDao contactDao;
	GenerateUUID uuid;
       
    public CreateContact() {
        super();
        
    }
    
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	contactDao = new ContactDao();
    	uuid = new GenerateUUID();
    	System.out.println("running contact dao");
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject(request.getReader().lines().collect(Collectors.joining()));
		String contactId = uuid.getUuid();
		String name = json.getString("name");
		String email = json.getString("email");
		Long phone = json.getLong("phone");
		String ownerId = json.getString("ownerId");
		ContactModel contact = new ContactModel(contactId, name, email, phone, ownerId);
		contactDao.insertContact(contact);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contact);
		out.write(jsonString);
		System.out.println("/Post");
	}

}
