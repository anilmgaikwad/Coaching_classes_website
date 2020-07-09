package classes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import classes.SMS.SMSData;

import classes.entity.Course;
import classes.entity.Exam;
import classes.entity.Examdetails;
import classes.entity.Presentyrecord;
import classes.entity.Standard;
import classes.entity.Student;
import classes.entity.Subject;
import classes.entity.User;
import classes.service.ExamService;
import classes.service.ExamdetailsService;
import classes.service.PresentyrecordService;
import classes.service.SMSService;
import classes.service.StandardService;
import classes.service.StudentService;
import classes.service.SubjectService;
import classes.service.UserService;
import classes.wrapper.MarkSheet;
import classes.wrapper.Studentwrapper;
import classes.wrapper.StandardExamPair;

import java.util.regex.*;
import java.util.Scanner;

@Controller
@RequestMapping("/standard")
public class StandardController {

	@Autowired
	private StandardService standardService;

	@Autowired
	private ExamService examService;

	@Autowired
	private SMSService smsService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private PresentyrecordService presentyrecordService;

	@Autowired
	private ExamdetailsService examdetailsService;
	
	@Autowired
	private UserService userService;

	@RequestMapping("/list")
	public String listExams(Model theModel) {

		List<Standard> standards = standardService.getStandards();

		theModel.addAttribute("standards", standards);

		return "list-standard";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Standard theStandard = new Standard();
		theModel.addAttribute("standard", theStandard);
		return "standard-form";
	}

	@PostMapping("/saveStandard")
	public String saveStandard(@ModelAttribute("standard") Standard theStandard) {

		standardService.saveStandard(theStandard);
		return "redirect:/standard/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("standardId") int theId, Model theModel) {

		// get the customer from our service
		Standard theStandard = standardService.getStandard(theId);

		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("standard", theStandard);

		// send over to our form
		return "standard-form";
	}

	@GetMapping("/delete")
	public String deleteExam(@RequestParam("standardId") int theId) {

		
		standardService.deleteStandard(theId);
		return "redirect:/standard/list";
	}
	
	@GetMapping("/deleteAllStudents")
	public String deleteAllStudents(@RequestParam("standardId") int theId) {

		Standard theStandard = standardService.getStandard(theId);
		if (null != theStandard) {
			List<Course> courses = theStandard.getCourses();
			List<Student> allStudents = new ArrayList<Student>();
			List<Subject> allSubjects = new ArrayList<Subject>();
			for (Course theCourse : courses) {
				allStudents.addAll(theCourse.getStudents());
				allSubjects.addAll(theCourse.getSubjects());
			}

			for (Subject theSubject : allSubjects) 
			{
				List<Presentyrecord> allPresentyrecord = new ArrayList<Presentyrecord>();
				List<Examdetails> allExamdetails = new ArrayList<Examdetails>();
				allPresentyrecord.addAll(theSubject.getPresentyrecords());
				allExamdetails.addAll(theSubject.getExamdetails());
				
				int [] preRecordIdArr = new int[allPresentyrecord.size()];
				int [] examDetailsIdArr = new int[allExamdetails.size()];
				
				for (int i=0;i<allExamdetails.size();i++) {
					Examdetails theExamdetails = allExamdetails.get(i);
					examDetailsIdArr[i] = theExamdetails.getId();
					theSubject.deleteExamDetails(theExamdetails.getId());
				
				}
				

				for ( int i=0;i<allPresentyrecord.size();i++) {
					Presentyrecord thePresentyrecord = allPresentyrecord.get(i);
					preRecordIdArr[i] = thePresentyrecord.getId();
					theSubject.deletePresentyrecord(thePresentyrecord.getId());
			
				}
				subjectService.saveSubject(theSubject);
				
				for (int i=0;i<allExamdetails.size();i++) {
					examdetailsService.deleteExamdetails(examDetailsIdArr[i]);
				}
				
				for ( int i=0;i<allPresentyrecord.size();i++) {
					presentyrecordService.deletePresentyrecord(preRecordIdArr[i]);
				}
				

			}

			for (Student theStudent : allStudents) {
				User theUser = theStudent.getUser();
				studentService.deleteStudent(theStudent.getId());
				userService.deleteUser(theUser.getId());
			}
		}
		
		return "redirect:/standard/list";
	}

	@GetMapping("/search")
	public String searchExams(@RequestParam("theSearchName") String theSearchName, Model theModel) {

		// search customers from the service
		List<Standard> standards = standardService.searchStandards(theSearchName);

		// add the customers to the model
		theModel.addAttribute("standards", standards);

		return "list-standard";
	}

	@GetMapping("/publishExamResult")
	public String publishExamResult(@RequestParam("standardId") int theId, @RequestParam("examId") int theExamId,
			Model theModel) {

		// get the customer from our service
		Exam theExam = examService.getExam(theExamId);
		Standard theStandard = standardService.getStandard(theId);
		if ((null != theStandard) && (null != theExam)) {
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
				for (int index = 1; index < 3; index++) {
					String mobile_no = "";
					if (1 == index)
						mobile_no = theStudent.getMobile_number1();
					else
						mobile_no = theStudent.getMobile_number2();
					Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
					Matcher m = p.matcher(mobile_no);
					if ((m.find() && m.group().equals(mobile_no))) {
						MarkSheet theMarkSheet = new MarkSheet(theStudentWrapper, theExamId, theExam.getName());
						SMSData smsData = new SMSData();

						smsData.setMobileNumber(mobile_no);
						smsData.setMessageType("uni");
						String messageStr = "Name:" + theStudentWrapper.getFull_name() + " " + "\n";
						messageStr = messageStr + theExam.getName() + " Marks " + "\n";
						;
						List<String> row1 = theMarkSheet.getRow1();
						List<String> row2 = theMarkSheet.getRow2();
						List<String> row3 = theMarkSheet.getRow3();
						if ((row1.size() == row2.size()) && (row2.size() == row3.size())) {
							for (int i = 1; i < row1.size(); i++) {
								messageStr = messageStr + row1.get(i) + "(" + row2.get(i) + "/" + row3.get(i) + ")"
										+ " ";
							}
						}
						if (theMarkSheet.getRow4().size() > 0)
							messageStr = messageStr + " Result:"
									+ theMarkSheet.getRow4().get(theMarkSheet.getRow4().size() - 1);

						smsData.setMessage(messageStr);
						smsService.sendSMS(smsData);
					}
				}
			}
		}
		List<StandardExamPair> theStandardExamPairList = new ArrayList<StandardExamPair>();
		getStandardExamList(theStandardExamPairList);
		// add the customers to the model
		theModel.addAttribute("theStandardExamPairList", theStandardExamPairList);
		return "list-standard-exam";
	}

	@GetMapping("/standardExamList")
	public String standardExamList(Model theModel) {

		List<StandardExamPair> theStandardExamPairList = new ArrayList<StandardExamPair>();
		getStandardExamList(theStandardExamPairList);
		// add the customers to the model
		theModel.addAttribute("theStandardExamPairList", theStandardExamPairList);
		return "list-standard-exam";
	}

	public void getStandardExamList(List<StandardExamPair> theStandardExamPairList) {
		List<Standard> standards = standardService.getStandards();

		for (Standard theStandard : standards) {
			if (null != theStandard) {
				List<Integer> examIdList = new ArrayList<Integer>();
				List<Course> courses = theStandard.getCourses();
				List<Subject> subjects = new ArrayList<Subject>();
				for (Course theCourse : courses) {
					subjects.addAll(theCourse.getSubjects());
				}

				for (Subject theSubject : subjects) {
					List<Examdetails> examdetails = new ArrayList<Examdetails>();
					examdetails = theSubject.getExamdetails();
					for (Examdetails theExamdetails : examdetails) {
						int examId = theExamdetails.getExam_id();
						boolean bExamIdFound = false;
						for (Integer theExamId : examIdList) {
							if (examId == theExamId.intValue()) {
								bExamIdFound = true;
								break;
							}
						}
						if (!bExamIdFound)
							examIdList.add(new Integer(examId));
					}
				}
				for (Integer theExamId : examIdList) {
					StandardExamPair theStandardExamPair = new StandardExamPair();
					theStandardExamPair.setStandard(theStandard);
					theStandardExamPair.setExam(examService.getExam(theExamId.intValue()));
					theStandardExamPairList.add(theStandardExamPair);
				}
			}
		}
	}

	@ModelAttribute("filterList")
	public List<String> getFilterList() {

		List<String> filterList = new ArrayList<String>();
		filterList.add("Standard");
		filterList.add("Name");
		filterList.add("Roll Number");
		return filterList;
	}
}
