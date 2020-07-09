package classes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import classes.entity.User;
import classes.entity.Videoinfo;
import classes.service.UserService;
import classes.service.VideoinfoService;
import classes.SMS.SMSData;
import classes.entity.Course;
import classes.entity.Exam;
import classes.entity.Examresult;
import classes.entity.Examdetails;
import classes.entity.Standard;
import classes.entity.Student;
import classes.entity.Subject;
import classes.service.ExamService;
import classes.service.ExamdetailsService;
import classes.service.SMSService;
import classes.service.StandardService;
import classes.service.StudentwrapperService;
import classes.service.SubjectService;
import classes.service.TeacherwrapperService;
import classes.wrapper.ExamDetailswrapper;
import classes.wrapper.Examresultwrapper;
import classes.wrapper.MessageBox;
import classes.wrapper.Studentwrapper;
import classes.wrapper.Subjectwrapper;
import classes.wrapper.Teacherwrapper;

@Controller
@RequestMapping("/teacherwrapper")
public class TeacherController {

	@Autowired
	private TeacherwrapperService teacherService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private UserService userService;

	@Autowired
	private ExamService examService;

	@Autowired
	private ExamdetailsService examdetailsService;

	@Autowired
	private StudentwrapperService studentwrapperservice;
	
	@Autowired
	private VideoinfoService videoinfoService;

	List<Subject> subjects;

	@RequestMapping("/list")
	public String listTeachers(Model theModel) {

		List<Teacherwrapper> teachers = teacherService.getTeacherwrappers();

		theModel.addAttribute("teachers", teachers);

		return "list-teachers";
	}

	@RequestMapping("/mySubjectList")
	public String mySubjectList(@RequestParam("teacherId") int theId, Model theModel) {

		Teacherwrapper myTeacherwrapper = teacherService.getTeacher(theId);
		List<Subjectwrapper> subjects = myTeacherwrapper.getSubjectwrappers();

		theModel.addAttribute("subjects", subjects);

		return "list-subjects";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Teacherwrapper theTeacherwrapper = new Teacherwrapper();
		theModel.addAttribute("teacherwrapper", theTeacherwrapper);
		// add the country options to the model

		return "teacher-form";
	}

	@PostMapping("/saveTeacher2")
	public String saveTeacher2(@ModelAttribute("teacherwrapper") Teacherwrapper theTeacherwrapper,
			@RequestParam("subjectNames") String[] subjectNamesList, BindingResult theBindingResult, Model theModel) {

		// form validation
		if (theBindingResult.hasErrors()) {
			return "teacher-form";
		}
		// check the database if user already exists
		User existing = null;
		if (0 == theTeacherwrapper.getId())
			existing = userService.findByUserName(theTeacherwrapper.getUsername());
		if (existing != null) {
			theModel.addAttribute("teacherwrapper", theTeacherwrapper);
			theModel.addAttribute("registrationError", "User name already exists.");
			return "teacher-form";
		}

		// check if password is same
		if (false == (theTeacherwrapper.getPassword().equals(theTeacherwrapper.getMatchingPassword()))) {
			theModel.addAttribute("teacherwrapper", theTeacherwrapper);
			theModel.addAttribute("registrationError", "Password entered is not same.");
			return "teacher-form";
		}

		if (null == subjects)
			subjects = subjectService.getSubjects();
		// String StName2 = standardNameList[0];
		List<Subject> thetempSubjects = new ArrayList<Subject>();
		theTeacherwrapper.setSubjects(thetempSubjects);
		for (String subName2 : subjectNamesList) {

			for (Subject theSubject : subjects) {
				String subName = theSubject.getName();
				if (true == subName.equals(subName2)) {
					theTeacherwrapper.getSubjects().add(theSubject);
				}
				/*
				 * List<Course> courses = theSubject.getCourses(); for (Course
				 * theCourse : courses) { List<Standard> standards =
				 * theCourse.getStandards(); for (Standard theStandard :
				 * standards) { subName = subName + " " + theStandard.getName();
				 * break; } if (true == subName.equals(subName2)) {
				 * theTeacherwrapper.getSubjects().add(theSubject); } }
				 */

			}
		}
		// theTeacherwrapper.loadUser();
		teacherService.saveTeacher(theTeacherwrapper);
		return "redirect:/teacherwrapper/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("teacherId") int theId, Model theModel) {

		// get the customer from our service
		Teacherwrapper theTeacherwrapper = teacherService.getTeacher(theId);

		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("teacherwrapper", theTeacherwrapper);
		// theModel.addAttribute("Std", theCourse.getStandard());

		// send over to our form
		return "teacher-form";
	}

	@GetMapping("/delete")
	public String deleteTeacher(@RequestParam("teacherId") int theId) {

		teacherService.deleteTeacher(theId);
		return "redirect:/teacherwrapper/list";
	}

	@GetMapping("/search")
	public String searchTeachers(@RequestParam("theSearchName") String theSearchName, Model theModel) {

		// search customers from the service
		List<Teacherwrapper> teachers = teacherService.searchTeachers(theSearchName);

		// add the customers to the model
		theModel.addAttribute("teachers", teachers);

		return "list-teachers";
	}

	@GetMapping("/createExamDetails")
	public String manageExamResult(@RequestParam("teacherId") int theId, Model theModel) {
		Teacherwrapper myTeacherwrapper = teacherService.getTeacher(theId);
		theModel.addAttribute("teacherwrapper", myTeacherwrapper);
		List<String> subNames = myTeacherwrapper.getSubjectNames();

		// get the customer from our service
		return "examdetails-form";
	}

	@PostMapping("/createExamResult")
	public String createExamResult(@ModelAttribute("teacherwrapper") Teacherwrapper theTeacherwrapper, Model theModel) {

		List<Exam> exams = examService.searchExams(theTeacherwrapper.getExamDetails().getExamName());
		Exam theExam = exams.get(0);

		List<Subject> subjects = subjectService.searchSubjects(theTeacherwrapper.getExamDetails().getSubjectName());
		Subject theSubject = subjects.get(0);
		if ((null != theExam) && (null != theSubject)) {
			Examdetails theExamdetails = new Examdetails();
			theExamdetails.setExam_id(theExam.getId());
			theExamdetails.setTotalmarks(theTeacherwrapper.getExamDetails().getTotalmarks());
			for (Course theCourse : theSubject.getCourses()) {
				for (Student theStudent : theCourse.getStudents()) {
					Examresult theExamResult = new Examresult();
					theExamResult.setStudent_id(theStudent.getId());
					theExamResult.setMarksobtained(0);
					theExamResult.setStatus(true);
					theExamdetails.addExamresult(theExamResult);
				}
			}
			theSubject.getExamdetails().add(theExamdetails);
		}
		// theSubject.getExamdetails().clear();
		subjectService.saveSubject(theSubject);
		Teacherwrapper myTeacherwrapper = teacherService.getTeacher(theTeacherwrapper.getId());
		List<Subjectwrapper> subjectwrappers = myTeacherwrapper.getSubjectwrappers();
		for (Subjectwrapper theSubjectwrapper : subjectwrappers) {
			List<ExamDetailswrapper> listExamDetailswrapper = theSubjectwrapper.getExamdetailswrapperlist();
			for (ExamDetailswrapper examDetailswrapper : listExamDetailswrapper) {
				String examName = examService.getExam(examDetailswrapper.getExam_id()).getName();
				examDetailswrapper.setExamName(examName);
			}
		}
		// List<Subjectwrapper> mysubjects =
		// myTeacherwrapper.getSubjectwrappers();
		theModel.addAttribute("teacherwrapper", myTeacherwrapper);
		// get the customer from our service
		return "list-SubjectExamResult";
	}

	@PostMapping("/saveExamResult")
	public String saveExamResult(@ModelAttribute("examDetailswrapper") ExamDetailswrapper theExamDetailswrapper,
			@RequestParam(value = "teacher_id", required = false) int teacher_id,
			@RequestParam(value = "subject_id", required = false) int subject_id, Model theModel) {
		List<Examresultwrapper> theExamresultwrappers = theExamDetailswrapper.getExamresultwrappers();
		Examdetails theExamdetails = examdetailsService.getExamdetails(theExamDetailswrapper.getId());
		if (null != theExamdetails) {
			List<Examresult> examresultsList = theExamdetails.getExamresults();
			if (theExamresultwrappers.size() == examresultsList.size()) {
				for (Examresult theExamresult : examresultsList) {
					Studentwrapper theStudentwrapper = studentwrapperservice.getStudent(theExamresult.getStudent_id());
					if (null != theStudentwrapper) {
						for (Examresultwrapper theExamresultwrapper : theExamresultwrappers) {
							if (theExamresultwrapper.getStudentname().equals(theStudentwrapper.getFull_name())) {
								theExamresult.setMarksobtained(theExamresultwrapper.getMarksobtained());
								theExamresult.setStatus(theExamresultwrapper.isPresentyStatus());
								break;
							}
						}
					}
				}
			}
		}
		examdetailsService.saveExamdetails(theExamdetails);
		Teacherwrapper myTeacherwrapper = teacherService.getTeacher(theExamDetailswrapper.getTeacher_id());
		List<Subjectwrapper> subjectwrappers = myTeacherwrapper.getSubjectwrappers();
		for (Subjectwrapper theSubjectwrapper : subjectwrappers) {
			List<ExamDetailswrapper> listExamDetailswrapper = theSubjectwrapper.getExamdetailswrapperlist();
			for (ExamDetailswrapper examDetailswrapper : listExamDetailswrapper) {
				String examName = examService.getExam(examDetailswrapper.getExam_id()).getName();
				examDetailswrapper.setExamName(examName);
			}
		}
		theModel.addAttribute("teacherwrapper", myTeacherwrapper);
		// get the customer from our service
		return "list-SubjectExamResult";
	}

	@GetMapping("/listExamResult")
	public String listExamResult(@ModelAttribute("teacherId") int theId, Model theModel) {
		Teacherwrapper myTeacherwrapper = teacherService.getTeacher(theId);
		List<Subjectwrapper> subjectwrappers = myTeacherwrapper.getSubjectwrappers();
		for (Subjectwrapper theSubjectwrapper : subjectwrappers) {
			List<ExamDetailswrapper> listExamDetailswrapper = theSubjectwrapper.getExamdetailswrapperlist();
			for (ExamDetailswrapper theExamDetailswrapper : listExamDetailswrapper) {
				String examName = examService.getExam(theExamDetailswrapper.getExam_id()).getName();
				theExamDetailswrapper.setExamName(examName);
			}
		}
		theModel.addAttribute("teacherwrapper", myTeacherwrapper);
		// get the customer from our service
		return "list-SubjectExamResult";
	}

	@GetMapping("/deleteExamResult")
	public String deleteExamResult(@RequestParam("examdetailId") int examdetailId,
			@RequestParam("subjectId") int subjectId, @RequestParam("teacherId") int teacherId, Model theModel) {

		Subject theSubject = subjectService.getSubject(subjectId);
		if (null != theSubject) {
			theSubject.deleteExamDetails(examdetailId);
			subjectService.saveSubject(theSubject);
			examdetailsService.deleteExamdetails(examdetailId);
		}
		Teacherwrapper myTeacherwrapper = teacherService.getTeacher(teacherId);
		List<Subjectwrapper> subjectwrappers = myTeacherwrapper.getSubjectwrappers();
		for (Subjectwrapper theSubjectwrapper : subjectwrappers) {
			List<ExamDetailswrapper> listExamDetailswrapper = theSubjectwrapper.getExamdetailswrapperlist();
			for (ExamDetailswrapper examDetailswrapper : listExamDetailswrapper) {
				String examName = examService.getExam(examDetailswrapper.getExam_id()).getName();
				examDetailswrapper.setExamName(examName);
			}
		}
		theModel.addAttribute("teacherwrapper", myTeacherwrapper);
		// get the customer from our service
		return "list-SubjectExamResult";
	}

	@GetMapping("/showExamResultFormForUpdate")
	public String showExamResultFormForUpdate(@RequestParam("examdetailId") int examdetailId,
			@RequestParam("subjectId") int subjectId, @RequestParam("teacherId") int teacherId, Model theModel) {

		Subject theSubject = subjectService.getSubject(subjectId);
		Examdetails foundExamdetails = null;
		if (null != theSubject) {
			List<Examdetails> Examdetailslist = theSubject.getExamdetails();
			for (Examdetails theExamdetails : Examdetailslist) {
				if (theExamdetails.getId() == examdetailId) {
					foundExamdetails = theExamdetails;
					break;
				}
			}
		}
		ExamDetailswrapper theExamDetailswrapper = new ExamDetailswrapper(foundExamdetails);
		theExamDetailswrapper.populateExamResultDetails(studentwrapperservice);
		theExamDetailswrapper.setTeacher_id(teacherId);
		theExamDetailswrapper.setSubject_id(subjectId);
		String examName = examService.getExam(foundExamdetails.getExam_id()).getName();
		theExamDetailswrapper.setExamName(examName);
		theModel.addAttribute("examDetailswrapper", theExamDetailswrapper);
		// get the customer from our service
		return "examresult-form";
	}

	@GetMapping("/showFormForVideoAdd")
	public String showFormForVideoAdd(@RequestParam("teacherwrapperId") int theId, Model theModel) {

		Teacherwrapper theTeacherwrapper = teacherService.getTeacher(theId);

		theModel.addAttribute("teacherwrapper", theTeacherwrapper);
		return "video-form";
	}
	
	@GetMapping("/showFormForVideoUpdate")
	public String showFormForVideoUpdate(@RequestParam("videoInfoId") int thevideoInfoId, 
			@RequestParam("teacherwrapperId") int teacherwrapperId,
			Model theModel) {

		Teacherwrapper theTeacherwrapper = teacherService.getTeacher(teacherwrapperId);
		List<Videoinfo> videoinfolist = new ArrayList<Videoinfo>();
		if (null != theTeacherwrapper) 
		{
			List<Subjectwrapper> subjectwrappers = theTeacherwrapper.getSubjectwrappers();
			
			for (Subjectwrapper theSubjectwrapper : subjectwrappers) {

				List<Videoinfo> myVideoinfolist = theSubjectwrapper.getListOfVideos();
				videoinfolist.addAll(myVideoinfolist);

			}
		}
		for (Videoinfo theVideoinfo : videoinfolist) {
			if(thevideoInfoId ==theVideoinfo.getId())
			{
				theTeacherwrapper.setVideo_id(theVideoinfo.getId());
				theTeacherwrapper.setVideo_Name(theVideoinfo.getName());
				theTeacherwrapper.setSubjectName_OfVideo(theVideoinfo.getSubject_name());
				theTeacherwrapper.setbVideoViewStatus(theVideoinfo.isView_status());
				break;
			}
		}
		
		theModel.addAttribute("teacherwrapper", theTeacherwrapper);
		return "video-form";
	}
	
	@GetMapping("/deleteVideo")
	public String deleteVideo(@RequestParam("videoInfoId") int thevideoInfoId, 
			@RequestParam("teacherwrapperId") int teacherwrapperId,
			Model theModel) {

		Teacherwrapper theTeacherwrapper = teacherService.getTeacher(teacherwrapperId);
		List<Videoinfo> videoinfolist = new ArrayList<Videoinfo>();
		if (null != theTeacherwrapper) 
		{
			List<Subjectwrapper> subjectwrappers = theTeacherwrapper.getSubjectwrappers();
			
			for (Subjectwrapper theSubjectwrapper : subjectwrappers) {

				List<Videoinfo> myVideoinfolist = theSubjectwrapper.getListOfVideos();
				for (Videoinfo theVideoinfo : myVideoinfolist) {
					if(thevideoInfoId ==theVideoinfo.getId())
					{
						Subject theSubject = subjectService.getSubject(theSubjectwrapper.getId());
						theSubject.removeVideo(theVideoinfo);
						subjectService.saveSubject(theSubject);
						myVideoinfolist.remove(theVideoinfo);
						videoinfoService.deleteVideoinfo(theVideoinfo.getId());
						break;
					}
				}
				videoinfolist.addAll(myVideoinfolist);

			}
		}
		theTeacherwrapper.setListOfVideos(videoinfolist);
		theModel.addAttribute("teacherwrapper", theTeacherwrapper);
		return "list-videos";
	}
	
	@GetMapping("/listSubjectVidoes")
	public String listSubjectVidoes(@RequestParam("id") int theId, Model theModel) {
		List<Videoinfo> videoinfolist = new ArrayList<Videoinfo>();
		Teacherwrapper theTeacherwrapper = teacherService.getTeacher(theId);
		if (null != theTeacherwrapper) {
			List<Subjectwrapper> subjectwrappers = theTeacherwrapper.getSubjectwrappers();
			
			for (Subjectwrapper theSubjectwrapper : subjectwrappers) {

				List<Videoinfo> myVideoinfolist = theSubjectwrapper.getListOfVideos();
				videoinfolist.addAll(myVideoinfolist);

			}
			theTeacherwrapper.setListOfVideos(videoinfolist);
		}
		theModel.addAttribute("teacherwrapper", theTeacherwrapper);
		return "list-videos";
	}
	
	@PostMapping("/saveVideoInfo")
	public String saveVideoInfo(@ModelAttribute("teacherwrapper") Teacherwrapper theTeacherwrapper, Model theModel) {

		Teacherwrapper myTeacherwrapper = teacherService.getTeacher(theTeacherwrapper.getId());
		if (null != myTeacherwrapper) {
			List<Subjectwrapper> subjectwrappers = myTeacherwrapper.getSubjectwrappers();
			List<Videoinfo> videoinfolist = new ArrayList<Videoinfo>();
			for (Subjectwrapper theSubjectwrapper : subjectwrappers) {

				List<Videoinfo> myVideoinfolist = theSubjectwrapper.getListOfVideos();
				videoinfolist.addAll(myVideoinfolist);

			}
			boolean bVideoFound = false;
			for(Videoinfo theVideoinfo:videoinfolist){
				if(theTeacherwrapper.getVideo_id() == theVideoinfo.getId()){
					theVideoinfo.setName(theTeacherwrapper.getVideo_Name());
					theVideoinfo.setSubject_name(theTeacherwrapper.getSubjectName_OfVideo());
					theVideoinfo.setView_status(theTeacherwrapper.isbVideoViewStatus());
					for (Subjectwrapper theSubjectwrapper : subjectwrappers) {
						if(theSubjectwrapper.getName().equals(theVideoinfo.getSubject_name())){
							Subject theSubject = subjectService.getSubject(theSubjectwrapper.getId());
							List<Videoinfo> subVideoinfolist = theSubject.getListOfVideos();
							for (Videoinfo subVideoinfo : subVideoinfolist) {
								if(subVideoinfo.getId() == theVideoinfo.getId()){
									subVideoinfo.setName(theVideoinfo.getName());
									subVideoinfo.setSubject_name(theVideoinfo.getSubject_name());
									subVideoinfo.setView_status(theVideoinfo.isView_status());
									break;
								}
							}
							subjectService.saveSubject(theSubject);
							
							break;
						}
					}
					bVideoFound = true;
					break;
				}
			}
			if(!bVideoFound)
			{
				Videoinfo theVideoinfo = new Videoinfo();
				theVideoinfo.setName(theTeacherwrapper.getVideo_Name());
				theVideoinfo.setSubject_name(theTeacherwrapper.getSubjectName_OfVideo());
				theVideoinfo.setView_status(theTeacherwrapper.isbVideoViewStatus());
				for (Subjectwrapper theSubjectwrapper : subjectwrappers) {
					if(theSubjectwrapper.getName().equals(theVideoinfo.getSubject_name())){
						Subject theSubject = subjectService.getSubject(theSubjectwrapper.getId());
						theSubject.addVideo(theVideoinfo);
						subjectService.saveSubject(theSubject);
						videoinfolist.add(theVideoinfo);
						break;
					}
				}
			}
			myTeacherwrapper.setListOfVideos(videoinfolist);
		}
		theModel.addAttribute("teacherwrapper", myTeacherwrapper);
		return "list-videos";
	}
	

	@ModelAttribute("subjectList")
	public List<String> getsubjectList() {

		List<String> subjectList = new ArrayList<String>();
		// if (null == subjects)
		subjects = subjectService.getSubjects();
		for (Subject theSubject : subjects) {
			String subName = theSubject.getName();
			subjectList.add(subName);
		}
		return subjectList;
	}

	@ModelAttribute("examNames")
	public List<String> getexamNames() {

		List<String> examNames = new ArrayList<String>();
		// if (null == subjects)
		List<Exam> exams = examService.getExams();
		for (Exam theExam : exams) {
			String examName = theExam.getName();
			examNames.add(examName);
		}
		return examNames;
	}

}
