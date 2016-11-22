package se.rfjell.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CENTERS")
public class Center {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String email;
	private String address;
	private String zipCode;
	private String city;
	private String telephone;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="admin_id", referencedColumnName="id", foreignKey = @ForeignKey(name = "FK_CENTERS_USERS"))
	@JsonIgnore
	private User admin;
	@OneToMany(mappedBy="center", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<StudentTest> studentTests;
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getAddress() {
		return address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public String getCity() {
		return city;
	}
	public String getTelephone() {
		return telephone;
	}
	public User getAdmin() {
		return admin;
	}
	public List<StudentTest> getStudentTests() {
		return studentTests;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setAdmin(User admin) {
		this.admin = admin;
	}
	public void setStudentTests(List<StudentTest> studentTests) {
		this.studentTests = studentTests;
	}

	
}
