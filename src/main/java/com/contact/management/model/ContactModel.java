package com.contact.management.model;

public class ContactModel {
	
	private String contactId;
	private String name;
	private String email;
	private Long phone;
	private String ownerId;
	
	public ContactModel(String contactId, String name, String email, Long phone, String ownerId) {
		super();
		this.contactId = contactId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.ownerId = ownerId;
	}
	
	public ContactModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ContactModel [contactId=" + contactId + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", ownerId=" + ownerId + "]";
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
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

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
}
