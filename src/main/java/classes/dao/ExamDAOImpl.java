package classes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import classes.entity.Exam;

@Repository
public class ExamDAOImpl implements ExamDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	//@Transactional  -This added to service layer
	public List<Exam> getExams() {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Exam> theQuery = 
				currentSession.createQuery("from Exam order by type",Exam.class);
		
		List<Exam> exams = theQuery.getResultList();
		return exams;
	}

	@Override
	public void saveExam(Exam theExam) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theExam);
	}

	@Override
	public Exam getExam(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Exam theExam = currentSession.get(Exam.class, theId);
		
		return theExam;
	}

	@Override
	public void deleteExam(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = 
				currentSession.createQuery("delete from Exam where id=:examId");
		
		theQuery.setParameter("examId", theId);
		theQuery.executeUpdate();
	}

	@Override
	public List<Exam> searchExams(String theSearchName) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Exam> theQuery = null;
		
		//
		// only search by name if theSearchName is not empty
		//
		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			theQuery =currentSession.createQuery("from Exam where lower(name) like :theName or lower(type) like :theName", Exam.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

		}
		else {
			// theSearchName is empty ... so just get all customers
			theQuery =currentSession.createQuery("from Exam", Exam.class);			
		}
		
		// execute query and get result list
		List<Exam> exams = theQuery.getResultList();
				
		// return the results		
		return exams;
	}
}
