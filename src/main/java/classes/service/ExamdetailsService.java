package classes.service;

import java.util.List;

import classes.entity.Examdetails;

public interface ExamdetailsService {
	public List<Examdetails> getExamdetailslist();

	public void saveExamdetails(Examdetails theExamdetails);

	public Examdetails getExamdetails(int theId);

	public void deleteExamdetails(int theId);

	public List<Examdetails> searchExamdetails(String theSearchName);
}
