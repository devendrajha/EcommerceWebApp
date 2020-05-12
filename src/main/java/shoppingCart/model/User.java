package shoppingCart.model;

import java.io.Serializable;

/**
 * JavaBean class used in jsp action tags.
 
 */
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String firstname;
	private String lastname;
	private String email;
	private String mobilenumber;
	private String username;
	private String password;
	public String getFirstName() {
		return firstname;
	}
	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}
	public String getLastName() {
		return lastname;
	}
	public void setLastName(String lastName) {
		this.lastname = lastName;
	}
	
	public String getemail() {
		return email;
	}
	public void setemail(String email) {
		this.email = email;
	}
	
	public String getMobileNumber() {
		return mobilenumber;
	}
	public void setMobileNumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
