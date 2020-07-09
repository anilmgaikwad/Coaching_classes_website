package classes.wrapper;

import java.util.ArrayList;
import java.util.List;

import classes.entity.Course;
import classes.entity.Standard;
import classes.entity.Student;
import classes.service.StandardService;
import classes.service.TeacherwrapperService;


public class MessageBox {
	private String receipents_str;
	private String message;
	private List<Studentwrapper> listOfStudents;
	public String getReceipents_str() {
		return receipents_str;
	}

	public void setReceipents_str(String receipents_str) {
		this.receipents_str = receipents_str;
	}

	private List<Teacherwrapper> listOfTeachers;

	

	public List<Studentwrapper> getListOfStudents() {
		return listOfStudents;
	}

	public void setListOfStudents(List<Studentwrapper> listOfStudents) {
		this.listOfStudents = listOfStudents;
	}

	public List<Teacherwrapper> getListOfTeachers() {
		return listOfTeachers;
	}

	public void setListOfTeachers(List<Teacherwrapper> listOfTeachers) {
		this.listOfTeachers = listOfTeachers;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	public void addTeacherReceipent(Teacherwrapper receipent) {
		if (null == this.listOfTeachers)
			this.listOfTeachers = new ArrayList<Teacherwrapper>();
		this.listOfTeachers.add(receipent);
	}

	public void addStudentReceipent(Studentwrapper receipent) {
		if (null == this.listOfStudents)
			this.listOfStudents = new ArrayList<Studentwrapper>();
		this.listOfStudents.add(receipent);
	}

	public void parseReceipentsStr(StandardService standardService, TeacherwrapperService teacherService) {
		if ((null != standardService) && (null != teacherService)) {
			String[] strArray = receipents_str.split(",");
			for (int i = 0; i < strArray.length; i++) {
				String str = strArray[i];
				CharSequence cs1 = "th";
				CharSequence cs2 = "T";
				if (str.contains(cs1)) {
					List<Standard> standards = standardService.getStandards();
					for (Standard theStandard : standards) {
						if (str.equals(theStandard.getName())) 
						{
							List<Course> courses = theStandard.getCourses();
							List<Student> allStudents = new ArrayList<Student>();
							for (Course theCourse : courses) {
								allStudents.addAll(theCourse.getStudents());
							}
							for (Student theStudent : allStudents) {
								Studentwrapper theStudentWrapper = new Studentwrapper();
								theStudentWrapper.setId(theStudent.getId());
								theStudentWrapper.setFirst_name(theStudent.getFirst_name());
								theStudentWrapper.setMiddle_name(theStudent.getMiddle_name());
								theStudentWrapper.setLast_name(theStudent.getLast_name());

								theStudentWrapper.setGender(theStudent.getGender());
								theStudentWrapper.setRoll_number(theStudent.getRoll_number());
								theStudentWrapper.setMobile_number1(theStudent.getMobile_number1());
								theStudentWrapper.setMobile_number2(theStudent.getMobile_number2());
								theStudentWrapper.setEmail(theStudent.getEmail());
								theStudentWrapper.setDateOfBirth(theStudent.getDateOfBirth());
								theStudentWrapper.setAddress(theStudent.getAddress());
								theStudentWrapper.setFeetable(theStudent.getFeetable());
								theStudentWrapper.setCourses(theStudent.getCourses());
								theStudentWrapper.setUser(theStudent.getUser());
								addStudentReceipent(theStudentWrapper);
							}
							break;
						}
					}
				} else if (str.contains(cs2)) {
					List<Teacherwrapper> teachers = teacherService.getTeacherwrappers();
					for(Teacherwrapper theTeacherwrapper:teachers){
						if(str.equals(theTeacherwrapper.getId_number())){
							addTeacherReceipent(theTeacherwrapper);
							break;
						}
					}
					
				} else {
					char first = str.charAt(0); 
					CharSequence cs3 =Character.toString(first);
					List<Standard> standards = standardService.getStandards();
					for (Standard theStandard : standards) {
						if(theStandard.getName().contains(cs3))
						{
							List<Course> courses = theStandard.getCourses();
							List<Student> allStudents = new ArrayList<Student>();
							for (Course theCourse : courses) {
								allStudents.addAll(theCourse.getStudents());
							}
							for (Student theStudent : allStudents) {
								if(str.equals(theStudent.getRoll_number())){
									Studentwrapper theStudentWrapper = new Studentwrapper();
									theStudentWrapper.setId(theStudent.getId());
									theStudentWrapper.setFirst_name(theStudent.getFirst_name());
									theStudentWrapper.setMiddle_name(theStudent.getMiddle_name());
									theStudentWrapper.setLast_name(theStudent.getLast_name());

									theStudentWrapper.setGender(theStudent.getGender());
									theStudentWrapper.setRoll_number(theStudent.getRoll_number());
									theStudentWrapper.setMobile_number1(theStudent.getMobile_number1());
									theStudentWrapper.setMobile_number2(theStudent.getMobile_number2());
									theStudentWrapper.setEmail(theStudent.getEmail());
									theStudentWrapper.setDateOfBirth(theStudent.getDateOfBirth());
									theStudentWrapper.setAddress(theStudent.getAddress());
									theStudentWrapper.setFeetable(theStudent.getFeetable());
									theStudentWrapper.setCourses(theStudent.getCourses());
									theStudentWrapper.setUser(theStudent.getUser());
									addStudentReceipent(theStudentWrapper);
									break;
								}
							}
							break;
						}
					}
				}

			}
		}
	}
}
