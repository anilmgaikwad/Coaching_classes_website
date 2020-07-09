package classes.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;



@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String first_name;
	
	@Column(name="gender")
	private String gender;
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column(name="middle_name")
	private String middle_name;
	
	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}


	@Column(name="last_name")
	private String last_name;
	
	@Column(name="roll_number")
	private String roll_number;
	
	@Column(name="mobile_number1")
	private String mobile_number1;
	
	@Column(name="mobile_number2")
	private String mobile_number2;
	
	@Column(name="email")
	private String email;
	
	@Column(name="date_of_birth")
    /*@Temporal(TemporalType.DATE)   */ 
    private Date dateOfBirth;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address address;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="feetable_id")
	private Feetable feetable;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	private User user;
	
	public Feetable getFeetable() {
		/*if(null == this.feetable)
			this.feetable = new Feetable();*/
		return this.feetable;
	}

	public void setFeetable(Feetable feetable) {
		this.feetable = feetable;
	}
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(fetch=FetchType.LAZY,cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(
			name="course_student",
			joinColumns=@JoinColumn(name="student_id"),
			inverseJoinColumns=@JoinColumn(name="course_id"))
	private List<Course> courses;

	
public Student(){}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFirst_name() {
	return first_name;
}

public void setFirst_name(String first_name) {
	this.first_name = first_name;
}

public String getLast_name() {
	return last_name;
}

public void setLast_name(String last_name) {
	this.last_name = last_name;
}



public String getRoll_number() {
	return roll_number;
}

public void setRoll_number(String roll_number) {
	this.roll_number = roll_number;
}

public String getMobile_number1() {
	return mobile_number1;
}

public void setMobile_number1(String mobile_number1) {
	this.mobile_number1 = mobile_number1;
}

public String getMobile_number2() {
	return mobile_number2;
}

public void setMobile_number2(String mobile_number2) {
	this.mobile_number2 = mobile_number2;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public Date getDateOfBirth() {
	return dateOfBirth;
}

public void setDateOfBirth(Date dateOfBirth) {
	this.dateOfBirth = dateOfBirth;
}

public Address getAddress() {
	return address;
}

public void setAddress(Address address) {
	this.address = address;
}

public List<Course> getCourses() {
	return courses;
}

public void setCourses(List<Course> courses) {
	this.courses = courses;
}

public Student(String first_name, String gender, String middle_name, String last_name, String roll_number,
		int mobile_number, String email, Date dateOfBirth, Address address, Feetable feetable, List<Course> courses,
		List<Examresult> examresults) {
	this.first_name = first_name;
	this.gender = gender;
	this.middle_name = middle_name;
	this.last_name = last_name;
	this.roll_number = roll_number;
	this.mobile_number1 = mobile_number1;
	this.email = email;
	this.dateOfBirth = dateOfBirth;
	this.address = address;
	this.feetable = feetable;
	this.courses = courses;
}

@Override
public String toString() {
	return "Student [id=" + id + ", first_name=" + first_name + ", gender=" + gender + ", middle_name=" + middle_name
			+ ", last_name=" + last_name + ", roll_number=" + roll_number + ", mobile_number 1=" + mobile_number1
			+ ", email=" + email + ", dateOfBirth=" + dateOfBirth + ", address=" + address + ", feetable=" + feetable
			+ ", courses=" + courses + "]";
}




}
