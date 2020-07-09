package classes.service;

import java.util.List;

import classes.entity.Examresult;

public interface ExamresultService {
	public List<Examresult> getExamresults();

	public void saveExamresult(Examresult theExamresult);

	public Examresult getExamresult(int theId);

	public void deleteExamresult(int theId);

	public List<Examresult> searchExamresults(String theSearchName);

}
