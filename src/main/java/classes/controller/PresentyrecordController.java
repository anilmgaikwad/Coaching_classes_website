package classes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import classes.entity.Course;
import classes.entity.Presenty;
import classes.entity.Presentyrecord;
import classes.entity.Standard;
import classes.entity.Student;
import classes.entity.Subject;
import classes.entity.Teacher;
import classes.service.PresentyService;
import classes.service.PresentyrecordService;
import classes.service.PresentyrecordwrapperService;
import classes.service.SubjectService;
import classes.service.SubjectwrapperService;
import classes.wrapper.Coursewrapper;
import classes.wrapper.Presentyrecordwrapper;
import classes.wrapper.Subjectwrapper;

@Controller
@RequestMapping("/presentyrecordwrapper")
public class PresentyrecordController {

	@Autowired
	private PresentyrecordwrapperService presentyrecordwrapperService;

	@Autowired
	private PresentyrecordService presentyrecordService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private SubjectwrapperService subjectServicewrapper;

	List<Subject> subjects;

	@RequestMapping("/list")
	public String listPresentyrecords(Model theModel) {

		List<Presentyrecordwrapper> presentyrecordwrappers = presentyrecordwrapperService.getPresentyrecordwrappers();
		theModel.addAttribute("presentyRecords", presentyrecordwrappers);

		return "found-list-Presentyrecords";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(@RequestParam("subjectwrapperId") int theId, Model theModel) {
		Subject theSubject = subjectService.getSubject(theId);
		Presentyrecordwrapper thepresentyrecordwrapper = new Presentyrecordwrapper(theSubject);
		theSubject.addPresentyrecord(thepresentyrecordwrapper.getThePresentyrecord());
		subjectService.saveSubject(theSubject);
		// presentyrecordwrapperService.savePresentyrecordwrapper(thepresentyrecordwrapper);
		Subjectwrapper theSubjectwrapper = subjectServicewrapper.getSubject(theId);
		theModel.addAttribute("subjectwrapper", theSubjectwrapper);

		// send over to our form
		return "list-presentyRecords";
	}

	@GetMapping("/delete")
	public String deleteExam(@RequestParam("presentyRecordId") int theId, @RequestParam("subjectID") int theSubjectId,
			Model theModel) {
		Subject theSubject = subjectService.getSubject(theSubjectId);
		theSubject.deletePresentyrecord(theId);
		subjectService.saveSubject(theSubject);
		presentyrecordService.deletePresentyrecord(theId);
		// presentyrecordwrapperService.deletePresentyrecordwrapper(theId);
		Subjectwrapper theSubjectwrapper = subjectServicewrapper.getSubject(theSubjectId);
		theModel.addAttribute("subjectwrapper", theSubjectwrapper);
		return "list-presentyRecords";

	}

	@GetMapping("/search")
	public String searchExams(@RequestParam("theSearchName") String theSearchName, Model theModel) {

		// search customers from the service
		List<Presentyrecordwrapper> presentyrecordwrappers = presentyrecordwrapperService
				.searchPresentyrecordwrappers(theSearchName);

		// add the customers to the model
		theModel.addAttribute("presentyRecords", presentyrecordwrappers);

		return "found-list-Presentyrecords";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("presentyRecordId") int theId,
			@RequestParam("subjectID") int theSubjectwrapperId, Model theModel) {

		Presentyrecordwrapper thePresentyrecordwrapper = presentyrecordwrapperService.getPresentyrecordwrapper(theId);
		Subjectwrapper theSubjectwrapper = subjectServicewrapper.getSubject(theSubjectwrapperId);
		if (null != thePresentyrecordwrapper) {
			thePresentyrecordwrapper.buildOtherDataWithSubjectwrapper(theSubjectwrapper, false);
			theModel.addAttribute("presentyrecordwrapper", thePresentyrecordwrapper);
			return "Presentyrecord-form";
		} else
			return null;

	}

	@PostMapping("/savePresentyrecord")
	public String savePresentyrecord(
			@ModelAttribute("presentyrecordwrapper") Presentyrecordwrapper thePresentyrecordwrapper,
			@RequestParam(value = "id", required = false) int theId,
			@RequestParam(value = "subjectId", required = false) int theSubjectId,
			@RequestParam(required = false, value = "presentStudentList", defaultValue = "{}") String[] presentStudentList,
			Model theModel) {
		// List<Presenty> presenties = new ArrayList<Presenty>();

		Subject theSubject = subjectService.getSubject(theSubjectId);
		Presentyrecordwrapper myPresentyrecordwrapper = presentyrecordwrapperService.getPresentyrecordwrapper(theId);
		myPresentyrecordwrapper.buildOtherDataWithSubject(theSubject, false);
		Map<String, Student> studentMap = myPresentyrecordwrapper.getStudentMap();

		List<Presentyrecord> presentyrecords = theSubject.getPresentyrecords();
		for (Presentyrecord presentyrecord : presentyrecords) {
			if (presentyrecord.getId() == theId) {
				presentyrecord.setDate_of_day(thePresentyrecordwrapper.getDate_of_day());
				List<Presenty> presenties = presentyrecord.getPresenties();
				for (Presenty thePresenty : presenties) {
					for (String studentName : presentStudentList) {
						Student theStudent = studentMap.get(studentName);
						if ((null != theStudent)&&(thePresenty.getStudent_id() == theStudent.getId())){
							thePresenty.setStatus(true);
							break;
						}
						else
							thePresenty.setStatus(false);
					}
				}
			}
		}
		subjectService.saveSubject(theSubject);
		// Presentyrecordwrapper myPresentyrecordwrapper =
		// presentyrecordwrapperService.getPresentyrecordwrapper(theId);

		// myPresentyrecordwrapper.setPresenties(presenties);
		Subjectwrapper theSubjectwrapper = subjectServicewrapper.getSubject(theSubjectId);
		/*
		 * List<Presentyrecordwrapper> presentyrecordwrappers =
		 * theSubjectwrapper.getPresentyrecordwrappers();
		 * for(Presentyrecordwrapper
		 * myPresentyrecordwrapper:presentyrecordwrappers) {
		 * if(myPresentyrecordwrapper.getId() ==
		 * thePresentyrecordwrapper.getId()) {
		 * myPresentyrecordwrapper.buildOtherDataWithSubjectwrapper(
		 * theSubjectwrapper, false);
		 * myPresentyrecordwrapper.setDate_of_day(thePresentyrecordwrapper.
		 * getDate_of_day());
		 * myPresentyrecordwrapper.setPresentiesOfStudent(presentStudentList); }
		 * }
		 */
		// subjectServicewrapper.saveSubject(theSubjectwrapper);

		// presentyrecordwrapperService.savePresentyrecordwrapper(myPresentyrecordwrapper);

		theModel.addAttribute("subjectwrapper", theSubjectwrapper);

		return "list-presentyRecords";

	}

	@ModelAttribute("subjectList")
	public List<String> getsubjectList() {

		List<String> subjectList = new ArrayList<String>();
		if (null == subjects)
			subjects = subjectService.getSubjects();
		for (Subject theSubject : subjects) {
			String subName = theSubject.getName();
			List<Course> courses = theSubject.getCourses();
			for (Course theCourse : courses) {
				List<Standard> standards = theCourse.getStandards();
				for (Standard theStandard : standards) {
					subName = subName + " " + theStandard.getName();
					subjectList.add(subName);
					break;
				}

			}
		}
		return subjectList;
	}

	@ModelAttribute("studentList")
	public List<String> getStudentList() {
		List<String> studentList = new ArrayList<String>();

		return studentList;
	}
}
