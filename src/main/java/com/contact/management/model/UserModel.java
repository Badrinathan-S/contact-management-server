package com.contact.management.model;

public class UserModel {
	
	private String ownerId;
	private String name;
	private String email;
	private Long phone;
	private String password;
	
	public UserModel(String ownerId, String name, String email, Long phone, String password) {
		super();
		this.ownerId = ownerId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}
	
	
	

	public UserModel(String ownerId, String name, String email, Long phone) {
		super();
		this.ownerId = ownerId;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "UserModel [ownerId=" + ownerId + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", password=" + password + "]";
	}


	public UserModel() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
