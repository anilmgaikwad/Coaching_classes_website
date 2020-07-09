package classes.service;

import java.util.List;

import classes.wrapper.Studentwrapper;

public interface StudentwrapperService {
	
	public List<Studentwrapper> getStudents();

	public void saveStudent(Studentwrapper theStudent);

	public Studentwrapper getStudent(int theId);

	public void deleteStudent(int theId);

	public List<Studentwrapper> searchStudents(String theSearchName);

}
