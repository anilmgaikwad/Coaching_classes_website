package classes.wrapper;

import java.util.ArrayList;
import java.util.List;

import classes.entity.Course;
import classes.entity.Examdetails;
import classes.entity.Examresult;
import classes.entity.Subject;

public class MarkSheet {
	private int studentId;
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	private String instituteName;
	private String studentName;
	private String rollNumber;
	private String examName;
	private List<String> row1;
	private List<String> row2;
	private List<String> row3;
	private List<String> row4;
	private int col_span;
	private String author;
	private String fileRelativePath;

	public String getFileRelativePath() {
		return fileRelativePath;
	}

	public void setFileRelativePath(String fileRelativePath) {
		this.fileRelativePath = fileRelativePath;
	}

	public MarkSheet(Studentwrapper theStudentwrapper, int theExamId,String examName) {
		this.instituteName = "Guru Coaching Classes";
		this.studentName = theStudentwrapper.getFull_name();
		this.author = theStudentwrapper.getFull_name();
		this.rollNumber = theStudentwrapper.getRoll_number();
		this.examName = examName;
		this.studentId = theStudentwrapper.getId();
		 row1 = new ArrayList<String>();
		 row2 = new ArrayList<String>();
		 row3 = new ArrayList<String>();
		 row4 = new ArrayList<String>();
		row1.add("Subject Name");
		row2.add("Marks Obtained");
		row3.add("Total Marks");
		row4.add("Grade");
		boolean bAbsent = false;
		int allTotalMarks = 0;
		int allObtainedMarks = 0;
		List<Course> courses = theStudentwrapper.getCourses();
		List<Subject> subjects = new ArrayList<Subject>();
		for (Course theCourse : courses) {
			List<Subject> mySubjects = theCourse.getSubjects();
			subjects.addAll(mySubjects);
		}
		for (Subject theSubject : subjects) {
			
			List<Examdetails> examdetailsList = theSubject.getExamdetails();
			for (Examdetails theExamdetails : examdetailsList) {
				if (theExamId == theExamdetails.getExam_id()) {
					row1.add(theSubject.getName());
					List<Examresult> examresults = theExamdetails.getExamresults();
					for (Examresult theExamresult : examresults) {
						if (theStudentwrapper.getId() == theExamresult.getStudent_id()) {
							allObtainedMarks = allObtainedMarks + theExamresult.getMarksobtained();
							if (true == theExamresult.isStatus()) {
								row2.add(Integer.toString(theExamresult.getMarksobtained()));
								float percent = ((float)theExamresult.getMarksobtained() / (float)theExamdetails.getTotalmarks()) * 100;
								row4.add(getMyGrade((int)percent));
							} else {
								row2.add("Absent");
								row4.add("C");
								bAbsent = true;
							}
						}
					}
					row3.add(Integer.toString(theExamdetails.getTotalmarks()));
					allTotalMarks = allTotalMarks + theExamdetails.getTotalmarks();
				}
			}
		}
		row1.add("Result");
		row2.add(Integer.toString(allObtainedMarks));
		row3.add(Integer.toString(allTotalMarks));
		String finalGrade = "";
		if (bAbsent)
			finalGrade = "FAIL";
		else {
			float percent = ((float)allObtainedMarks / (float)allTotalMarks) * 100;
			finalGrade = getMyGrade((int) percent);
		}
		row4.add(finalGrade);
		this.col_span = row1.size()/2;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getCol_span() {
		return col_span;
	}

	public void setCol_span(int col_span) {
		this.col_span = col_span;
	}

	public String getInstituteName() {
		return instituteName;
	}

	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}



	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public List<String> getRow1() {
		return row1;
	}

	public void setRow1(List<String> row1) {
		this.row1 = row1;
	}

	public List<String> getRow2() {
		return row2;
	}

	public void setRow2(List<String> row2) {
		this.row2 = row2;
	}

	public List<String> getRow3() {
		return row3;
	}

	public void setRow3(List<String> row3) {
		this.row3 = row3;
	}

	public List<String> getRow4() {
		return row4;
	}

	public void setRow4(List<String> row4) {
		this.row4 = row4;
	}

	public String getMyGrade(int marks) {
		String grade = "";
		if (marks < 35) {
			grade = "C";
		} else if ((35 <= marks) && (marks < 50)) {
			grade = "B";
		} else if ((50 <= marks) && (marks < 60)) {
			grade = "B+";
		} else if ((60 <= marks) && (marks < 70)) {
			grade = "A";
		} else if ((70 <= marks) && (marks < 80)) {
			grade = "A+";
		} else if ((80 <= marks)) {
			grade = "E";
		}
		return grade;
	}
}
