package classes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import classes.dao.CourseDAO;
import classes.entity.Course;

@Service
public class CourseServiceImpl implements CourseService {

	
	@Autowired
	private CourseDAO courseDAO;
	
	@Override
	@Transactional
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		return courseDAO.getCourses();
	}

	@Override
	@Transactional
	public void saveCourse(Course theCourse) {
		// TODO Auto-generated method stub
		courseDAO.saveCourse(theCourse);
	}

	@Override
	@Transactional
	public Course getCourse(int theId) {
		// TODO Auto-generated method stub
		return courseDAO.getCourse(theId);
	}

	@Override
	@Transactional
	public void deleteCourse(int theId) {
		// TODO Auto-generated method stub
		courseDAO.deleteCourse(theId);
	}

	@Override
	@Transactional
	public List<Course> searchCourses(String theSearchName) {
		// TODO Auto-generated method stub
		return courseDAO.searchCourses(theSearchName);
	}

}
