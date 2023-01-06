package com.contact.management.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.contact.management.model.ContactModel;
import com.contact.management.model.UserModel;

public class ContactDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/contact-management?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	private String jdbcDriver = "com.mysql.jdbc.Driver";

	private static final String INSERT_CONTACT_SQL = "INSERT INTO contact"
			+ "  (contactId, name, email, phone, ownerId) VALUE" + " (?, ?, ?, ?, ?);";
	private static final String LIST_CONTACT_SQL = "SELECT * FROM contact where ownerId = ?;";
	private static final String UPDATE_CONTACT_SQL = "UPDATE contact set name=?, email=?, phone=? where contactId=? AND ownerId=?;";
	private static final String SELECT_CONTACT_SQL = "SELECT * FROM contact WHERE contactId=? AND ownerId=?;";
	private static final String DELETE_CONTACT_SQL = "DELETE FROM contact WHERE contactId=? and ownerId=?;";

	protected Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}

		return connection;
	}

	public void insertContact(ContactModel contact) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_CONTACT_SQL)) {
			statement.setString(1, contact.getContactId());
			statement.setString(2, contact.getName());
			statement.setString(3, contact.getEmail());
			statement.setLong(4, contact.getPhone());
			statement.setString(5, contact.getOwnerId());
			statement.executeUpdate();
			System.out.println(contact.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ContactModel> listContact(String ownerId) {
		ArrayList<ContactModel> contactList = new ArrayList<ContactModel>();

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(LIST_CONTACT_SQL)) {

			statement.setString(1, ownerId);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				String contactId = rs.getString("contactId");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Long phone = rs.getLong("phone");
				String ownerId1 = rs.getString("ownerId");
				ContactModel contact = new ContactModel(contactId, name, email, phone, ownerId1);
				contactList.add(contact);
			}

		} catch (SQLException e) {

		}
		return contactList;
	}

	public ContactModel selectContact(String contactId, String ownerId) {
		ContactModel contact = null;

		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_CONTACT_SQL)) {
			statement.setString(1, contactId);
			statement.setString(2, ownerId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String contactId1 = rs.getString("contactId");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Long phone = rs.getLong("phone");
				String ownerId1 = rs.getString("ownerId");
				contact = new ContactModel(contactId1, name, email, phone, ownerId1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contact;
	}

	public boolean updateContact(ContactModel contact) {
		boolean isRowUpdated = false;
		try (Connection connetion = getConnection();
				PreparedStatement statement = connetion.prepareStatement(UPDATE_CONTACT_SQL)) {
			statement.setString(1, contact.getName());
			statement.setString(2, contact.getEmail());
			statement.setLong(3, contact.getPhone());
			statement.setString(4, contact.getContactId());
			statement.setString(5, contact.getOwnerId());
			isRowUpdated = statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isRowUpdated;
	}
	
	public boolean delectContact(String contactId, String ownerId) {
		boolean isRowUpdated = false;
		try(Connection connetion = getConnection();
				PreparedStatement statement = connetion.prepareStatement(DELETE_CONTACT_SQL)){
		
			statement.setString(1, contactId);
			statement.setString(2, ownerId);
			isRowUpdated = statement.executeUpdate() > 0;
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return isRowUpdated;
	}

}
