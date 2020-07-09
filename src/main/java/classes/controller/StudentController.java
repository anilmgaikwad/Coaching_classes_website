package classes.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import classes.wrapper.Coursewrapper;
import classes.wrapper.Feeinstallmentwrapper;
import classes.wrapper.MarkSheet;
import classes.entity.Course;
import classes.entity.Exam;
import classes.entity.Examdetails;
import classes.entity.Feeinstallment;
import classes.entity.Feetable;
import classes.entity.Standard;
import classes.entity.Student;
import classes.entity.Subject;
import classes.entity.User;
import classes.entity.Videoinfo;
import classes.pdfexport.ExportMarkSheet;
import classes.service.CourseService;
import classes.service.ExamService;
import classes.service.StudentService;
import classes.service.StudentwrapperService;
import classes.service.UserService;
import classes.wrapper.Studentwrapper;

@Controller
@RequestMapping("/studentwrapper")
public class StudentController {

	@Autowired
	private StudentwrapperService studentService;

	@Autowired
	private StudentService studentService2;

	@Autowired
	private CourseService courseService;

	private List<Course> courses;

	@Autowired
	private UserService userService;

	@Autowired
	private ExamService examService;
	
	@Autowired
	ServletContext servletContext;

	/*
	 * @InitBinder public void initBinder(WebDataBinder binder) {
	 * SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	 * sdf.setLenient(true); binder.registerCustomEditor(Date.class, new
	 * CustomDateEditor(sdf, true)); }
	 */

	@RequestMapping("/list")
	public String listStudents(Model theModel) {

		List<Studentwrapper> students = studentService.getStudents();

		theModel.addAttribute("students", students);

		return "list-students";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Studentwrapper theStudentwrapper = new Studentwrapper();
		theModel.addAttribute("studentwrapper", theStudentwrapper);
		// add the country options to the model

		return "student-form";
	}

	@PostMapping("/savestudent2")
	public String savestudent2(@ModelAttribute("studentwrapper") Studentwrapper theStudentwrapper,
			@RequestParam("courseNames") String[] courseNamesList, BindingResult theBindingResult, Model theModel) {

		// form validation
		if (theBindingResult.hasErrors()) {
			return "student-form";
		}

		// check the database if user already exists
		User existing = null;
		if (0 == theStudentwrapper.getId())
			existing = userService.findByUserName(theStudentwrapper.getUsername());
		if (existing != null) {
			theModel.addAttribute("studentwrapper", theStudentwrapper);
			theModel.addAttribute("registrationError", "User name already exists.");
			return "student-form";
		}

		// check if password is same
		if (false == (theStudentwrapper.getPassword().equals(theStudentwrapper.getMatchingPassword()))) {
			theModel.addAttribute("studentwrapper", theStudentwrapper);
			theModel.addAttribute("registrationError", "Password entered is not same.");
			return "student-form";
		}

		// if (null == courses)
		courses = courseService.getCourses();
		// String StName2 = standardNameList[0];
		Student myStudent = studentService2.getStudent(theStudentwrapper.getId());
		Feetable theFeetable = null;
		if (null != myStudent)
			theFeetable = myStudent.getFeetable();
		if (null != theFeetable)
			theStudentwrapper.setFeetable(theFeetable);
		List<Course> thetempCourses = new ArrayList<Course>();

		for (String StName2 : courseNamesList) {

			for (Course theCourse : courses) {
				String StName = theCourse.getTitle();
				if (true == StName.equals(StName2)) {
					thetempCourses.add(theCourse);

				}
				/*
				 * List<Standard> standards = theCourse.getStandards(); for
				 * (Standard theStandard : standards) { StName = StName + " " +
				 * theStandard.getName() + " " + theStandard.getBatch(); if
				 * (true == StName.equals(StName2)) {
				 * thetempCourses.add(theCourse);
				 * 
				 * } }
				 */
			}
		}
		theStudentwrapper.setCourses(thetempCourses);

		studentService.saveStudent(theStudentwrapper);
		return "redirect:/studentwrapper/list";
	}

	@GetMapping("/feeTable")
	public String feeTable(@RequestParam("studentId") int theId, Model theModel) {
		// get the customer from our service
		Studentwrapper theStudentwrapper = studentService.getStudent(theId);

		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("studentwrapper", theStudentwrapper);
		// theModel.addAttribute("Std", theCourse.getStandard());
		return "fee-table";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {

		// get the customer from our service
		Studentwrapper theStudentwrapper = studentService.getStudent(theId);

		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("studentwrapper", theStudentwrapper);
		// theModel.addAttribute("Std", theCourse.getStandard());

		// send over to our form
		return "student-form";
	}

	@GetMapping("/delete")
	public String deleteCourse(@RequestParam("studentId") int theId) {

		studentService.deleteStudent(theId);
		return "redirect:/studentwrapper/list";
	}

	@GetMapping("/search")
	public String searchCourses(@RequestParam("theSearchName") String theSearchName, Model theModel) {

		// search customers from the service
		List<Studentwrapper> students = studentService.searchStudents(theSearchName);

		// add the customers to the model
		theModel.addAttribute("students", students);

		return "list-students";
	}

	@GetMapping("/addNewFeeinstallment")
	public String addNewFeeinstallment(@RequestParam("studentId") int theId, Model theModel) {

		// get the customer from our service
		Studentwrapper theStudentwrapper = studentService.getStudent(theId);
		Feeinstallmentwrapper theFeeInstallment = theStudentwrapper.getTheFeeinstallmentwrapper();
		theFeeInstallment.setId(0);
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("studentwrapper", theStudentwrapper);
		// theModel.addAttribute("Std", theCourse.getStandard());

		// send over to our form
		return "fee-installment";
	}

	@GetMapping("/myExamResultsList")
	public String myExamResultsList(@RequestParam("studentId") int theId, Model theModel) {

		// get the customer from our service
		Studentwrapper theStudentwrapper = studentService.getStudent(theId);
		if (null != theStudentwrapper) {
			List<Course> courses = theStudentwrapper.getCourses();
			List<Subject> subjects = new ArrayList<Subject>();
			for (Course theCourse : courses) {
				List<Subject> mySubjects = theCourse.getSubjects();
				subjects.addAll(mySubjects);
			}
			List<Examdetails> examdetailsList = new ArrayList<Examdetails>();
			;
			for (Subject theSubject : subjects) {
				examdetailsList.addAll(theSubject.getExamdetails());
			}
			List<Examdetails> sortedExamdetailsList = new ArrayList<Examdetails>();
			if (0 != examdetailsList.size())
			{
				sortedExamdetailsList.add(examdetailsList.get(0));
				examdetailsList.remove(0);
			}
	
			for (int i = 0; i < examdetailsList.size(); i++) {
				
				int exam_id = examdetailsList.get(i).getExam_id();
				boolean bfound = false;
				for (int j = 0; j < sortedExamdetailsList.size(); j++) 
				{
					if (exam_id == sortedExamdetailsList.get(j).getExam_id()) {
						bfound = true;
						break;
					}
				}
				if(false == bfound)
					sortedExamdetailsList.add(examdetailsList.get(i));
			}
			for (int i = 0; i < sortedExamdetailsList.size(); i++) {
				Exam theExam = examService.getExam(sortedExamdetailsList.get(i).getExam_id());
				theStudentwrapper.getTermExamsList().add(theExam);
			}
			theModel.addAttribute("studentwrapper", theStudentwrapper);
		}
		// send over to our form
		return "student-termExamList";
	}

	@GetMapping("/showMyExamResult")
	public String showMyExamResult(@RequestParam("studentId") int theStudentId,
			@RequestParam("termExamId") int thetermExamId, Model theModel) {

		// get the customer from our service
		Studentwrapper theStudentwrapper = studentService.getStudent(theStudentId);
		if (null != theStudentwrapper) {

			Exam theExam = examService.getExam(thetermExamId);
			MarkSheet theMarkSheet = new MarkSheet(theStudentwrapper, thetermExamId, theExam.getName());
			String relativeWebPath = "/src/main/webapp/resources/image/";
			String absoluteDiskPath = servletContext.getRealPath(relativeWebPath);
			theMarkSheet.setFileRelativePath(absoluteDiskPath);
			ExportMarkSheet theExportMarkSheet = new ExportMarkSheet(theMarkSheet);
			theExportMarkSheet.export();
			theModel.addAttribute("marksheet", theMarkSheet);
		}

		// send over to our form
		return "marksheet";
	}

	@PostMapping("/savefeeinstallment")
	public String savefeeinstallment(@ModelAttribute("studentwrapper") Studentwrapper theStudentwrapper,
			@RequestParam("id") int theId, Model theModel) {
		Student myStudent = studentService2.getStudent(theId);
		Feeinstallmentwrapper updateFeeinstallment = theStudentwrapper.getTheFeeinstallmentwrapper();
		Feetable theFeetable = myStudent.getFeetable();
		List<Feeinstallment> theFeeinstallments = theFeetable.getFeeinstallments();
		boolean updateFeeInstallment = false;
		for (Feeinstallment theFeeinstallment : theFeeinstallments) {
			if (theFeeinstallment.getId() == updateFeeinstallment.getId()) {
				theFeeinstallment.setAmount(updateFeeinstallment.getAmount());
				theFeeinstallment.setDate_of_day(updateFeeinstallment.getDate_of_day());
				updateFeeInstallment = true;
				break;
			}
		}
		if (false == updateFeeInstallment) {
			Feeinstallment newFeeinstallment = new Feeinstallment();
			newFeeinstallment.setId(updateFeeinstallment.getId());
			newFeeinstallment.setAmount(updateFeeinstallment.getAmount());
			newFeeinstallment.setDate_of_day(updateFeeinstallment.getDate_of_day());
			theFeeinstallments.add(newFeeinstallment);
		}
		studentService2.saveStudent(myStudent);
		Studentwrapper updatedStudentwrapper = studentService.getStudent(theId);
		theModel.addAttribute("studentwrapper", updatedStudentwrapper);
		return "fee-table";
	}

	@GetMapping("/showFormForUpdateFeeinstallment")
	public String showFormForUpdateFeeinstallment(@RequestParam("studentwrapperId") int theId,
			@RequestParam("feeinstallmentId") int thefeeinstallmentId, Model theModel) {
		// get the customer from our service
		Student myStudent = studentService2.getStudent(theId);
		Feetable theFeetable = myStudent.getFeetable();
		Studentwrapper theStudentwrapper = studentService.getStudent(theId);
		List<Feeinstallment> theFeeinstallments = theFeetable.getFeeinstallments();
		for (Feeinstallment theFeeinstallment : theFeeinstallments) {
			if (thefeeinstallmentId == theFeeinstallment.getId()) {
				Feeinstallmentwrapper theFeeinstallmentwrapper = new Feeinstallmentwrapper(theFeeinstallment, theId);
				theStudentwrapper.setTheFeeinstallmentwrapper(theFeeinstallmentwrapper);
				break;
			}
		}
		// theStudentwrapper.loadIntempFeeinstallment(feeinstallmentId);
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("studentwrapper", theStudentwrapper);
		// theModel.addAttribute("Std", theCourse.getStandard());

		// send over to our form
		return "fee-installment";
	}

	@GetMapping("/deleteFeeInstallment")
	public String deleteFeeInstallment(@RequestParam("studentwrapperId") int theId,
			@RequestParam("feeinstallmentId") int thefeeinstallmentId, Model theModel) {

		Student myStudent = studentService2.getStudent(theId);
		Feetable theFeetable = myStudent.getFeetable();
		List<Feeinstallment> theFeeinstallments = theFeetable.getFeeinstallments();
		for (Feeinstallment theFeeinstallment : theFeeinstallments) {
			if (thefeeinstallmentId == theFeeinstallment.getId()) {
				theFeeinstallments.remove(theFeeinstallment);
				break;
			}
		}
		studentService2.saveStudent(myStudent);
		Studentwrapper updatedStudentwrapper = studentService.getStudent(theId);
		theModel.addAttribute("studentwrapper", updatedStudentwrapper);
		return "fee-table";
	}

	@ModelAttribute("courseList")
	public List<String> getcourseList() {

		List<String> courseList = new ArrayList<String>();
		// if (null == courses)
		courses = courseService.getCourses();
		for (Course theCourse : courses) {
			String StName = theCourse.getTitle();
			/*
			 * List<Standard> standards = theCourse.getStandards(); for
			 * (Standard theStandard : standards) { StName = StName + " " +
			 * theStandard.getName() + " " + theStandard.getBatch(); }
			 */ courseList.add(StName);
		}
		return courseList;
	}
	
	
	@GetMapping("/showVideo")
	public String showVideo2(@RequestParam("studentId") int theId, @RequestParam("VideoinfoId") int theVideoId,Model theModel) {
		Student myStudent = studentService2.getStudent(theId);
		List<Course> courses = myStudent.getCourses();
		List<Subject> subjects = new ArrayList<Subject>();
		for (Course theCourse : courses) {
			List<Subject> mySubjects = theCourse.getSubjects();
			subjects.addAll(mySubjects);
		}
		List<Videoinfo> videoinfolist = new ArrayList<Videoinfo>();
		for (Subject theSubject : subjects) {
			List<Videoinfo> myVideoinfolist = theSubject.getListOfVideos();
			videoinfolist.addAll(myVideoinfolist);
		}
		
		String fileName = "";
		
		for (Videoinfo theVideoinfo : videoinfolist) {
			if(theVideoId == theVideoinfo.getId()){
				fileName = theVideoinfo.getName();
			}
		}
		
		
		theModel.addAttribute("fileName", fileName);
		return "video";
	}
	
	@GetMapping("/video")
	public String showVideo(@RequestParam("studentId") int theId, Model theModel) {
		String fileName = "movie";
		theModel.addAttribute("fileName", fileName);
		return "video";
	}

}
