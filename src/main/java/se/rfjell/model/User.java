package se.rfjell.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "USERS")
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
	private String confirmationLink;
	private char enabled;
	
	public String getAddress() {
		return address;
	}
	public String getConfirmationLink() {
		return confirmationLink;
	}
	public String getEmail() {
		return username;
	}
	public char getEnabled() {
		return enabled;
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
	public void setConfirmationLink() {
		String hash = hash();
		this.confirmationLink = hash;
	}
	public void setEnabled(char enabled) {
		this.enabled = enabled;
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
	private String hash(){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			String s = getUsername() + (new Date()).getTime();
			md.update(s.getBytes());
			byte b[] = md.digest();
	
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				sb.append( Integer.toString( (b[i] & 0xff) + 0x100, 16).substring(1) );
			}
			return sb.toString();
		} catch(NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
		}
		return getUsername() + (new Date()).getTime();
	}
	public void emptyConfirmationLink(){
		confirmationLink = "";
	}
}
