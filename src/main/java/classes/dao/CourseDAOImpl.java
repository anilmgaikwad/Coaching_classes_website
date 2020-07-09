package classes.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import classes.entity.Course;
import classes.entity.Student;
import classes.entity.Subject;


@Repository
public class CourseDAOImpl implements CourseDAO {


	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Course> theQuery = 
				currentSession.createQuery("from Course order by title",Course.class);

		List<Course> courses = theQuery.getResultList();

		return courses;
	}

	@Override
	public void saveCourse(Course theCourse) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theCourse);
	}

	@Override
	public Course getCourse(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Course theCourse = currentSession.get(Course.class, theId);

		return theCourse;
	}

	@Override
	public void deleteCourse(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = 
				currentSession.createQuery("delete from Course where id=:courseId");

		theQuery.setParameter("courseId", theId);
		theQuery.executeUpdate();
	}

	@Override
	public List<Course> searchCourses(String theSearchName) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Course> theQuery = null;

		//
		// only search by name if theSearchName is not empty
		//
		if (theSearchName != null && theSearchName.trim().length() > 0) {

			// search for firstName or lastName ... case insensitive
			theQuery =currentSession.createQuery("from Course where lower(title) like :theName", Course.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

		}
		else {
			// theSearchName is empty ... so just get all customers
			theQuery =currentSession.createQuery("from Course", Course.class);			
		}

		// execute query and get result list
		List<Course> courses = theQuery.getResultList();

		// return the results		
		return courses;
	}

}
