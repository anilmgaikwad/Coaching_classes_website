package classes.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import classes.dao.PresentyrecordDAO;
import classes.entity.Presentyrecord;

@Service
public class PrsesntyrecordServiceImpl implements PresentyrecordService {

	@Autowired
	private PresentyrecordDAO presentyrecordDAO;
	
	@Override
	@Transactional
	public List<Presentyrecord> getPresentyrecords() {
		// TODO Auto-generated method stub
		return presentyrecordDAO.getPresentyrecords();
	}

	@Override
	@Transactional
	public void savePresentyrecord(Presentyrecord thePresentyrecord) {
		// TODO Auto-generated method stub
		presentyrecordDAO.savePresentyrecord(thePresentyrecord);
	}

	@Override
	@Transactional
	public Presentyrecord getPresentyrecord(int theId) {
		// TODO Auto-generated method stub
		return presentyrecordDAO.getPresentyrecord(theId);
	}

	@Override
	@Transactional
	public void deletePresentyrecord(int theId) {
		// TODO Auto-generated method stub
		presentyrecordDAO.deletePresentyrecord(theId);
	}

	@Override
	@Transactional
	public List<Presentyrecord> searchPresentyrecords(String theSearchName) {
		// TODO Auto-generated method stub
		return presentyrecordDAO.searchPresentyrecords(theSearchName);
	}

}
