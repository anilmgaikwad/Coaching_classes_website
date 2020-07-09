package classes.dao;

import java.util.List;

import classes.entity.Exam;

public interface ExamDAO {
	
	public List<Exam> getExams();

	public void saveExam(Exam theExam);

	public Exam getExam(int theId);

	public void deleteExam(int theId);

	public List<Exam> searchExams(String theSearchName);

	

}
