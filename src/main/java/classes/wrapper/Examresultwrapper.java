package classes.wrapper;



import classes.entity.Examresult;
import classes.service.StudentwrapperService;

public class Examresultwrapper {
	private String studentname;
	private int marksobtained;	
	private boolean presentyStatus;
	
	public Examresultwrapper(Examresult theExamresult,StudentwrapperService studentwrapperservice){

		if (null != studentwrapperservice) {
			this.studentname = studentwrapperservice.getStudent(theExamresult.getStudent_id()).getFull_name();
			this.marksobtained = theExamresult.getMarksobtained();
			this.presentyStatus = theExamresult.isStatus();
		}
	}
	public Examresultwrapper(){}
	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public int getMarksobtained() {
		return marksobtained;
	}

	public void setMarksobtained(int marksobtained) {
		this.marksobtained = marksobtained;
	}
	public boolean isPresentyStatus() {
		return presentyStatus;
	}
	public void setPresentyStatus(boolean presentyStatus) {
		this.presentyStatus = presentyStatus;
	}



}
