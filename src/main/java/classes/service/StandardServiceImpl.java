package classes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import classes.dao.ExamDAO;
import classes.dao.StandardDAO;
import classes.entity.Standard;

@Service
public class StandardServiceImpl implements StandardService {

	@Autowired
	private StandardDAO standardDAO;
	
	@Override
	@Transactional
	public List<Standard> getStandards() {
		// TODO Auto-generated method stub
		return standardDAO.getStandards();
	}

	@Override
	@Transactional
	public void saveStandard(Standard theStandard) {
		// TODO Auto-generated method stub
		standardDAO.saveStandard(theStandard);
	}

	@Override
	@Transactional
	public Standard getStandard(int theId) {
		// TODO Auto-generated method stub
		return standardDAO.getStandard(theId);
	}

	@Override
	@Transactional
	public void deleteStandard(int theId) {
		// TODO Auto-generated method stub
		standardDAO.deleteStandard(theId);
	}

	@Override
	@Transactional
	public List<Standard> searchStandards(String theSearchName) {
		// TODO Auto-generated method stub
		return standardDAO.searchStandards(theSearchName);
	}

}
