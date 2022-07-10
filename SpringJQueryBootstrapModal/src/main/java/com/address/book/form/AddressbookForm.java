package com.address.book.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddressbookForm {			
	@NotNull
	private Long id;

	@NotEmpty
	private String firstname;
	
	@NotEmpty
	private String lastname;
	
	@NotNull
	private Long phonenumber;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String address;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Long getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(Long phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
