package classes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import classes.dao.ExamresultDAO;
import classes.entity.Examresult;

@Service
public class ExamresultServiceImpl implements ExamresultService {

	@Autowired
	private ExamresultDAO examresultDAO;
	
	@Override
	@Transactional
	public List<Examresult> getExamresults() {
		// TODO Auto-generated method stub
		return examresultDAO.getExamresults();
	}

	@Override
	@Transactional
	public void saveExamresult(Examresult theExamresult) {
		// TODO Auto-generated method stub
		examresultDAO.saveExamresult(theExamresult);
	}

	@Override
	@Transactional
	public Examresult getExamresult(int theId) {
		// TODO Auto-generated method stub
		return examresultDAO.getExamresult(theId);
	}

	@Override
	@Transactional
	public void deleteExamresult(int theId) {
		// TODO Auto-generated method stub
		examresultDAO.deleteExamresult(theId);
	}

	@Override
	@Transactional
	public List<Examresult> searchExamresults(String theSearchName) {
		// TODO Auto-generated method stub
		return examresultDAO.searchExamresults(theSearchName);
	}

}
