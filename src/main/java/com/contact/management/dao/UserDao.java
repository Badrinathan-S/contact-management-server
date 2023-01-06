package com.contact.management.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.contact.management.model.UserModel;



public class UserDao {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/contact-management?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	private String jdbcDriver = "com.mysql.jdbc.Driver";

	private static final String INSERT_USER_SQL = "INSERT INTO users" + " (ownerId, name, email, phone, password) VALUE"
			+ " (?, ?, ?, ?, ?);";
	private static final String SELECT_USER_BY_CREDENTIAL = "SELECT ownerId, name, email, phone FROM users WHERE email=? AND password=?;";
	private static final String DELETE_USER_SQL = "DELETE FROM users WHERE ownerId=?;";
	private static final String UPDATE_USER_SQL = "UPDATE users set name=?, email=?, country=? where ownerId=?;";
	private static final String SELECT_USER_BY_OWNERID = "SELECT ownerId, name, email, country FROM users WHERE ownerId=?;";
	
	
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
	
	
	public void insertUser(UserModel user) {
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL)) {
			statement.setString(1, user.getOwnerId());
			statement.setString(2, user.getName());
			statement.setString(3, user.getEmail());
			statement.setLong(4, user.getPhone());
			statement.setString(5, user.getPassword());
			System.out.println(statement);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public UserModel getUser(UserModel user) {
		UserModel userDetails = null;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_CREDENTIAL)) {
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				String ownerId = rs.getString("ownerId");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Long phone = rs.getLong("phone");
				userDetails = new UserModel(ownerId, name, email, phone);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return userDetails;
		
	}
		

}
