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
@Table(name="TESTS")
public class Test {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private String description;
	@OneToMany(mappedBy="test", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<StudentTest> studentTests;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="provider_id", referencedColumnName="id", foreignKey=@ForeignKey(name = "FK_TESTS_PROVIDERS"))
	private Provider provider;

	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public List<StudentTest> getStudentTests() {
		return studentTests;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setStudentTests(List<StudentTest> studentTests) {
		this.studentTests = studentTests;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
}
