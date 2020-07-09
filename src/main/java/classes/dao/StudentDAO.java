package classes.dao;

import java.util.List;

import classes.entity.Student;

public interface StudentDAO {
	public List<Student> getStudents();

	public void saveStudent(Student theStudent);

	public Student getStudent(int theId);

	public void deleteStudent(int theId);

	public List<Student> searchStudents(String theSearchName);
}
