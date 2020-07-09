package classes.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import classes.entity.Course;
import classes.entity.Exam;
import classes.entity.Examdetails;
import classes.entity.Presentyrecord;
import classes.entity.Standard;
import classes.entity.Subject;
import classes.entity.Teacher;
import classes.entity.Videoinfo;
import classes.service.ExamService;

public class Subjectwrapper {

	private int id;
	private String name;
	private String syllabus_term1;
	private String syllabus_term2;
	private String syllabus_term3;
	private String syllabus_term4;
	private List<ExamDetailswrapper> examdetailswrapperlist;
	private List<Videoinfo> listOfVideos;


	public List<Videoinfo> getListOfVideos() {
		if(null == this.listOfVideos)
			this.listOfVideos = new ArrayList<Videoinfo>();
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

	public List<ExamDetailswrapper> getExamdetailswrapperlist() {
		return examdetailswrapperlist;
	}

	public void setExamdetailswrapperlist(List<ExamDetailswrapper> examdetailswrapperlist) {
		this.examdetailswrapperlist = examdetailswrapperlist;
	}
	
	public void setExamdetailslist(List<Examdetails> examdetailslist) {
		examdetailswrapperlist = new ArrayList<ExamDetailswrapper>();
		for(Examdetails theExamdetails:examdetailslist)
		{
			ExamDetailswrapper theExamDetailswrapper = new ExamDetailswrapper();
			theExamDetailswrapper.setId(theExamdetails.getId());
			theExamDetailswrapper.setExam_id(theExamdetails.getExam_id());
			theExamDetailswrapper.setSubjectName(this.name);
			theExamDetailswrapper.setTotalmarks(theExamdetails.getTotalmarks());
			examdetailswrapperlist.add(theExamDetailswrapper);
		}
		
	}
	


	private List<Presentyrecordwrapper> presentyrecordwrappers;

	public List<Presentyrecordwrapper> getPresentyrecordwrappers() {
		return presentyrecordwrappers;
	}

	public void setPresentyrecordwrappers(List<Presentyrecordwrapper> presentyrecordwrappers) {
		this.presentyrecordwrappers = presentyrecordwrappers;
	}
	public void deletePresentyrecordwrapper(int theId){
		if(null != this.presentyrecordwrappers){
			for(Presentyrecordwrapper thePresentyrecordwrapper :this.presentyrecordwrappers){
				if(thePresentyrecordwrapper.getId() == theId)
				{
					presentyrecordwrappers.remove(thePresentyrecordwrapper);
					break;
				}
			}
		}
	}

	private List<Course> courses;
	private List<Teacher> teachers;
	private List<String> courseNames;
	private List<String> teacherNames;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSyllabus_term1() {
		return syllabus_term1;
	}

	public void setSyllabus_term1(String syllabus_term1) {
		this.syllabus_term1 = syllabus_term1;
	}

	public String getSyllabus_term2() {
		return syllabus_term2;
	}

	public void setSyllabus_term2(String syllabus_term2) {
		this.syllabus_term2 = syllabus_term2;
	}

	public String getSyllabus_term3() {
		return syllabus_term3;
	}

	public void setSyllabus_term3(String syllabus_term3) {
		this.syllabus_term3 = syllabus_term3;
	}

	public String getSyllabus_term4() {
		return syllabus_term4;
	}

	public void setSyllabus_term4(String syllabus_term4) {
		this.syllabus_term4 = syllabus_term4;
	}

	public List<Presentyrecord> getPresentyrecords() {
		List<Presentyrecord> presentyrecords = new ArrayList<Presentyrecord>();
		if(null != this.presentyrecordwrappers){
		
		for(Presentyrecordwrapper thePresentyRecordwrapper:this.presentyrecordwrappers)
		{
			presentyrecords.add(thePresentyRecordwrapper.getThePresentyrecord());
		}
		
		}
		return presentyrecords;
	}

	public void setPresentyrecords(List<Presentyrecord> presentyrecords) {
		//this.presentyrecords = presentyrecords;
		this.presentyrecordwrappers = new ArrayList<Presentyrecordwrapper>();
		for (Presentyrecord thePresentyRecord : presentyrecords) {
			Presentyrecordwrapper thePresentyRecordwrapper = new Presentyrecordwrapper(thePresentyRecord);

			thePresentyRecordwrapper.buildOtherDataWithSubjectwrapper(this, false);
			presentyrecordwrappers.add(thePresentyRecordwrapper);
		}
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
		courseNames = new ArrayList<String>();
		for (Course theCourse : courses) {
			String courseName = theCourse.getTitle();
			courseNames.add(courseName);
			/*List<Standard> standards = theCourse.getStandards();
			for (Standard theStandard : standards) {
				courseName = courseName + " " + theStandard.getName() + " " + theStandard.getBatch();
				courseNames.add(courseName);
				break;
			}*/

		}
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
		teacherNames = new ArrayList<String>();
		for (Teacher theTeacher : teachers) {
			teacherNames.add(theTeacher.getFirst_name() + " " + theTeacher.getMiddle_name() + " "
					+ theTeacher.getLast_name() + "(" + theTeacher.getId_number() + ")");
		}
	}

	public List<String> getCourseNames() {
		if (null == courseNames)
			courseNames = new ArrayList<String>();
		return courseNames;
	}

	public void setCourseNames(List<String> courseNames) {
		this.courseNames = courseNames;
	}

	public List<String> getTeacherNames() {
		if (null == teacherNames)
			teacherNames = new ArrayList<String>();
		return teacherNames;
	}

	public void setTeacherNames(List<String> teacherNames) {
		teacherNames = teacherNames;
	}
	
	public Subjectwrapper(){}
	
	public Subjectwrapper(Subject theSubject){
		  id = theSubject.getId();
		  name = theSubject.getName();
		  syllabus_term1 = theSubject.getSyllabus_term1();
		  syllabus_term2 = theSubject.getSyllabus_term2();
		  syllabus_term3 = theSubject.getSyllabus_term3();
		  syllabus_term4 = theSubject.getSyllabus_term4();
		  setCourses(theSubject.getCourses());
		  setPresentyrecords(theSubject.getPresentyrecords());
		  setTeachers(theSubject.getTeachers());
		  setExamdetailslist(theSubject.getExamdetails());
		  setListOfVideos(theSubject.getListOfVideos());
	}

}
