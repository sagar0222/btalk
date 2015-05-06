/**.
 * This pojo class contains Employee attributes.
 */
package com.brighttalk.rest.entity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sagsarka
 *
 */
@XmlRootElement
public class Employee {
	
	private int id;
	
	private String firstName;

	private String lastName;
	
	private String email;
	
	private String phone;
	
	/** Public default constructor **/
	public Employee() {

	}

	/** Public parameterized constructor **/
	public Employee( String firstName, String lastName, String email, String phone) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		
	}

	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
