package se.rfjell.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "STUDENT_TESTS")
public class StudentTest {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String payed;
	private Date bookingDate;
	private Date testDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="center_id", referencedColumnName="id", foreignKey=@ForeignKey(name = "FK_CENTERS_STUDENT_TESTS"))
	private Center center;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="tester_id", referencedColumnName="id", foreignKey=@ForeignKey(name = "FK_USERS_STUDENT_TESTS"))
	private User tester;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name="test_id", referencedColumnName="id", foreignKey=@ForeignKey(name = "FK_TESTS_STUDENT_TESTS"))
	private Test test;

	public Date getBookingDate() {
		return bookingDate;
	}

	public Center getCenter() {
		return center;
	}

	public long getId() {
		return id;
	}

	public String getPayed() {
		return payed;
	}

	public Test getTest() {
		return test;
	}

	public Date getTestDate() {
		return testDate;
	}

	public User getTester() {
		return tester;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public void setCenter(Center center) {
		this.center = center;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setPayed(String payed) {
		this.payed = payed;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public void setTester(User tester) {
		this.tester = tester;
	}
}
