package classes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import classes.dao.PresentyDAO;
import classes.dao.PresentyrecordDAO;
import classes.entity.Presenty;
import classes.entity.Presentyrecord;
import classes.wrapper.Presentyrecordwrapper;

@Service
public class PresentyrecordwrapperServiceImpl implements PresentyrecordwrapperService {

	@Autowired
	private PresentyrecordDAO presentyrecordDAO;

	@Autowired
	private PresentyDAO presentyDAO;

	@Override
	@Transactional
	public List<Presentyrecordwrapper> getPresentyrecordwrappers() {
		// TODO Auto-generated method stub

		List<Presentyrecordwrapper> presentyRecordwrappers = new ArrayList<Presentyrecordwrapper>();
		List<Presentyrecord> presentyRecords = presentyrecordDAO.getPresentyrecords();
		for (Presentyrecord thePresentyrecord : presentyRecords) {
			Presentyrecordwrapper thePresentyrecordwrapper = new Presentyrecordwrapper(thePresentyrecord);
			presentyRecordwrappers.add(thePresentyrecordwrapper);
		}
		return presentyRecordwrappers;
	}

	@Override
	@Transactional
	public void savePresentyrecordwrapper(Presentyrecordwrapper thePresentyrecordwrapper) {
		// TODO Auto-generated method stub
		
		if (null != thePresentyrecordwrapper) {
			presentyrecordDAO.savePresentyrecord(thePresentyrecordwrapper.getThePresentyrecord());
			/*for (Presenty thePresenty : thePresentyrecordwrapper.getPresenties())
				presentyDAO.savePresenty(thePresenty);*/
		}
	}

	@Override
	@Transactional
	public Presentyrecordwrapper getPresentyrecordwrapper(int theId) {
		// TODO Auto-generated method stub
		Presentyrecord thePresentyrecord = presentyrecordDAO.getPresentyrecord(theId);
		if (null != thePresentyrecord) {
			Presentyrecordwrapper thePresentyrecordwrapper = new Presentyrecordwrapper(thePresentyrecord);
			return thePresentyrecordwrapper;
		} else
			return null;
	}
	
	@Override
	@Transactional
	public Presentyrecord getPresentyrecord(int theId) {
		// TODO Auto-generated method stub
		Presentyrecord thePresentyrecord = presentyrecordDAO.getPresentyrecord(theId);
			return thePresentyrecord;
	}

	@Override
	@Transactional
	public void deletePresentyrecordwrapper(int theId) {
		// TODO Auto-generated method stub
		presentyrecordDAO.deletePresentyrecord(theId);
	}

	@Override
	@Transactional
	public List<Presentyrecordwrapper> searchPresentyrecordwrappers(String theSearchName) {
		// TODO Auto-generated method stub
		List<Presentyrecordwrapper> presentyRecordwrappers = new ArrayList<Presentyrecordwrapper>();
		List<Presentyrecord> presentyRecords = presentyrecordDAO.searchPresentyrecords(theSearchName);
		if (null != presentyRecords) {
			for (Presentyrecord thePresentyrecord : presentyRecords) {
				Presentyrecordwrapper thePresentyrecordwrapper = new Presentyrecordwrapper(thePresentyrecord);
				presentyRecordwrappers.add(thePresentyrecordwrapper);
			}

		}
		return presentyRecordwrappers;
	}

}
