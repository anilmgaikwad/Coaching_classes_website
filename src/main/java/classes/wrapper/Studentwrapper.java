package classes.wrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import classes.entity.Address;
import classes.entity.Course;
import classes.entity.Exam;
import classes.entity.Examresult;
import classes.entity.Feeinstallment;
import classes.entity.Feetable;
import classes.entity.Standard;
import classes.entity.User;
import classes.entity.Videoinfo;
import classes.validation.FieldMatch;

@FieldMatch.List({
	@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match") })

public class Studentwrapper {

	private int id;
	private String full_name;

	public String getFull_name() {
		full_name = first_name + " " + middle_name + " " + last_name;
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String first_name;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String middle_name;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String last_name;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String username;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String password;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String matchingPassword;
	
private List<Videoinfo> listOfVideos;
	
	public List<Videoinfo> getListOfVideos() {
		return listOfVideos;
	}


	public void setListOfVideos(List<Videoinfo> listOfVideos) {
		this.listOfVideos = listOfVideos;
	}
	
	public void addVideo(Videoinfo theVideoinfo) {
		if(null == this.listOfVideos)
			this.listOfVideos = new ArrayList<Videoinfo>();
		this.listOfVideos.add(theVideoinfo);
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

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	private String gender;
	private String roll_number;
	private String mobile_number1;
	private String mobile_number2;
	private String email;
	private List<String> courseNames;
	private List<String> standardBatches;
	private Date dateOfBirth;
	private Address address;
	private List<Course> courses;
	private List<Examresult> examresults;
	private Date date_of_day;
	 private float amount;
	 private User user;
	 private List<Exam> termExamsList;
	 public List<Exam> getTermExamsList() {
		 if(null == termExamsList)
			 termExamsList = new ArrayList<Exam>();
		return termExamsList;
	}

	public void setTermExamsList(List<Exam> termExamsList) {
		this.termExamsList = termExamsList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	Feeinstallmentwrapper theFeeinstallmentwrapper;

	public Feeinstallmentwrapper getTheFeeinstallmentwrapper() {
		if(null == this.theFeeinstallmentwrapper)
			this.theFeeinstallmentwrapper = new Feeinstallmentwrapper();
		this.theFeeinstallmentwrapper.setStudentId(id);
		return theFeeinstallmentwrapper;
	}

	public void setTheFeeinstallmentwrapper(Feeinstallmentwrapper theFeeinstallmentwrapper) {
		this.theFeeinstallmentwrapper = theFeeinstallmentwrapper;
	}

	public Date getDate_of_day() {
		return date_of_day;
	}

	public void setDate_of_day(Date date_of_day) {
		this.date_of_day = date_of_day;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	
	

	public void loadIntempFeeinstallment(int tempFeeInstallmentId){
		if (null != feetable) {
			for(Feeinstallment theFeeinstallment:feetable.getFeeinstallments())
			{
				if(theFeeinstallment.getId() == tempFeeInstallmentId)
				{
					this.theFeeinstallmentwrapper.setAmount(theFeeinstallment.getAmount());
					this.theFeeinstallmentwrapper.setDate_of_day(theFeeinstallment.getDate_of_day());
					this.theFeeinstallmentwrapper.setId(tempFeeInstallmentId);
					break;
				}
			}
		}
	}
	
	public void deleteFeeinstallment(int tempFeeInstallmentId){
		if (null != feetable) {
			for(Feeinstallment theFeeinstallment:feetable.getFeeinstallments())
			{
				if(theFeeinstallment.getId() == tempFeeInstallmentId)
				{
					feetable.deleteFromtempFeeInstallments(tempFeeInstallmentId);
					break;
				}
			}
		}
	}
	
	

	public List<String> getStandardBatches() {
		return standardBatches;
	}

	public void setStandardBatches(List<String> standardBatches) {
		this.standardBatches = standardBatches;
	}

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

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
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

	private Feetable feetable;

	public Feetable getFeetable() {
	/*	if(null == feetable)
			feetable = new Feetable();*/
		return feetable;
	}

	public void setFeetable(Feetable feetable) {
		this.feetable = feetable;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
		if (null == courseNames)
			courseNames = new ArrayList<String>();
		if (null == standardBatches)
			standardBatches = new ArrayList<String>();
		for (Course theCourse : this.courses) {
			String courseTitle = theCourse.getTitle();
			List<Standard> standards = theCourse.getStandards();
			String stNameWithBatch = "";
			for (Standard theStandard : standards) {
				stNameWithBatch = stNameWithBatch + theStandard.getName() + " " + theStandard.getBatch() + " ";
			}
			courseNames.add(courseTitle);
			standardBatches.add(stNameWithBatch);
		}
		UpdateFeetable();
	}

	public List<Examresult> getExamresults() {
		return examresults;
	}

	public void setExamresults(List<Examresult> examresults) {
		this.examresults = examresults;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<String> getCourseNames() {
		return courseNames;
	}

	public void setCourseNames(List<String> courseNames) {
		this.courseNames = courseNames;
	}

	private void UpdateFeetable() {
		if (null == feetable)
			feetable = new Feetable();
		if (null != courses) {
			float totalFee = 0.0f;
			for (Course theCourse : courses) {
				totalFee = totalFee + theCourse.getFee();
			}
			feetable.setTotalamount(totalFee);
			feetable.setRemaingamount(totalFee);
			feetable.setId(this.id);
		}
		if (null != feetable.getFeeinstallments()) {
			for (Feeinstallment theFeeinstallment : feetable.getFeeinstallments()) {
				feetable.setRemaingamount(feetable.getRemaingamount() - theFeeinstallment.getAmount());
			}
		}
	}

	public Studentwrapper() {
	}

}
