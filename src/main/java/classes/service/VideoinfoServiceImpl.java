package classes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import classes.dao.VideoinfoDAO;
import classes.entity.Videoinfo;

@Service
public class VideoinfoServiceImpl implements VideoinfoService {

	@Autowired
	private VideoinfoDAO videoinfoDAO;
	
	@Override
	@Transactional
	public List<Videoinfo> getVideoinfos() {
		// TODO Auto-generated method stub
		return videoinfoDAO.getVideoinfos();
	}

	@Override
	@Transactional
	public void saveVideoinfo(Videoinfo theVideoinfo) {
		// TODO Auto-generated method stub
		videoinfoDAO.saveVideoinfo(theVideoinfo);
	}

	@Override
	@Transactional
	public Videoinfo getVideoinfo(int theId) {
		// TODO Auto-generated method stub
		return videoinfoDAO.getVideoinfo(theId);
	}

	@Override
	@Transactional
	public void deleteVideoinfo(int theId) {
		// TODO Auto-generated method stub
		videoinfoDAO.deleteVideoinfo(theId);
	}

	@Override
	@Transactional
	public List<Videoinfo> searchVideoinfos(String theSearchName) {
		// TODO Auto-generated method stub
		return videoinfoDAO.searchVideoinfos(theSearchName);
	}

}
