package classes.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import classes.dao.RoleDao;
import classes.dao.TeacherDAO;
import classes.dao.UserDao;
import classes.entity.Role;
import classes.entity.Teacher;
import classes.entity.User;
import classes.wrapper.Teacherwrapper;

@EnableWebSecurity
@Service
public class TeacherwrapperServiceImpl implements TeacherwrapperService {

	@Autowired
	private TeacherDAO teacherDAO;

	@Autowired
	private RoleDao roleDao;

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
	public List<Teacherwrapper> getTeacherwrappers() {
		// TODO Auto-generated method stub
		List<Teacherwrapper> teacherwrappers = new ArrayList<Teacherwrapper>();
		List<Teacher> teachers = teacherDAO.getTeachers();
		for (Teacher theTeacher : teachers) {
			Teacherwrapper theTeacherWrapper = new Teacherwrapper();
			theTeacherWrapper.setId(theTeacher.getId());
			theTeacherWrapper.setFirst_name(theTeacher.getFirst_name());
			theTeacherWrapper.setMiddle_name(theTeacher.getMiddle_name());
			theTeacherWrapper.setLast_name(theTeacher.getLast_name());
			theTeacherWrapper.setId_number(theTeacher.getId_number());

			theTeacherWrapper.setGender(theTeacher.getGender());
			theTeacherWrapper.setId_number(theTeacher.getId_number());
			theTeacherWrapper.setMobile_number1(theTeacher.getMobile_number1());
			theTeacherWrapper.setMobile_number2(theTeacher.getMobile_number2());

			theTeacherWrapper.setEmail(theTeacher.getEmail());
			theTeacherWrapper.setDateOfBirth(theTeacher.getDateOfBirth());
			theTeacherWrapper.setAddress(theTeacher.getAddress());

			theTeacherWrapper.setStandard(theTeacher.getStandard());
			theTeacherWrapper.setSubjects(theTeacher.getSubjects());
			theTeacherWrapper.setQualification(theTeacher.getQualification());
			User theUser = new User();
			theUser.setId(theTeacher.getUser().getId());
			theUser.setUsername(theTeacher.getUser().getUsername());
			theUser.setRoles(theTeacher.getUser().getRoles());
			theUser.setPassword(theTeacher.getUser().getPassword());
			theTeacherWrapper.setUser(theUser);

			/*
			 * if(null != theCourse.getStandard())
			 * theCourseWrapper.setStandardName(theCourse.getStandard().getName(
			 * ) + " "+ theCourse.getStandard().getDivision());
			 */
			teacherwrappers.add(theTeacherWrapper);
		}
		return teacherwrappers;
	}

	@Override
	@Transactional
	public List<Teacher> getTeachers() {
		// TODO Auto-generated method stub
		return teacherDAO.getTeachers();
	}

	@Override
	@Transactional
	public void saveTeacher(Teacherwrapper theTeacherwrapper) {
		// TODO Auto-generated method stub
		int teacherToModifyId = theTeacherwrapper.getId();
		List<Teacher> teachers = teacherDAO.getTeachers();
		Teacher foundTeacher = null;
		for (Teacher theTeacher : teachers) {
			int teacherId = theTeacher.getId();
			if (teacherToModifyId == teacherId) {
				foundTeacher = theTeacher;
				break;
			}
		}
		if (null == foundTeacher) {
			foundTeacher = new Teacher();
			foundTeacher.setId(theTeacherwrapper.getId());
		}

		foundTeacher.setId(theTeacherwrapper.getId());
		foundTeacher.setFirst_name(theTeacherwrapper.getFirst_name());
		foundTeacher.setMiddle_name(theTeacherwrapper.getMiddle_name());
		foundTeacher.setLast_name(theTeacherwrapper.getLast_name());
		foundTeacher.setId_number(theTeacherwrapper.getId_number());

		foundTeacher.setGender(theTeacherwrapper.getGender());
		foundTeacher.setId_number(theTeacherwrapper.getId_number());
		foundTeacher.setMobile_number1(theTeacherwrapper.getMobile_number1());
		foundTeacher.setMobile_number2(theTeacherwrapper.getMobile_number2());

		foundTeacher.setEmail(theTeacherwrapper.getEmail());
		foundTeacher.setDateOfBirth(theTeacherwrapper.getDateOfBirth());
		foundTeacher.setAddress(theTeacherwrapper.getAddress());

		foundTeacher.setSubjects(theTeacherwrapper.getSubjects());
		foundTeacher.setStandard(theTeacherwrapper.getStandard());
		foundTeacher.setQualification(theTeacherwrapper.getQualification());

		User theUser = foundTeacher.getUser();
		if (null == theUser) {
			theUser = new User();
			Role role1 = roleDao.findRoleByName("ROLE_COMMON");
			Role role2 = roleDao.findRoleByName("ROLE_TEACHER");
			theUser.addRole(role1);
			theUser.addRole(role2);

		}

		theUser.setUsername(theTeacherwrapper.getUsername());
		theUser.setPassword(theTeacherwrapper.getPassword());
		theUser.setPassword(passwordEncoder().encode(theUser.getPassword()));

		List<Role> roles = theUser.getRoles();
		Role foundAdminRole = null;
		if(null != roles){
		for (Role theRole : roles) {
			if (theRole.getName().equals("ROLE_ADMIN")) {
				foundAdminRole = theRole;
				break;
			}
		}
			if ((theTeacherwrapper.getAdminrole().equals("no")) && (null != foundAdminRole)) {
				theUser.removeRole(foundAdminRole);
			} else if ((theTeacherwrapper.getAdminrole().equals("yes")) && (null == foundAdminRole)) {
				Role role3 = roleDao.findRoleByName("ROLE_ADMIN");
				theUser.addRole(role3);
			}
		}
		userDao.save(theUser);
		foundTeacher.setUser(theUser);

		teacherDAO.saveTeacher(foundTeacher);
	}

	@Override
	@Transactional
	public Teacherwrapper getTeacher(int theId) {
		// TODO Auto-generated method stub
		Teacher theTeacher = teacherDAO.getTeacher(theId);
		Teacherwrapper theTeacherWrapper = new Teacherwrapper();
		theTeacherWrapper.setId(theTeacher.getId());
		theTeacherWrapper.setFirst_name(theTeacher.getFirst_name());
		theTeacherWrapper.setMiddle_name(theTeacher.getMiddle_name());
		theTeacherWrapper.setLast_name(theTeacher.getLast_name());
		theTeacherWrapper.setId_number(theTeacher.getId_number());

		theTeacherWrapper.setGender(theTeacher.getGender());
		theTeacherWrapper.setId_number(theTeacher.getId_number());
		theTeacherWrapper.setMobile_number1(theTeacher.getMobile_number1());
		theTeacherWrapper.setMobile_number2(theTeacher.getMobile_number2());

		theTeacherWrapper.setEmail(theTeacher.getEmail());
		theTeacherWrapper.setDateOfBirth(theTeacher.getDateOfBirth());
		theTeacherWrapper.setAddress(theTeacher.getAddress());

		theTeacherWrapper.setStandard(theTeacher.getStandard());
		theTeacherWrapper.setSubjects(theTeacher.getSubjects());
		theTeacherWrapper.setQualification(theTeacher.getQualification());
		User theUser = new User();
		theUser.setId(theTeacher.getUser().getId());
		theUser.setUsername(theTeacher.getUser().getUsername());
		theUser.setRoles(theTeacher.getUser().getRoles());
		theUser.setPassword(theTeacher.getUser().getPassword());
		theTeacherWrapper.setUser(theUser);
		return theTeacherWrapper;
	}

	@Override
	@Transactional
	public void deleteTeacher(int theId) {
		// TODO Auto-generated method stub
		Teacher theTeacher = teacherDAO.getTeacher(theId);
		User theUser = theTeacher.getUser();
		teacherDAO.deleteTeacher(theId);
		userDao.deleteUser(theUser.getId());

	}

	@Override
	@Transactional
	public List<Teacherwrapper> searchTeachers(String theSearchName) {
		// TODO Auto-generated method stub
		List<Teacherwrapper> teacherwrappers = new ArrayList<Teacherwrapper>();
		List<Teacher> teachers = teacherDAO.searchTeachers(theSearchName);
		for (Teacher theTeacher : teachers) {
			Teacherwrapper theTeacherWrapper = new Teacherwrapper();
			theTeacherWrapper.setId(theTeacher.getId());
			theTeacherWrapper.setFirst_name(theTeacher.getFirst_name());
			theTeacherWrapper.setMiddle_name(theTeacher.getMiddle_name());
			theTeacherWrapper.setLast_name(theTeacher.getLast_name());
			theTeacherWrapper.setId_number(theTeacher.getId_number());

			theTeacherWrapper.setGender(theTeacher.getGender());
			theTeacherWrapper.setId_number(theTeacher.getId_number());
			theTeacherWrapper.setMobile_number1(theTeacher.getMobile_number1());
			theTeacherWrapper.setMobile_number2(theTeacher.getMobile_number2());

			theTeacherWrapper.setEmail(theTeacher.getEmail());
			theTeacherWrapper.setDateOfBirth(theTeacher.getDateOfBirth());
			theTeacherWrapper.setAddress(theTeacher.getAddress());
			theTeacherWrapper.setQualification(theTeacher.getQualification());

			theTeacherWrapper.setStandard(theTeacher.getStandard());
			theTeacherWrapper.setSubjects(theTeacher.getSubjects());
			User theUser = new User();
			theUser.setId(theTeacher.getUser().getId());
			theUser.setUsername(theTeacher.getUser().getUsername());
			theUser.setRoles(theTeacher.getUser().getRoles());
			theUser.setPassword(theTeacher.getUser().getPassword());
			theTeacherWrapper.setUser(theUser);
			/*
			 * if(null != theCourse.getStandard())
			 * theCourseWrapper.setStandardName(theCourse.getStandard().getName(
			 * ) + " "+ theCourse.getStandard().getDivision());
			 */
			teacherwrappers.add(theTeacherWrapper);
		}
		return teacherwrappers;
	}

}
