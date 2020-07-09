package classes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import classes.entity.Course;
import classes.entity.Presentyrecord;
import classes.entity.Standard;
import classes.entity.Teacher;

import classes.service.CoursewrapperService;
import classes.service.SubjectwrapperService;
import classes.service.TeacherwrapperService;
import classes.wrapper.Presentyrecordwrapper;
import classes.wrapper.Subjectwrapper;

@Controller
@RequestMapping("/subjectwrapper")
public class SubjectController {
	
	@Autowired
	private SubjectwrapperService subjectService;
	
	@Autowired
	private CoursewrapperService courseService;

	private List<Course> courses;
	
	@Autowired
	private TeacherwrapperService teacherService;

	private List<Teacher> teachers;
	
	@RequestMapping("/list")
	public String listSubjects(Model theModel){
		
		List<Subjectwrapper> subjects = subjectService.getSubjects();	
		theModel.addAttribute("subjects", subjects);
		
		return "list-subjects";
	}
	
	@GetMapping("/showSubjectFormForAdd")
	public String showFormForAdd(Model theModel){
		Subjectwrapper theSubject = new Subjectwrapper();
		theModel.addAttribute("subjectwrapper", theSubject);
		return "subject-form";
	}
	
	@PostMapping("/saveSubject")
	public String saveSubject(@ModelAttribute("subjectwrapper") Subjectwrapper theSubjectwrapper,
			@RequestParam(required = false, value ="courseNames",defaultValue = "{}") String[] courseNamesList,
			@RequestParam(required = false, value ="teacherNames",defaultValue = "{}") String[] teacherNamesList)
	{
		if (null == courses)
			courses = courseService.getCourses();
		List<Course> thetempCourses = new ArrayList<Course>();
		theSubjectwrapper.setCourses(thetempCourses);
		for (String StName2 : courseNamesList) {

			for (Course theCourse : courses) {
				String StName = theCourse.getTitle();
				if (true == StName.equals(StName2)) {
					theSubjectwrapper.getCourses().add(theCourse);
				}
				/*List<Standard> standards = theCourse.getStandards();
				for (Standard theStandard : standards) {
					StName = StName + " " + theStandard.getName() + " " + theStandard.getBatch();
					if (true == StName.equals(StName2)) {

						theSubjectwrapper.getCourses().add(theCourse);

					}
				}*/
			}
		}
		
		if (null == teachers)
			teachers = teacherService.getTeachers();
		List<Teacher> thetempTeachersList = new ArrayList<Teacher>();
		theSubjectwrapper.setTeachers(thetempTeachersList);
		for (String theTeacherName : teacherNamesList) {

			for (Teacher theTeacher : teachers) {
				String theTeacherName2 = theTeacher.getFirst_name()+ " "+
	                     theTeacher.getMiddle_name()+ " "+
				         theTeacher.getLast_name()+"(" +
	                     theTeacher.getId_number()+")";
				if (true == theTeacherName.equals(theTeacherName2)) {

					theSubjectwrapper.getTeachers().add(theTeacher);
				}
			}
		}
		
		subjectService.saveSubject(theSubjectwrapper);
		return "redirect:/subjectwrapper/list";
	}
	
	@GetMapping("/showSubjectFormForUpdate")
	public String showFormForUpdate(@RequestParam("subjectId") int theId,
									Model theModel) {
		
		// get the customer from our service
		Subjectwrapper theSubjectwrapper = subjectService.getSubject(theId);	
		
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("subjectwrapper", theSubjectwrapper);
		
		// send over to our form		
		return "subject-form";
	}
	
	@GetMapping("/delete")
	public String deleteExam(@RequestParam("subjectId") int theId) {
		
		subjectService.deleteSubject(theId);
		return "redirect:/subjectwrapper/list";
	}
	
	@GetMapping("/searchSubject")
	public String searchExams(@RequestParam("theSearchName") String theSearchName,
									Model theModel) {

		// search customers from the service
		List<Subjectwrapper> subjects = subjectService.searchSubjects(theSearchName);
				
		// add the customers to the model
		theModel.addAttribute("subjects", subjects);

		return "list-subjects";		
	}
	
	@GetMapping("/presentyrecords")
	public String presentyrecords(@RequestParam("subjectId") int theId,
									Model theModel) {
		
		// get the customer from our service
		Subjectwrapper theSubjectwrapper = subjectService.getSubject(theId);	
		
		List<Presentyrecordwrapper> presentyrecordwrappers = theSubjectwrapper.getPresentyrecordwrappers();
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("subjectwrapper", theSubjectwrapper);
		
		// send over to our form		
		return "list-presentyRecords";
	}
	
	@ModelAttribute("courseList")
	public List<String> getcourseList() {

		List<String> courseList = new ArrayList<String>();
		//if (null == courses)
			courses = courseService.getCourses();
		
		for (Course theCourse : courses) {
			String StName = theCourse.getTitle();
			/*List<Standard> standards = theCourse.getStandards();
			for (Standard theStandard : standards) {
				StName = StName + " " + theStandard.getName() + " " + theStandard.getBatch();
			}*/
			courseList.add(StName);
		}
		return courseList;
	}
	
	@ModelAttribute("teacherList")
	public List<String> getteacherList() {

		List<String> teacherList = new ArrayList<String>();
		//if (null == teachers)
			teachers = teacherService.getTeachers();
		for (Teacher theTeacher : teachers) {
	
			teacherList.add(theTeacher.getFirst_name()+ " "+
                    theTeacher.getMiddle_name()+ " "+
			         theTeacher.getLast_name()+"(" +
                    theTeacher.getId_number()+")");
		}
		return teacherList;
	}

}
