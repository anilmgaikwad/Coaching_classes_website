package classes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import classes.entity.Examresult;

@Repository
public class ExamresultDAOImpl implements ExamresultDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Examresult> getExamresults() {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Examresult> theQuery = currentSession.createQuery("from Examresult order by title", Examresult.class);

		List<Examresult> examresults = theQuery.getResultList();

		return examresults;
	}

	@Override
	public void saveExamresult(Examresult theExamresult) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theExamresult);
	}

	@Override
	public Examresult getExamresult(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Examresult theExamresult = currentSession.get(Examresult.class, theId);

		return theExamresult;
	}

	@Override
	public void deleteExamresult(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("delete from Examresult where id=:examresultId");

		theQuery.setParameter("examresultId", theId);
		theQuery.executeUpdate();
	}

	@Override
	public List<Examresult> searchExamresults(String theSearchName) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Examresult> theQuery = null;

		//
		// only search by name if theSearchName is not empty
		//
		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			theQuery = currentSession.createQuery(
					"from Examresult where lower(totalmarks) like :theTotalMarks", Examresult.class);
			theQuery.setParameter("theTotalMarks", "%" + theSearchName.toLowerCase() + "%");

		} else {
			// theSearchName is empty ... so just get all customers
			theQuery = currentSession.createQuery("from Examresult", Examresult.class);
		}

		// execute query and get result list
		List<Examresult> examresults = theQuery.getResultList();

		// return the results
		return examresults;
	}

}
