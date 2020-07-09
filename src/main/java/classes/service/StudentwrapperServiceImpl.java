package classes.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import classes.dao.RoleDao;

import classes.dao.StudentDAO;
import classes.dao.UserDao;
import classes.entity.Course;
import classes.entity.Examdetails;
import classes.entity.Examresult;
import classes.entity.Feetable;
import classes.entity.Student;
import classes.entity.Subject;
import classes.entity.User;
import classes.wrapper.Studentwrapper;

@Service
public class StudentwrapperServiceImpl implements StudentwrapperService {

	@Autowired
	private StudentDAO studentDAO;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private UserDao userDao;

	/*
	 * @Autowired private BCryptPasswordEncoder passwordEncoder;
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}

	@Override
	@Transactional
	public List<Studentwrapper> getStudents() {
		// TODO Auto-generated method stub
		List<Studentwrapper> studentwrappers = new ArrayList<Studentwrapper>();
		List<Student> students = studentDAO.getStudents();
		for (Student theStudent : students) {
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
			User theUser = new User();
			theUser.setId(theStudent.getUser().getId());
			theUser.setUsername(theStudent.getUser().getUsername());
			theUser.setRoles(theStudent.getUser().getRoles());
			theUser.setPassword(theStudent.getUser().getPassword());
			theStudentWrapper.setUser(theUser);

			/*
			 * if(null != theCourse.getStandard())
			 * theCourseWrapper.setStandardName(theCourse.getStandard().getName(
			 * ) + " "+ theCourse.getStandard().getDivision());
			 */
			studentwrappers.add(theStudentWrapper);
		}
		return studentwrappers;
	}

	@Override
	@Transactional
	public void saveStudent(Studentwrapper theStudentWrapper) {
		// TODO Auto-generated method stub
		int studentToModifyId = theStudentWrapper.getId();
		List<Student> students = studentDAO.getStudents();
		Student foundStudent = null;
		boolean bNewStudent = true;
		for (Student theStudent : students) {
			int studentId = theStudent.getId();
			if (studentToModifyId == studentId) {
				foundStudent = theStudent;
				bNewStudent = false;
				break;
			}
		}
		if (null == foundStudent) {
			foundStudent = new Student();
			foundStudent.setId(theStudentWrapper.getId());
		}

		foundStudent.setId(theStudentWrapper.getId());
		foundStudent.setFirst_name(theStudentWrapper.getFirst_name());
		foundStudent.setMiddle_name(theStudentWrapper.getMiddle_name());
		foundStudent.setLast_name(theStudentWrapper.getLast_name());

		foundStudent.setGender(theStudentWrapper.getGender());
		foundStudent.setRoll_number(theStudentWrapper.getRoll_number());
		foundStudent.setMobile_number1(theStudentWrapper.getMobile_number1());
		foundStudent.setMobile_number2(theStudentWrapper.getMobile_number2());

		foundStudent.setEmail(theStudentWrapper.getEmail());
		foundStudent.setDateOfBirth(theStudentWrapper.getDateOfBirth());
		foundStudent.setAddress(theStudentWrapper.getAddress());

		foundStudent.setCourses(theStudentWrapper.getCourses());
		User theUser = foundStudent.getUser();
		if (null == theUser) {
			theUser = new User();
			theUser.addRole(roleDao.findRoleByName("ROLE_STUDENT"));
			theUser.addRole(roleDao.findRoleByName("ROLE_COMMON"));
		
		}
		theUser.setUsername(theStudentWrapper.getUsername());
		theUser.setPassword(theStudentWrapper.getPassword());
		theUser.setPassword(passwordEncoder().encode(theUser.getPassword()));
		userDao.save(theUser);

		foundStudent.setUser(theUser);
		Feetable theFeetable = foundStudent.getFeetable();
		if (null == theFeetable)
			foundStudent.setFeetable(theStudentWrapper.getFeetable());

		studentDAO.saveStudent(foundStudent);

		students = studentDAO.searchStudents(theStudentWrapper.getRoll_number());

		Student savedStudent = null;
		if (null != students)
			savedStudent = students.get(0);
		if ((true == bNewStudent) && (null != savedStudent)) {
			List<Course> theCourses = theStudentWrapper.getCourses();
			List<Subject> subjects = new ArrayList<Subject>();
			List<Course> theCoursesFrmDB = new ArrayList<Course>();
			for (Course theCourse : theCourses)
				theCoursesFrmDB.add(courseService.getCourse(theCourse.getId()));
			for (Course theCourse : theCoursesFrmDB) {
				subjects.addAll(theCourse.getSubjects());
			}
			// List<Examdetails> examDetailsList = new ArrayList<Examdetails>();
			for (Subject theSubject : subjects) {
				List<Examdetails> examDetailsList = theSubject.getExamdetails();
				if (null != examDetailsList) {
					for (Examdetails theExamdetails : examDetailsList) {
						Examresult theExamResult = new Examresult();
						theExamResult.setStudent_id(savedStudent.getId());
						theExamResult.setMarksobtained(0);
						theExamResult.setStatus(false);
						theExamdetails.addExamresult(theExamResult);
						// theSubject.getExamdetails().add(theExamdetails);
					}
					subjectService.saveSubject(theSubject);
				}
			}
		}
	}

	@Override
	@Transactional
	public Studentwrapper getStudent(int theId) {
		// TODO Auto-generated method stub
		Student theStudent = studentDAO.getStudent(theId);
		Studentwrapper theStudentWrapper = new Studentwrapper();
		theStudentWrapper.setId(theStudent.getId());
		theStudentWrapper.setFirst_name(theStudent.getFirst_name());
		theStudentWrapper.setMiddle_name(theStudent.getMiddle_name());
		theStudentWrapper.setLast_name(theStudent.getLast_name());
		String full_name = theStudent.getFirst_name() + " " + theStudent.getMiddle_name() + " "
				+ theStudent.getLast_name();
		theStudentWrapper.setFull_name(full_name);
		theStudentWrapper.setGender(theStudent.getGender());
		theStudentWrapper.setRoll_number(theStudent.getRoll_number());
		theStudentWrapper.setMobile_number1(theStudent.getMobile_number1());
		theStudentWrapper.setMobile_number2(theStudent.getMobile_number2());

		theStudentWrapper.setEmail(theStudent.getEmail());
		theStudentWrapper.setDateOfBirth(theStudent.getDateOfBirth());
		theStudentWrapper.setAddress(theStudent.getAddress());
		theStudentWrapper.setFeetable(theStudent.getFeetable());
		theStudentWrapper.setCourses(theStudent.getCourses());
		User theUser = new User();
		theUser.setId(theStudent.getUser().getId());
		theUser.setUsername(theStudent.getUser().getUsername());
		theUser.setRoles(theStudent.getUser().getRoles());
		theUser.setPassword(theStudent.getUser().getPassword());
		theStudentWrapper.setUser(theUser);

		return theStudentWrapper;
	}

	@Override
	@Transactional
	public void deleteStudent(int theId) {
		// TODO Auto-generated method stub
		Student theStudent = studentDAO.getStudent(theId);
		User theUser = theStudent.getUser();
		studentDAO.deleteStudent(theId);
		userDao.deleteUser(theUser.getId());
		

	}

	@Override
	@Transactional
	public List<Studentwrapper> searchStudents(String theSearchName) {
		// TODO Auto-generated method stub
		List<Studentwrapper> studentwrappers = new ArrayList<Studentwrapper>();
		List<Student> students = studentDAO.searchStudents(theSearchName);
		for (Student theStudent : students) {
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

			theStudentWrapper.setCourses(theStudent.getCourses());
			User theUser = new User();
			theUser.setId(theStudent.getUser().getId());
			theUser.setUsername(theStudent.getUser().getUsername());
			theUser.setRoles(theStudent.getUser().getRoles());
			theUser.setPassword(theStudent.getUser().getPassword());
			theStudentWrapper.setUser(theUser);
			/*
			 * if(null != theCourse.getStandard())
			 * theCourseWrapper.setStandardName(theCourse.getStandard().getName(
			 * ) + " "+ theCourse.getStandard().getDivision());
			 */
			studentwrappers.add(theStudentWrapper);
		}
		return studentwrappers;
	}

}
