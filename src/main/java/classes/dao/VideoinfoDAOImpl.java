package classes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import classes.entity.Videoinfo;

@Repository
public class VideoinfoDAOImpl implements VideoinfoDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Videoinfo> getVideoinfos() {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Videoinfo> theQuery = 
				currentSession.createQuery("from Videoinfo order by title",Videoinfo.class);

		List<Videoinfo> videoinfos = theQuery.getResultList();

		return videoinfos;
	}

	@Override
	public void saveVideoinfo(Videoinfo theVideoinfo) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theVideoinfo);
	}

	@Override
	public Videoinfo getVideoinfo(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Videoinfo theVideoinfo = currentSession.get(Videoinfo.class, theId);

		return theVideoinfo;
	}

	@Override
	public void deleteVideoinfo(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		/*Query theQuery = 
				currentSession.createQuery("delete from Videoinfo where id=:VideoinfoId");

		theQuery.setParameter("VideoinfoId", theId);
		theQuery.executeUpdate();*/
		
		Videoinfo theVideoinfo = currentSession.get(Videoinfo.class, theId);
		currentSession.delete(theVideoinfo);
	}

	@Override
	public List<Videoinfo> searchVideoinfos(String theSearchName) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Videoinfo> theQuery = null;

		//
		// only search by name if theSearchName is not empty
		//
		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			theQuery =currentSession.createQuery("from Videoinfo where lower(name) like :theName", Videoinfo.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

		}
		else {
			// theSearchName is empty ... so just get all customers
			theQuery =currentSession.createQuery("from Videoinfo", Videoinfo.class);			
		}

		// execute query and get result list
		List<Videoinfo> videoinfos = theQuery.getResultList();

		// return the results		
		return videoinfos;
	}

}
