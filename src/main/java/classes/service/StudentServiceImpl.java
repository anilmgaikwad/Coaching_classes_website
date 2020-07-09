package classes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import classes.dao.StudentDAO;
import classes.entity.Student;
@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDAO studentDAO;

	@Override
	@Transactional
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return studentDAO.getStudents();
	}

	@Override
	@Transactional
	public void saveStudent(Student theStudent) {
		// TODO Auto-generated method stub
		studentDAO.saveStudent(theStudent);
	}

	@Override
	@Transactional
	public Student getStudent(int theId) {
		// TODO Auto-generated method stub
		return studentDAO.getStudent(theId);
	}

	@Override
	@Transactional
	public void deleteStudent(int theId) {
		// TODO Auto-generated method stub
		studentDAO.deleteStudent(theId);
	}

	@Override
	@Transactional
	public List<Student> searchStudents(String theSearchName) {
		// TODO Auto-generated method stub
		return studentDAO.searchStudents(theSearchName);
	}

}
