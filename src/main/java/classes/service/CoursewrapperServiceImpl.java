package classes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import classes.dao.CourseDAO;
import classes.entity.Course;

import classes.wrapper.Coursewrapper;

@Service
public class CoursewrapperServiceImpl implements CoursewrapperService {
	
	@Autowired
	private CourseDAO courseDAO;

	@Override
	@Transactional
	public List<Coursewrapper> getCoursewrappers() {
		// TODO Auto-generated method stub
		List<Coursewrapper> coursewrappers = new ArrayList<Coursewrapper>();
		List<Course> courses = courseDAO.getCourses();
		for(Course theCourse:courses)
		{
			Coursewrapper theCourseWrapper = new Coursewrapper();
			theCourseWrapper.setId(theCourse.getId());
			theCourseWrapper.setFee(theCourse.getFee());
			theCourseWrapper.setTitle(theCourse.getTitle());
			theCourseWrapper.setStandards(theCourse.getStandards());
			/*if(null != theCourse.getStandard())
				theCourseWrapper.setStandardName(theCourse.getStandard().getName() + " "+ theCourse.getStandard().getDivision());*/
			coursewrappers.add(theCourseWrapper);
		}
		return coursewrappers;
	}
	
	@Override
	@Transactional
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		return courseDAO.getCourses();
	}

	@Override
	@Transactional
	public void saveCourse(Coursewrapper theCourseWrapper) {
		// TODO Auto-generated method stub
		int courseToModifyId = theCourseWrapper.getId();
		List<Course> courses = courseDAO.getCourses();
		Course foundCourse = null;
		for(Course theCourse:courses)
		{
			int courseId = theCourse.getId();
			if(courseToModifyId == courseId)
			{
				foundCourse = theCourse;
				break;
			}
		}
		if(null == foundCourse){
			foundCourse = new Course();
			foundCourse.setId(theCourseWrapper.getId());
		}
		
		foundCourse.setTitle(theCourseWrapper.getTitle()); 
		foundCourse.setStandards(theCourseWrapper.getStandards()); 
		foundCourse.setFee(theCourseWrapper.getFee()); 
		courseDAO.saveCourse(foundCourse);
	}

	@Override
	@Transactional
	public Coursewrapper getCourse(int theId) {
		// TODO Auto-generated method stub
		Course theCourse = courseDAO.getCourse(theId);
		Coursewrapper theCourseWrapper = new Coursewrapper();
		theCourseWrapper.setId(theCourse.getId());
		theCourseWrapper.setFee(theCourse.getFee());
		theCourseWrapper.setTitle(theCourse.getTitle());
		theCourseWrapper.setStandards(theCourse.getStandards());
/*		if(null != theCourse.getStandard()){
			theCourseWrapper.setStandardName(theCourse.getStandard().getName() + " "+ theCourse.getStandard().getDivision());
			theCourseWrapper.setStandard(theCourse.getStandard());
		}*/
			return theCourseWrapper;
	}

	@Override
	@Transactional
	public void deleteCourse(int theId) {
		// TODO Auto-generated method stub
		courseDAO.deleteCourse(theId);
	}

	@Override
	@Transactional
	public List<Coursewrapper> searchCourses(String theSearchName) {
		// TODO Auto-generated method stub
		List<Course> courses = courseDAO.searchCourses(theSearchName);
		List<Coursewrapper> coursewrappers = new ArrayList<Coursewrapper>();
		
		for(Course theCourse:courses)
		{
			Coursewrapper theCourseWrapper = new Coursewrapper(theCourse);
			coursewrappers.add(theCourseWrapper);
		}
		return coursewrappers;
	}

	

}
