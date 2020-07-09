package classes.dao;

import java.util.List;

import classes.entity.Examdetails;

public interface ExamdetailsDAO {
	public List<Examdetails> getExamdetails();

	public void saveExamdetails(Examdetails theExamdetails);

	public Examdetails getExamdetails(int theId);

	public void deleteExamdetails(int theId);

	public List<Examdetails> searchExamdetails(String theSearchName);
}
