package classes.wrapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import classes.entity.Course;
import classes.entity.Presenty;
import classes.entity.Presentyrecord;
import classes.entity.Student;
import classes.entity.Subject;

public class Presentyrecordwrapper {
	private int id;
	private Date date_of_day;

	private List<String> presentStudentList;
	private List<Boolean> studentPresentyStatus;

	// private String dateInStringFormat;

	private Map<String, Student> studentMap;
	private List<Presenty> presenties;
	private int subjectId;
	private String subjectName;

	private List<Presentywrapper> presentywrappers;
	public List<Presentywrapper> getPresentywrappers() {
		return presentywrappers;
	}

	public void setPresentywrappers(List<Presentywrapper> presentywrappers) {
		this.presentywrappers = presentywrappers;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public List<Presenty> getPresenties() {
		return presenties;
	}

	public void setPresenties(List<Presenty> presenties) {
		this.presenties = presenties;
	}

	public void addPresenty(Presenty thePresenty) {
		if (null == presenties)
			presenties = new ArrayList<Presenty>();
		presenties.add(thePresenty);
	}

	public Presentyrecordwrapper(Subject theSubject) {
		buildOtherDataWithSubject(theSubject, true);
	}

	public Presentyrecord getThePresentyrecord() {
		Presentyrecord thePresentyrecord = new Presentyrecord();
		thePresentyrecord.setId(this.id);
		thePresentyrecord.setDate_of_day(this.date_of_day);
		thePresentyrecord.setPresenties(this.presenties);
		return thePresentyrecord;
	}

	public Presentyrecordwrapper() {
	}

	public Presentyrecordwrapper(Presentyrecord thePresentyrecord) {

		// this.thePresentyrecord.setSubject(thePresentyrecord.getSubject());
		this.presenties = thePresentyrecord.getPresenties();
		// this.thePresentyrecord = thePresentyrecord;
		this.id = thePresentyrecord.getId();
		this.date_of_day = thePresentyrecord.getDate_of_day();
		// setSubject(thePresentyrecord.getSubject());
		// this.subject = thePresentyrecord.getSubject();

	}

	public void buildOtherDataWithSubject(Subject theSubject, boolean bCreatePresenties) {
		studentNames = new ArrayList<String>();
		presentStudentList = new ArrayList<String>();
		studentMap = new LinkedHashMap<String, Student>();
		studentPresentyStatus = new ArrayList<Boolean>();
		List<Presentywrapper> presentywrappers = new ArrayList<Presentywrapper>();
		this.subjectId = theSubject.getId();
		this.subjectName = theSubject.getName();

		List<Course> courses = theSubject.getCourses();
		for (Course theCourse : courses) {
			Set<Student> students = theCourse.getStudents();
			for (Student student : students) {
				String studentName = student.getFirst_name() + " " + student.getMiddle_name() + " "
						+ student.getLast_name();
				studentNames.add(studentName);
				Presentywrapper thePresentywrapper = new Presentywrapper();
				thePresentywrapper.setStudentName(studentName);
				
				
				studentMap.put(studentName, student);
				if (bCreatePresenties) {
					Presenty thePresenty = new Presenty();
					thePresenty.setStatus(false);
					thePresenty.setStudent_id(student.getId());
					addPresenty(thePresenty);
					thePresentywrapper.setStatus(false);
				} else {
					for (Presenty thePresenty : presenties) {
					//	if (true == thePresenty.isStatus()) {
							if (student.getId() == thePresenty.getStudent_id()) {
								presentStudentList.add(studentName);
								thePresentywrapper.setStatus(thePresenty.isStatus());
								break;
							}
					//	}
					}
				}
				presentywrappers.add(thePresentywrapper);
			}
		}

	}

	public void buildOtherDataWithSubjectwrapper(Subjectwrapper theSubject, boolean bCreatePresenties) {
		studentNames = new ArrayList<String>();
		presentStudentList = new ArrayList<String>();
		studentMap = new LinkedHashMap<String, Student>();
		studentPresentyStatus = new ArrayList<Boolean>();
		this.subjectName = theSubject.getName();
		this.subjectId = theSubject.getId();
		List<Presentywrapper> presentywrappers = new ArrayList<Presentywrapper>();

		List<Course> courses = theSubject.getCourses();
		for (Course theCourse : courses) {
			Set<Student> students = theCourse.getStudents();
			for (Student student : students) {
				String studentName = student.getFirst_name() + " " + student.getMiddle_name() + " "
						+ student.getLast_name();
				studentNames.add(studentName);
				Presentywrapper thePresentywrapper = new Presentywrapper();
				thePresentywrapper.setStudentName(studentName);
				studentMap.put(studentName, student);
				if (bCreatePresenties) {
					Presenty thePresenty = new Presenty();
					thePresenty.setStatus(false);
					thePresenty.setStudent_id(student.getId());
					addPresenty(thePresenty);
					thePresentywrapper.setStatus(false);
				}else {
					for (Presenty thePresenty : presenties) {
						//if (true == thePresenty.isStatus()) {
							if (student.getId() == thePresenty.getStudent_id()) {
								presentStudentList.add(studentName);
								thePresentywrapper.setStatus(thePresenty.isStatus());
								break;
							}
						//}
					}
				}
				presentywrappers.add(thePresentywrapper);
			}
		}

	}

	public void setPresentiesOfStudent(String[] presentStudentList) {
		// if(null == thePresentyrecord.getPresenties())

		for (String presentStudent : presentStudentList) {
			Student theStudent = studentMap.get(presentStudent);
			for (Presenty thePresenty : presenties) {
				if (thePresenty.getStudent_id() == theStudent.getId()) {
					thePresenty.setStatus(true);
					break;
				}
			}
		}

	}

	public List<Boolean> getStudentPresentyStatus() {
		return studentPresentyStatus;
	}

	public void setStudentPresentyStatus(List<Boolean> studentPresentyStatus) {
		this.studentPresentyStatus = studentPresentyStatus;
	}

	public List<String> getPresentStudentList() {
		return presentStudentList;
	}

	public void setPresentStudentList(List<String> presentStudentList) {
		this.presentStudentList = presentStudentList;
	}

	public Map<String, Student> getStudentMap() {
		return studentMap;
	}

	public void setStudentMap(Map<String, Student> studentMap) {
		this.studentMap = studentMap;
	}

	private List<String> studentNames;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate_of_day() {
		return date_of_day;
	}

	public void setDate_of_day(Date date_of_day) {
		if (null != date_of_day) {
			this.date_of_day = date_of_day;
		}
	}

	public List<String> getStudentNames() {
		return studentNames;
	}

	public void setStudentNames(List<String> studentNames) {
		this.studentNames = studentNames;
	}

}
