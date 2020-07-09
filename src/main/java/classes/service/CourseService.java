package classes.service;

import java.util.List;

import classes.entity.Course;

public interface CourseService {
	public List<Course> getCourses();

	public void saveCourse(Course theCourse);

	public Course getCourse(int theId);

	public void deleteCourse(int theId);

	public List<Course> searchCourses(String theSearchName);
}
