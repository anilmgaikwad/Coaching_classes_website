package classes.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import classes.entity.Teacher;

@Repository
public class TeacherDAOImpl implements TeacherDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Teacher> getTeachers() {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Teacher> theQuery = 
				currentSession.createQuery("from Teacher order by id_number",Teacher.class);

		List<Teacher> teachers = theQuery.getResultList();

		return teachers;
	}

	@Override
	public void saveTeacher(Teacher theTeacher) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theTeacher);
	}

	@Override
	public Teacher getTeacher(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Teacher theTeacher = currentSession.get(Teacher.class, theId);
		
		return theTeacher;
	}

	@Override
	public void deleteTeacher(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = 
				currentSession.createQuery("delete from Teacher where id=:teacherId");
		
		theQuery.setParameter("teacherId", theId);
		theQuery.executeUpdate();
	}

	@Override
	public List<Teacher> searchTeachers(String theSearchName) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Teacher> theQuery = null;

		//
		// only search by name if theSearchName is not empty
		//
		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			theQuery =currentSession.createQuery("from Teacher where lower(first_name) like :theName or lower(last_name) like :theName or lower(last_name) like :theName", Teacher.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

		}
		else {
			// theSearchName is empty ... so just get all customers
			theQuery =currentSession.createQuery("from Teacher", Teacher.class);			
		}

		// execute query and get result list
		List<Teacher> teachers = theQuery.getResultList();

		// return the results		
		return teachers;
	}

}
