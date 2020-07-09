package classes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import classes.dao.PresentyDAO;
import classes.entity.Presenty;
@Service
public class PresentyServiceImpl implements PresentyService {

	@Autowired
	private PresentyDAO presentyDAO;
	
	@Override
	@Transactional
	public List<Presenty> getPresenties() {
		// TODO Auto-generated method stub
		return presentyDAO.getPresenties();
	}

	@Override
	@Transactional
	public void savePresenty(Presenty thePresenty) {
		// TODO Auto-generated method stub
		presentyDAO.savePresenty(thePresenty);
	}

	@Override
	@Transactional
	public Presenty getPresenty(int theId) {
		// TODO Auto-generated method stub
		return presentyDAO.getPresenty(theId);
	}

	@Override
	@Transactional
	public void deletePresenty(int theId) {
		// TODO Auto-generated method stub
		presentyDAO.deletePresenty(theId);
	}

	@Override
	@Transactional
	public List<Presenty> searchPresenties(String theSearchName) {
		// TODO Auto-generated method stub
		return presentyDAO.searchPresenties(theSearchName);
	}

}
