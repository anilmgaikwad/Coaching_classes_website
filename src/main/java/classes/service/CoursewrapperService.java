package classes.service;

import java.util.List;

import classes.entity.Course;
import classes.wrapper.Coursewrapper;

public interface CoursewrapperService {
	
	public List<Coursewrapper> getCoursewrappers();
	public List<Course> getCourses();

	public void saveCourse(Coursewrapper theCourse);

	public Coursewrapper getCourse(int theId);

	public void deleteCourse(int theId);

	public List<Coursewrapper> searchCourses(String theSearchName);

}
