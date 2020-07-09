package classes.dao;

import java.util.List;

import classes.entity.Subject;

public interface SubjectDAO {
	
	public List<Subject> getSubjects();

	public void saveSubject(Subject theSubject);

	public Subject getSubject(int theId);

	public void deleteSubject(int theId);

	public List<Subject> searchSubjects(String theSearchName);

}
