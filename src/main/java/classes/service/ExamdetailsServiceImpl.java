package classes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import classes.dao.ExamdetailsDAO;
import classes.entity.Examdetails;

@Service
public class ExamdetailsServiceImpl implements ExamdetailsService {

	@Autowired
	private ExamdetailsDAO examdetailsDAO;
	
	@Override
	@Transactional
	public List<Examdetails> getExamdetailslist() {
		// TODO Auto-generated method stub
		return examdetailsDAO.getExamdetails();
	}

	@Override
	@Transactional
	public void saveExamdetails(Examdetails theExamdetails) {
		// TODO Auto-generated method stub
		examdetailsDAO.saveExamdetails(theExamdetails);
	}

	@Override
	@Transactional
	public Examdetails getExamdetails(int theId) {
		// TODO Auto-generated method stub
		return examdetailsDAO.getExamdetails(theId);
	}

	@Override
	@Transactional
	public void deleteExamdetails(int theId) {
		// TODO Auto-generated method stub
		examdetailsDAO.deleteExamdetails(theId);
	}

	@Override
	@Transactional
	public List<Examdetails> searchExamdetails(String theSearchName) {
		// TODO Auto-generated method stub
		return examdetailsDAO.searchExamdetails(theSearchName);
	}

}
