package se.rfjell.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@NotNull
	@NotEmpty
	private String username;
	
	private String password;
	private String role;
	private String firstName;
	private String lastName;
	private String telephone;
	private String address;
	private String gender;
	private String zipCode;
	
	public String getAddress() {
		return address;
	}
	public String getEmail() {
		return username;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getGender() {
		return gender;
	}
	public long getId() {
		return id;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPassword() {
		return password;
	}
	public String getRole() {
		return role;
	}
	public String getTelephone() {
		return telephone;
	}
	public String getUsername() {
		return username;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
}
