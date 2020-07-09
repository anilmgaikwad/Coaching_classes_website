package classes.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="examdetails")
public class Examdetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@Column(name="totalmarks")
	private int totalmarks;
	
	
/*	@OneToOne(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="exam_id")
	private Exam exam;*/
	
	@Column(name="exam_id")
	private int exam_id;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "examdetails_id")
	private List<Examresult> examresults;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotalmarks() {
		return totalmarks;
	}

	public void setTotalmarks(int totalmarks) {
		this.totalmarks = totalmarks;
	}



	
	public int getExam_id() {
		return exam_id;
	}

	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}

	public void addExamresult(Examresult examresult) {
		if(null == this.examresults)
			this.examresults = new ArrayList<Examresult>();
		this.examresults.add(examresult);
	}

	public List<Examresult> getExamresults() {
		return examresults;
	}

	public void setExamresults(List<Examresult> examresults) {
		this.examresults = examresults;
	}

}
