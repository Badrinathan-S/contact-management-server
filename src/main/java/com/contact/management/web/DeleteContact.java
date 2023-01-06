package com.contact.management.web;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.contact.management.dao.ContactDao;

/**
 * Servlet implementation class DeleteContact
 */
//@WebServlet("/DeleteContact")
public class DeleteContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ContactDao contactDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteContact() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	contactDao = new ContactDao();
    }
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(request, response);
		JSONObject json = new JSONObject(request.getReader().lines().collect(Collectors.joining()));
		String contactId = json.getString("contactId");
		String ownerId = json.getString("ownerId");
		contactDao.delectContact(contactId, ownerId);
	}

}
