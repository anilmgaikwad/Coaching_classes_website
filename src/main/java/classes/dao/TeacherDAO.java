package classes.dao;

import java.util.List;

import classes.entity.Teacher;

public interface TeacherDAO {
	public List<Teacher> getTeachers();

	public void saveTeacher(Teacher theTeacher);

	public Teacher getTeacher(int theId);

	public void deleteTeacher(int theId);

	public List<Teacher> searchTeachers(String theSearchName);
}
