package classes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import classes.dao.ExamDAO;
import classes.dao.SubjectDAO;
import classes.entity.Subject;

@Service
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired
	private SubjectDAO subjectDAO;

	@Override
	@Transactional
	public List<Subject> getSubjects() {
		// TODO Auto-generated method stub
		return subjectDAO.getSubjects();
	}

	@Override
	@Transactional
	public void saveSubject(Subject theSubject) {
		// TODO Auto-generated method stub
		subjectDAO.saveSubject(theSubject);
	}

	@Override
	@Transactional
	public Subject getSubject(int theId) {
		// TODO Auto-generated method stub
		return subjectDAO.getSubject(theId);
	}

	@Override
	@Transactional
	public void deleteSubject(int theId) {
		// TODO Auto-generated method stub
		subjectDAO.deleteSubject(theId);
	}

	@Override
	@Transactional
	public List<Subject> searchSubjects(String theSearchName) {
		// TODO Auto-generated method stub
		return subjectDAO.searchSubjects(theSearchName);
	}

}
