package classes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import classes.dao.ExamDAO;
import classes.entity.Exam;
import classes.service.ExamService;

@Controller
@RequestMapping("/exam")
public class ExamController {
	
	@Autowired
	private ExamService examService;
	
	@RequestMapping("/list")
	public String listExams(Model theModel){
		
		List<Exam> exams = examService.getExams();
		
		theModel.addAttribute("exams", exams);
		
		return "list-exams";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		Exam theExam = new Exam();
		theModel.addAttribute("exam", theExam);
		return "exam-form";
	}
	
	@PostMapping("/saveExam")
	public String saveExam(@ModelAttribute("exam") Exam theExam){
	
		examService.saveExam(theExam);
		return "redirect:/exam/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("examId") int theId,
									Model theModel) {
		
		// get the customer from our service
		Exam theExam = examService.getExam(theId);	
		
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("exam", theExam);
		
		// send over to our form		
		return "exam-form";
	}
	
	@GetMapping("/delete")
	public String deleteExam(@RequestParam("examId") int theId) {
		
		examService.deleteExam(theId);
		return "redirect:/exam/list";
	}
	
	@GetMapping("/search")
	public String searchExams(@RequestParam("theSearchName") String theSearchName,
									Model theModel) {

		// search customers from the service
		List<Exam> exams = examService.searchExams(theSearchName);
				
		// add the customers to the model
		theModel.addAttribute("exams", exams);

		return "list-exams";		
	}
}
