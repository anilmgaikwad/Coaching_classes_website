package classes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import classes.dao.SubjectDAO;
import classes.entity.Subject;
import classes.wrapper.Subjectwrapper;

@Service
public class SubjectwrapperServiceImpl implements SubjectwrapperService {

	@Autowired
	private SubjectDAO subjectDAO;
	
	@Override
	@Transactional
	public List<Subjectwrapper> getSubjects() {
		// TODO Auto-generated method stub
		List<Subjectwrapper> subjectwrappers = new ArrayList<Subjectwrapper>();
		List<Subject> subjects = subjectDAO.getSubjects();
		for(Subject theSubject:subjects)
		{
			Subjectwrapper theSubjectwrapper= new Subjectwrapper();
			theSubjectwrapper.setId(theSubject.getId());
			theSubjectwrapper.setName(theSubject.getName());
			theSubjectwrapper.setSyllabus_term1(theSubject.getSyllabus_term1());
			theSubjectwrapper.setSyllabus_term2(theSubject.getSyllabus_term2());
			theSubjectwrapper.setSyllabus_term3(theSubject.getSyllabus_term3());
			theSubjectwrapper.setSyllabus_term4(theSubject.getSyllabus_term4());
			theSubjectwrapper.setCourses(theSubject.getCourses());
			theSubjectwrapper.setPresentyrecords(theSubject.getPresentyrecords());
			theSubjectwrapper.setTeachers(theSubject.getTeachers());
			theSubjectwrapper.setExamdetailslist(theSubject.getExamdetails());
			theSubjectwrapper.setListOfVideos(theSubject.getListOfVideos());
			subjectwrappers.add(theSubjectwrapper);
		}
		return subjectwrappers;
	}

	@Override
	@Transactional
	public void saveSubject(Subjectwrapper theSubjectwrapper) {
		// TODO Auto-generated method stub
       int foundSubjectwrapperID = theSubjectwrapper.getId();
       Subject foundSubject = null;
       List<Subject> subjects = subjectDAO.getSubjects();
		for(Subject theSubject:subjects)
		{
			if(foundSubjectwrapperID == theSubject.getId()) 
			{
				foundSubject = theSubject;
				break;
			}
		}
		if(null == foundSubject)
		{
			foundSubject = new Subject();
			foundSubject.setId(foundSubjectwrapperID);
		}
		foundSubject.setId(theSubjectwrapper.getId());
		foundSubject.setName(theSubjectwrapper.getName());
		foundSubject.setSyllabus_term1(theSubjectwrapper.getSyllabus_term1());
		foundSubject.setSyllabus_term2(theSubjectwrapper.getSyllabus_term2());
		foundSubject.setSyllabus_term3(theSubjectwrapper.getSyllabus_term3());
		foundSubject.setSyllabus_term4(theSubjectwrapper.getSyllabus_term4());
		foundSubject.setCourses(theSubjectwrapper.getCourses());
		foundSubject.setPresentyrecords(theSubjectwrapper.getPresentyrecords());
		foundSubject.setTeachers(theSubjectwrapper.getTeachers());
		foundSubject.setListOfVideos(theSubjectwrapper.getListOfVideos());
		subjectDAO.saveSubject(foundSubject);
	}

	@Override
	@Transactional
	public Subjectwrapper getSubject(int theId) {
		// TODO Auto-generated method stub
		Subject theSubject = subjectDAO.getSubject(theId);
		Subjectwrapper theSubjectwrapper = new Subjectwrapper();
		theSubjectwrapper.setId(theSubject.getId());
		theSubjectwrapper.setName(theSubject.getName());
		theSubjectwrapper.setSyllabus_term1(theSubject.getSyllabus_term1());
		theSubjectwrapper.setSyllabus_term2(theSubject.getSyllabus_term2());
		theSubjectwrapper.setSyllabus_term3(theSubject.getSyllabus_term3());
		theSubjectwrapper.setSyllabus_term4(theSubject.getSyllabus_term4());
		theSubjectwrapper.setCourses(theSubject.getCourses());
		theSubjectwrapper.setPresentyrecords(theSubject.getPresentyrecords());
		theSubjectwrapper.setTeachers(theSubject.getTeachers());
		theSubjectwrapper.setExamdetailslist(theSubject.getExamdetails());
		theSubjectwrapper.setListOfVideos(theSubject.getListOfVideos());
		return theSubjectwrapper;
	}

	@Override
	@Transactional
	public void deleteSubject(int theId) {
		// TODO Auto-generated method stub
		subjectDAO.deleteSubject(theId);
	}

	@Override
	@Transactional
	public List<Subjectwrapper> searchSubjects(String theSearchName) {
		// TODO Auto-generated method stub
		List<Subjectwrapper> subjectwrappers = new ArrayList<Subjectwrapper>();
		List <Subject>subjects = subjectDAO.searchSubjects(theSearchName);
		for(Subject theSubject:subjects)
		{
			Subjectwrapper theSubjectwrapper= new Subjectwrapper();
			theSubjectwrapper.setId(theSubject.getId());
			theSubjectwrapper.setName(theSubject.getName());
			theSubjectwrapper.setSyllabus_term1(theSubject.getSyllabus_term1());
			theSubjectwrapper.setSyllabus_term2(theSubject.getSyllabus_term2());
			theSubjectwrapper.setSyllabus_term3(theSubject.getSyllabus_term3());
			theSubjectwrapper.setSyllabus_term4(theSubject.getSyllabus_term4());
			theSubjectwrapper.setCourses(theSubject.getCourses());
			theSubjectwrapper.setPresentyrecords(theSubject.getPresentyrecords());
			theSubjectwrapper.setTeachers(theSubject.getTeachers());
			theSubjectwrapper.setExamdetailslist(theSubject.getExamdetails());
			theSubjectwrapper.setListOfVideos(theSubject.getListOfVideos());
			subjectwrappers.add(theSubjectwrapper);
		}
		return subjectwrappers;
	}

}
