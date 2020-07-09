package classes.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import classes.entity.Course;
import classes.entity.Exam;
import classes.entity.Subject;

@Repository
public class SubjectDAOImpl implements SubjectDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Subject> getSubjects() {
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Subject> theQuery = currentSession.createQuery("from Subject order by name", Subject.class);

		List<Subject> subjects = theQuery.getResultList();

		return subjects;
	}

	@Override
	public void saveSubject(Subject theSubject) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theSubject);
	}

	@Override
	public Subject getSubject(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Subject theSubject = currentSession.get(Subject.class, theId);
		return theSubject;
	}

	@Override
	public void deleteSubject(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Subject theSubject = currentSession.get(Subject.class, theId);
		currentSession.delete(theSubject);
		/*
		 * Query theQuery =
		 * currentSession.createQuery("delete from Subject where id=:subjectId"
		 * );
		 * 
		 * theQuery.setParameter("subjectId", theId); theQuery.executeUpdate();
		 */

	}

	@Override
	public List<Subject> searchSubjects(String theSearchName) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Subject> theQuery = null;

		//
		// only search by name if theSearchName is not empty
		//
		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			theQuery = currentSession.createQuery(
					"from Subject where lower(name) like :theName", Subject.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

		} else {
			// theSearchName is empty ... so just get all customers
			theQuery = currentSession.createQuery("from Subject", Subject.class);
		}

		// execute query and get result list
		List<Subject> subjects = theQuery.getResultList();

		// return the results
		return subjects;
	}

}
