package classes.wrapper;

import java.util.ArrayList;
import java.util.List;

import classes.entity.Examdetails;
import classes.entity.Examresult;
import classes.service.StudentwrapperService;

public class ExamDetailswrapper {

	private String examName;
	private int totalmarks;
	private int id;
	private int exam_id;
	private int teacher_id;
	private int subject_id;
	private List <Examresultwrapper> examresultwrappers;
	
	private List<Examresult> examresults;

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public int getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(int subject_id) {
		this.subject_id = subject_id;
	}

	

	public int getExam_id() {
		return exam_id;
	}

	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	private String subjectName;

	public int getTotalmarks() {
		return totalmarks;
	}

	public void setTotalmarks(int totalmarks) {
		this.totalmarks = totalmarks;
	}

	public ExamDetailswrapper(Examdetails theExamdetails) {
		this.exam_id = theExamdetails.getExam_id();
		this.id = theExamdetails.getId();
		this.totalmarks = theExamdetails.getTotalmarks();
		this.examresults = theExamdetails.getExamresults();
	}

	public List<Examresultwrapper> getExamresultwrappers() {
		return examresultwrappers;
	}

	public void setExamresultwrappers(List<Examresultwrapper> examresultwrappers) {
		this.examresultwrappers = examresultwrappers;
	}

	public void populateExamResultDetails(StudentwrapperService studentwrapperservice) {
		examresultwrappers = new ArrayList<Examresultwrapper>();
		for(Examresult theExamresult:this.examresults){
			Examresultwrapper theExamresultwrapper = new Examresultwrapper(theExamresult,studentwrapperservice);
			examresultwrappers.add(theExamresultwrapper);
		}
	}

	

	public List<Examresult> getExamresults() {
		return examresults;
	}

	public void setExamresults(List<Examresult> examresults) {
		this.examresults = examresults;
	}

	public ExamDetailswrapper() {

	}
}
