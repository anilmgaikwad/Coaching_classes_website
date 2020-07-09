package classes.entity;


import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import classes.wrapper.Coursewrapper;


@Entity
@Table(name="course")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="fee")
	private int fee;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(
			name="course_standard",
			joinColumns=@JoinColumn(name="course_id"),
			inverseJoinColumns=@JoinColumn(name="standard_id"))	
	private List<Standard> standards;
	

    @LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(
			name="course_student",
			joinColumns=@JoinColumn(name="course_id"),
			inverseJoinColumns=@JoinColumn(name="student_id"))
	private Set<Student> students; 
	

    @LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(fetch=FetchType.LAZY,cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(
			name="course_subject",
			joinColumns=@JoinColumn(name="course_id"),
			inverseJoinColumns=@JoinColumn(name="subject_id"))
	private List<Subject> subjects;
	
	/*@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="course_id")
	private List<Presentyrecord> listOfPresentyRecord;*/
	

	public Course(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Course(String title, int fee, List<Standard> standards, List<Subject> subjects) {
		this.title = title;
		this.fee = fee;
		this.standards = standards;
		this.subjects = subjects;
		
	}

	public List<Standard> getStandards() {
		return standards;
	}

	public void setStandards(List<Standard> standards) {
		this.standards = standards;
	}

	/*public List<Presentyrecord> getListOfPresentyRecord() {
		return listOfPresentyRecord;
	}

	public void setListOfPresentyRecord(List<Presentyrecord> listOfPresentyRecord) {
		this.listOfPresentyRecord = listOfPresentyRecord;
	}*/

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", fee=" + fee + ", standards=" + standards + ", students="
				+ students + ", subjects=" + subjects + ", listOfPresentyRecord=" + "]";
	}
	
	public Course(Coursewrapper theCourseWrapper){
		if(null != theCourseWrapper)
		{
			id = theCourseWrapper.getId();
			title = theCourseWrapper.getTitle();
			fee = theCourseWrapper.getFee();
			this.standards = theCourseWrapper.getStandards();
		}
		
	}


}
