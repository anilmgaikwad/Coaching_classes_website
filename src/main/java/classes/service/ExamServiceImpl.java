package classes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import classes.dao.ExamDAO;
import classes.entity.Exam;

@Service
public class ExamServiceImpl implements ExamService {

	@Autowired
	private ExamDAO examDAO;
	
	@Override
	@Transactional
	public List<Exam> getExams() {
		// TODO Auto-generated method stub
		return examDAO.getExams();
	}

	@Override
	@Transactional
	public void saveExam(Exam theExam) {
		// TODO Auto-generated method stub
		examDAO.saveExam(theExam);
	}

	@Override
	@Transactional
	public Exam getExam(int theId) {
		// TODO Auto-generated method stub
		return examDAO.getExam(theId);
	}

	@Override
	@Transactional
	public void deleteExam(int theId) {
		// TODO Auto-generated method stub
	 examDAO.deleteExam(theId);
	}

	@Override
	@Transactional
	public List<Exam> searchExams(String theSearchName) {
		// TODO Auto-generated method stub
		return examDAO.searchExams(theSearchName);
		//return examDAO.getExams();
	}

}
