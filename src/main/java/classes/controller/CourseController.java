package classes.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import classes.entity.Standard;
import classes.entity.Subject;
import classes.service.CourseService;
import classes.service.CoursewrapperService;
import classes.service.StandardService;
import classes.wrapper.Coursewrapper;

@Controller
@RequestMapping("/coursewrapper")
public class CourseController {

	@Autowired
	private CoursewrapperService courseService;
	
	@Autowired
	private StandardService standardService;
	
	private String selectedStandard;
	
	private List<Standard> standards;
	
	public String getSelectedStandard() {
		return selectedStandard;
	}

	public void setSelectedStandard(String selectedStandard) {
		this.selectedStandard = selectedStandard;
	}

	@RequestMapping("/list")
	public String listCourses(Model theModel){
		
		List<Coursewrapper> courses = courseService.getCoursewrappers();
		
		theModel.addAttribute("courses", courses);
		
		return "list-courses";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		Coursewrapper theCoursewrapper = new Coursewrapper();
		theCoursewrapper.setStandards(standardService.getStandards());
		theModel.addAttribute("coursewrapper", theCoursewrapper);
		// add the country options to the model 
		
		return "course-form";
	}
	
	@PostMapping("/saveCourse")
	public String saveCourse(@ModelAttribute("coursewrapper") Coursewrapper theCoursewrapper,@RequestParam("standardNames") String[] standardNameList)
	{
		if (null == standards)
			standards = standardService.getStandards();
		//String StName2 = standardNameList[0];
		List<Standard> theTempStandards = new ArrayList<Standard>();
		theCoursewrapper.setStandards(theTempStandards);
		for(String StName2:standardNameList)
		{
		
		for (Standard theStandard : standards) 
		{
			String StName = theStandard.getName() + " " + theStandard.getBatch();
			
			if(true == StName.equals(StName2) )
			{
				theCoursewrapper.getStandards().add(theStandard);

			}
		}
		}
		courseService.saveCourse(theCoursewrapper);
		return "redirect:/coursewrapper/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("courseId") int theId,
									Model theModel) {
		
		// get the customer from our service
		Coursewrapper theCoursewrapper = courseService.getCourse(theId);	
		
		// set customer as a model attribute to pre-populate the form
		theModel.addAttribute("coursewrapper", theCoursewrapper);
		//theModel.addAttribute("Std", theCourse.getStandard());
		
		// send over to our form		
		return "course-form";
	}
	
	@GetMapping("/delete")
	public String deleteCourse(@RequestParam("courseId") int theId) {
		
		courseService.deleteCourse(theId);
		return "redirect:/coursewrapper/list";
	}
	
	@GetMapping("/search")
	public String searchCourses(@RequestParam("theSearchName") String theSearchName,
									Model theModel) {

		// search customers from the service
		List<Coursewrapper> courses = courseService.searchCourses(theSearchName);
				
		// add the customers to the model
		theModel.addAttribute("courses", courses);

		return "list-courses";		
	}
	
	/* @ModelAttribute("standardList")
	 public List<Standard > getstandardList() {
		 
		 List<Standard> standardList = new ArrayList<Standard>();
		 if(null == standards)
		 standards = standardService.getStandards();
	      for (Standard theStandard:standards)
	      {
	    	  String StName = theStandard.getName()+ " "+ theStandard.getDivision();
	    	  standardList.add(theStandard);
	      }
	      return standardList;
	   }*/

	/*@ModelAttribute("standardList")
	 public LinkedHashMap<Standard, String> getstandardList() {
		LinkedHashMap<Standard, String> standardList = new LinkedHashMap<Standard, String>();
	      if(null == standards)
	 		 standards = standardService.getStandards();
	 	      for (Standard theStandard:standards)
	 	      {
	 	    	  String StName = theStandard.getName()+ " "+ theStandard.getDivision();
	 	    	  standardList.put(theStandard,StName);
	 	      }
	 	      return standardList;
	      
	   }*/
	
	 @ModelAttribute("standardList")
	 public List<String > getstandardList() {
		 
		 List<String> standardList = new ArrayList<String>();
		// if(null == standards)
		 standards = standardService.getStandards();
	      for (Standard theStandard:standards)
	      {
	    	  String StName = theStandard.getName()+ " "+ theStandard.getBatch();
	    	  standardList.add(StName);
	      }
	      return standardList;
	   }

}
