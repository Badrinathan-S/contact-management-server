package com.contact.management.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.contact.management.dao.ContactDao;
import com.contact.management.model.ContactModel;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class ListContact
 */
//@WebServlet("/ListContact")
public class ListContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContactDao contactDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListContact() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	contactDao = new ContactDao();
    	
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
//		doGet(request, response);
		PrintWriter out = response.getWriter();
		JSONObject json = new JSONObject(request.getReader().lines().collect(Collectors.joining()));
		String ownerId = json.getString("ownerId");
		ArrayList<ContactModel> contactList = (ArrayList<ContactModel>) contactDao.listContact(ownerId);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(contactList);
		out.write(jsonString);
	}

}
