package classes.service;

import java.util.List;

import classes.entity.Teacher;
import classes.wrapper.Teacherwrapper;

public interface TeacherwrapperService {
	public List<Teacherwrapper> getTeacherwrappers();
	public List<Teacher> getTeachers();

	public void saveTeacher(Teacherwrapper theTeacher);

	public Teacherwrapper getTeacher(int theId);

	public void deleteTeacher(int theId);

	public List<Teacherwrapper> searchTeachers(String theSearchName);
}
