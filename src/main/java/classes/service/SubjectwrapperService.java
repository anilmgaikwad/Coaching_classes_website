package classes.service;

import java.util.List;

import classes.wrapper.Subjectwrapper;

public interface SubjectwrapperService {
	public List<Subjectwrapper> getSubjects();

	public void saveSubject(Subjectwrapper theSubject);

	public Subjectwrapper getSubject(int theId);

	public void deleteSubject(int theId);

	public List<Subjectwrapper> searchSubjects(String theSearchName);
}
