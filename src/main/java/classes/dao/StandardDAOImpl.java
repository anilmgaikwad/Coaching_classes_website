package classes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import classes.entity.Exam;
import classes.entity.Standard;

@Repository
public class StandardDAOImpl implements StandardDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Standard> getStandards() {
		// TODO Auto-generated method stub
Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Standard> theQuery = 
				currentSession.createQuery("from Standard order by name",Standard.class);
		
		List<Standard> standards = theQuery.getResultList();
		return standards;
	}

	@Override
	public void saveStandard(Standard theStandard) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theStandard);
	}

	@Override
	public Standard getStandard(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Standard theStandard = currentSession.get(Standard.class, theId);
		
		return theStandard;
	}

	@Override
	public void deleteStandard(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = 
				currentSession.createQuery("delete from Standard where id=:standardId");
		
		theQuery.setParameter("standardId", theId);
		theQuery.executeUpdate();
	}

	@Override
	public List<Standard> searchStandards(String theSearchName) {
		// TODO Auto-generated method stub
		// get the current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				
				Query<Standard> theQuery = null;
				
				//
				// only search by name if theSearchName is not empty
				//
				if (theSearchName != null && theSearchName.trim().length() > 0) {

					// search for firstName or lastName ... case insensitive
					theQuery =currentSession.createQuery("from Standard where lower(name) like :theName or lower(batch) like :theName", Standard.class);
					theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

				}
				else {
					// theSearchName is empty ... so just get all customers
					theQuery =currentSession.createQuery("from Standard", Standard.class);			
				}
				
				// execute query and get result list
				List<Standard> standards = theQuery.getResultList();
						
				// return the results		
				return standards;
	}

}
