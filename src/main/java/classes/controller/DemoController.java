package classes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import classes.entity.Course;
import classes.entity.Role;
import classes.entity.Subject;
import classes.entity.User;
import classes.entity.Videoinfo;
import classes.service.StudentService;
import classes.service.StudentwrapperService;
import classes.service.TeacherwrapperService;
import classes.service.UserService;
import classes.wrapper.Studentwrapper;
import classes.wrapper.Teacherwrapper;

@Controller
public class DemoController {

	@Autowired
	private UserService userService;

	@Autowired
	private TeacherwrapperService teacherService;

	@Autowired
	private StudentwrapperService studentService;

	@GetMapping("/")
	public String showLanding() {

		return "landing";
	}
	
	@GetMapping("/licenseError")
	public String showlicenseError() {

		return "licenseError";
	}

	@GetMapping("/home")
	public String showHome() {

		return "home";
	}

	/*
	 * @GetMapping("/video") public String showVideo() {
	 * 
	 * return "video"; }
	 */
	// add request mapping for /teachers

	@GetMapping("/teachers")
	public String showLeaders(Model theModel) {
		String username = "";
		boolean bTeacherFound = false;
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated()) {
			if (auth.getDetails() != null) {
				UserDetails userDetails = (UserDetails) auth.getPrincipal();
				username = userDetails.getUsername();
				User theUser = userService.findByUserName(username);

				if (null != theUser) {

					List<Teacherwrapper> teachers = teacherService.getTeacherwrappers();
					for (Teacherwrapper theTeacherwrapper : teachers) {
						if (theTeacherwrapper.getUser().getUsername().equals(username)) {
							theModel.addAttribute("teacherwrapper", theTeacherwrapper);
							bTeacherFound = true;
							break;
						}
					}
				}
			}

		}
		return "teachers";
	}

	// add request mapping for /admins

	@GetMapping("/admins")
	public String showSystems() {

		return "admins";
	}

	// add request mapping for /students

	@GetMapping("/students")
	public String showStudents(Model theModel) {
		String username = "";
		boolean bStudentFound = false;
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated()) {
			if (auth.getDetails() != null) {
				UserDetails userDetails = (UserDetails) auth.getPrincipal();
				username = userDetails.getUsername();
				User theUser = userService.findByUserName(username);

				if (null != theUser) {
					List<Studentwrapper> studentwrappers = studentService.getStudents();
					for (Studentwrapper theStudentwrapper : studentwrappers) {
						if (theStudentwrapper.getUser().getUsername().equals(username)) {
							if (null != theStudentwrapper) {
								List<Course> courses = theStudentwrapper.getCourses();
								List<Subject> subjects = new ArrayList<Subject>();
								for (Course theCourse : courses) {
									List<Subject> mySubjects = theCourse.getSubjects();
									subjects.addAll(mySubjects);
								}
								List<Videoinfo> videoinfolist = new ArrayList<Videoinfo>();
								for (Subject theSubject : subjects) {
									List<Videoinfo> myVideoinfolist = theSubject.getListOfVideos();
									for(Videoinfo theVideoinfo:myVideoinfolist){
										if(true == theVideoinfo.isView_status())
											videoinfolist.add(theVideoinfo);
									}
									
								}
								theStudentwrapper.setListOfVideos(videoinfolist);
								theModel.addAttribute("studentwrapper", theStudentwrapper);
								bStudentFound = true;
								break;
							}
						}
					}
				}

			}
			
		}
		return "students";
	}
}
