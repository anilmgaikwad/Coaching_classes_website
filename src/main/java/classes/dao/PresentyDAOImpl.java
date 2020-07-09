package classes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import classes.entity.Presenty;



@Repository
public class PresentyDAOImpl implements PresentyDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Presenty> getPresenties() {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Presenty> theQuery = 
				currentSession.createQuery("from Presenty order by id",Presenty.class);

		List<Presenty> presenties = theQuery.getResultList();

		return presenties;
	}

	@Override
	public void savePresenty(Presenty thePresenty) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(thePresenty);
	}

	@Override
	public Presenty getPresenty(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Presenty thePresenty = currentSession.get(Presenty.class, theId);

		return thePresenty;
	}

	@Override
	public void deletePresenty(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = 
				currentSession.createQuery("delete from Presenty where id=:presentyId");

		theQuery.setParameter("presentyId", theId);
		theQuery.executeUpdate();
	}

	@Override
	public List<Presenty> searchPresenties(String theSearchName) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Presenty> theQuery = null;

		//
		// only search by name if theSearchName is not empty
		//
		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			theQuery =currentSession.createQuery("from Presenty where lower(status) like :thepresenty", Presenty.class);
			theQuery.setParameter("thepresenty", "%" + theSearchName.toLowerCase() + "%");

		}
		else {
			// theSearchName is empty ... so just get all customers
			theQuery =currentSession.createQuery("from Presenty", Presenty.class);			
		}

		// execute query and get result list
		List<Presenty> presenties = theQuery.getResultList();

		// return the results		
		return presenties;
	}

}
