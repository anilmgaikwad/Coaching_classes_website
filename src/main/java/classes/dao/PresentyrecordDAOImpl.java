package classes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import classes.entity.Presentyrecord;


@Repository
public class PresentyrecordDAOImpl implements PresentyrecordDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Presentyrecord> getPresentyrecords() {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Presentyrecord> theQuery = 
				currentSession.createQuery("from Presentyrecord order by id",Presentyrecord.class);

		List<Presentyrecord> presentyrecords = theQuery.getResultList();

		return presentyrecords;
	}

	@Override
	public void savePresentyrecord(Presentyrecord thePresentyrecord) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(thePresentyrecord);
	}

	@Override
	public Presentyrecord getPresentyrecord(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Presentyrecord thePresentyrecord = currentSession.get(Presentyrecord.class, theId);

		return thePresentyrecord;
	}

	@Override
	public void deletePresentyrecord(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		/*Query theQuery = 
				currentSession.createQuery("delete from Presentyrecord where id=:presentyrecordId");

		theQuery.setParameter("presentyrecordId", theId);
		theQuery.executeUpdate();*/
	
		Presentyrecord thePresentyrecord = currentSession.get(Presentyrecord.class, theId);
		currentSession.delete(thePresentyrecord);
	}

	@Override
	public List<Presentyrecord> searchPresentyrecords(String theSearchName) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Presentyrecord> theQuery = null;

		//
		// only search by name if theSearchName is not empty
		//
		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			theQuery =currentSession.createQuery("from Presentyrecord where lower(date_of_day) like :theName or lower(subject_id) like :theName", Presentyrecord.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
			
		}
		else {
			// theSearchName is empty ... so just get all customers
			theQuery =currentSession.createQuery("from Presentyrecord", Presentyrecord.class);			
		}

		// execute query and get result list
		List<Presentyrecord> presentyrecords = theQuery.getResultList();

		// return the results		
		return presentyrecords;
	}

}
