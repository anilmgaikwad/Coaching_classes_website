package classes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import classes.entity.Examdetails;


@Repository
public class ExamdetailsDAOImpl implements ExamdetailsDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Examdetails> getExamdetails() {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Examdetails> theQuery = currentSession.createQuery("from Examdetails order by totalmarks", Examdetails.class);

		List<Examdetails> listExamdetails = theQuery.getResultList();

		return listExamdetails;
	}

	@Override
	public void saveExamdetails(Examdetails theExamdetails) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theExamdetails);
	}

	@Override
	public Examdetails getExamdetails(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Examdetails theExamdetails = currentSession.get(Examdetails.class, theId);

		return theExamdetails;
	}

	@Override
	public void deleteExamdetails(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
	/*	Query theQuery = currentSession.createQuery("delete from Examdetails where id=:ExamdetailsId");

		theQuery.setParameter("ExamdetailsId", theId);
		theQuery.executeUpdate();*/
		
		Examdetails theExamdetails = currentSession.get(Examdetails.class, theId);
		currentSession.delete(theExamdetails);
	}

	@Override
	public List<Examdetails> searchExamdetails(String theSearchName) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Examdetails> theQuery = null;

		//
		// only search by name if theSearchName is not empty
		//
		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			theQuery = currentSession.createQuery(
					"from Examdetails where lower(totalmarks) like :theTotalMarks", Examdetails.class);
			theQuery.setParameter("theTotalMarks", "%" + theSearchName.toLowerCase() + "%");

		} else {
			// theSearchName is empty ... so just get all customers
			theQuery = currentSession.createQuery("from Examresult", Examdetails.class);
		}

		// execute query and get result list
		List<Examdetails> listExamdetails = theQuery.getResultList();

		// return the results
		return listExamdetails;
	}

}
