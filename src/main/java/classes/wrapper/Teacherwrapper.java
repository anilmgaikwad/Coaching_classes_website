package classes.wrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import classes.entity.Address;
import classes.entity.Course;
import classes.entity.Examresult;
import classes.entity.Standard;
import classes.entity.Subject;
import classes.entity.User;
import classes.entity.Videoinfo;
import classes.user.CrmUser;
import classes.validation.FieldMatch;

@FieldMatch.List({
		@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match") })

public class Teacherwrapper {
	private int id;
	private String full_name;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String first_name;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String middle_name;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String last_name;

	private String gender;
	private String id_number;
	private String mobile_number1;
	private String mobile_number2;
	private String email;
	private Date dateOfBirth;
	private Address address;
	private Standard standard;
	private List<Subject> subjects;
	private List<String> subjectNames;
	private String standardName;
	private User user;
	private String qualification;
	private String adminrole;
	private ExamDetailswrapper examDetails;
	private List<ExamDetailswrapper> listOfExamDetails;
	private List<Subjectwrapper> subjectwrappers;
	private List<Videoinfo> listOfVideos;
	private String subjectName_OfVideo;
	private String video_Name;
	private int video_id;
	private boolean bVideoViewStatus;

	public boolean isbVideoViewStatus() {
		return bVideoViewStatus;
	}

	public void setbVideoViewStatus(boolean bVideoViewStatus) {
		this.bVideoViewStatus = bVideoViewStatus;
	}

	public int getVideo_id() {
		return video_id;
	}

	public void setVideo_id(int video_id) {
		this.video_id = video_id;
	}

	public String getVideo_Name() {
		return video_Name;
	}

	public void setVideo_Name(String video_Name) {
		this.video_Name = video_Name;
	}

	public String getSubjectName_OfVideo() {
		return subjectName_OfVideo;
	}

	public void setSubjectName_OfVideo(String subjectName_OfVideo) {
		this.subjectName_OfVideo = subjectName_OfVideo;
	}

	public List<Videoinfo> getListOfVideos() {
		return listOfVideos;
	}

	public void setListOfVideos(List<Videoinfo> listOfVideos) {
		this.listOfVideos = listOfVideos;
	}

	public List<Subjectwrapper> getSubjectwrappers() {

		return subjectwrappers;
	}

	public void setSubjectwrappers(List<Subjectwrapper> subjectwrappers) {
		this.subjectwrappers = subjectwrappers;
	}

	public List<ExamDetailswrapper> getListOfExamDetails() {
		if (null == listOfExamDetails)
			listOfExamDetails = new ArrayList<ExamDetailswrapper>();
		return listOfExamDetails;
	}

	public void setListOfExamDetails(List<ExamDetailswrapper> listOfExamDetails) {
		this.listOfExamDetails = listOfExamDetails;
	}

	public ExamDetailswrapper getExamDetails() {
		if (null == examDetails)
			examDetails = new ExamDetailswrapper();
		return examDetails;
	}

	public void setExamDetails(ExamDetailswrapper examDetails) {
		this.examDetails = examDetails;
	}

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String username;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String password;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String matchingPassword;

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
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

	public String getAdminrole() {
		return adminrole;
	}

	public void setAdminrole(String adminrole) {
		this.adminrole = adminrole;
	}

	private CrmUser crmUser;

	public CrmUser getCrmUser() {
		return crmUser;
	}

	public void setCrmUser(CrmUser crmUser) {
		this.crmUser = crmUser;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getId_number() {
		return id_number;
	}

	public void setId_number(String id_number) {
		this.id_number = id_number;
	}

	public Standard getStandard() {
		return standard;
	}

	public void setStandard(Standard standard) {
		if (null != standard) {
			this.standard = standard;
			standardName = standard.getName();
		}
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
		subjectNames = new ArrayList<String>();
		for (Subject theSubject : subjects) {
			String subjectName = theSubject.getName();
			subjectNames.add(subjectName);
		}
		subjectwrappers = new ArrayList<Subjectwrapper>();
		for (Subject theSubject : subjects) {
			Subjectwrapper theSubjectwrapper = new Subjectwrapper(theSubject);
			subjectwrappers.add(theSubjectwrapper);
		}
	}

	public List<String> getSubjectNames() {
		return subjectNames;
	}

	public void setSubjectNames(List<String> subjectNames) {
		this.subjectNames = subjectNames;
	}

	public String getStandardName() {
		return standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
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

	public void loadUser() {
		this.user.setUsername(this.crmUser.getUserName());
		this.user.setPassword(this.crmUser.getPassword());
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFull_name() {
		full_name = first_name + " " + middle_name + " " + last_name;
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public Teacherwrapper() {
		if (null == examDetails)
			examDetails = new ExamDetailswrapper();
	}

}
