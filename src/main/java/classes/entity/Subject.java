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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import classes.wrapper.Presentyrecordwrapper;



@Entity
@Table(name="subject")
public class Subject {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="syllabus_term1")
	private String syllabus_term1;
	
	@Column(name="syllabus_term2")
	private String syllabus_term2;
	
	@Column(name="syllabus_term3")
	private String syllabus_term3;
	
	@Column(name="syllabus_term4")
	private String syllabus_term4;
	

/*	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="subject", cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})*/
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="subject_id")
	private List<Presentyrecord> presentyrecords;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(fetch=FetchType.EAGER,cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(
			name="course_subject",
			joinColumns=@JoinColumn(name="subject_id"),
			inverseJoinColumns=@JoinColumn(name="course_id"))
	private List<Course> courses;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(fetch=FetchType.LAZY,cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(
			name="subject_teacher",
			joinColumns=@JoinColumn(name="subject_id"),
			inverseJoinColumns=@JoinColumn(name="teacher_id"))
	private List<Teacher> teachers;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="subject_id")
	private List<Examdetails> examdetails;
	
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="subject_id")
	private List<Videoinfo> listOfVideos;
	
	public List<Videoinfo> getListOfVideos() {
		return listOfVideos;
	}


	public void setListOfVideos(List<Videoinfo> listOfVideos) {
		this.listOfVideos = listOfVideos;
	}
	
	public void addVideo(Videoinfo theVideoinfo) {
		if(null == this.listOfVideos)
			this.listOfVideos = new ArrayList<Videoinfo>();
		this.listOfVideos.add(theVideoinfo);
	}
	public void removeVideo(Videoinfo theVideoinfo) {
		if(null != this.listOfVideos){
			this.listOfVideos.remove(theVideoinfo);
		}
			
	
	}

	public Subject(){
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<Examdetails> getExamdetails() {
		return examdetails;
	}


	public void setExamdetails(List<Examdetails> examdetails) {
		this.examdetails = examdetails;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSyllabus_term1() {
		return syllabus_term1;
	}


	public void setSyllabus_term1(String syllabus_term1) {
		this.syllabus_term1 = syllabus_term1;
	}


	public String getSyllabus_term2() {
		return syllabus_term2;
	}


	public void setSyllabus_term2(String syllabus_term2) {
		this.syllabus_term2 = syllabus_term2;
	}


	public String getSyllabus_term3() {
		return syllabus_term3;
	}


	public void setSyllabus_term3(String syllabus_term3) {
		this.syllabus_term3 = syllabus_term3;
	}


	public String getSyllabus_term4() {
		return syllabus_term4;
	}


	public void setSyllabus_term4(String syllabus_term4) {
		this.syllabus_term4 = syllabus_term4;
	}


	public List<Course> getCourses() {
		return courses;
	}


	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}


	public List<Teacher> getTeachers() {
		return teachers;
	}


	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public List<Presentyrecord> getPresentyrecords() {
		return presentyrecords;
	}


	public void setPresentyrecords(List<Presentyrecord> presentyrecords) {
		this.presentyrecords = presentyrecords;
	}

	public void addPresentyrecord(Presentyrecord thePresentyrecord) {
		if(null == this.presentyrecords) {
			presentyrecords = new ArrayList<Presentyrecord>();
		}
		presentyrecords.add(thePresentyrecord);
	}
	
	public void addExamdetails(Examdetails theExamdetails) {
		if(null == this.examdetails) {
			examdetails = new ArrayList<Examdetails>();
		}
		examdetails.add(theExamdetails);
	}
	
	public void deletePresentyrecord(int theId) {
		if(null != this.presentyrecords) {
			for(Presentyrecord thePresentyrecord :this.presentyrecords){
				if(thePresentyrecord.getId() == theId)
				{
					this.presentyrecords.remove(thePresentyrecord);
					break;
				}
			}
		}
		
	}

	public void deleteExamDetails(int theId) {
		if(null != this.examdetails) {
			for(Examdetails theExamdetails :this.examdetails){
				if(theExamdetails.getId() == theId)
				{
					this.examdetails.remove(theExamdetails);
					break;
				}
			}
		}
		
	}


	public Subject(String name, String syllabus_term1, String syllabus_term2, String syllabus_term3,
			String syllabus_term4, List<Presentyrecord> presentyrecords, List<Course> courses, List<Teacher> teachers) {
		this.name = name;
		this.syllabus_term1 = syllabus_term1;
		this.syllabus_term2 = syllabus_term2;
		this.syllabus_term3 = syllabus_term3;
		this.syllabus_term4 = syllabus_term4;
		this.presentyrecords = presentyrecords;
		this.courses = courses;
		this.teachers = teachers;
	}


	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", syllabus_term1=" + syllabus_term1 + ", syllabus_term2="
				+ syllabus_term2 + ", syllabus_term3=" + syllabus_term3 + ", syllabus_term4=" + syllabus_term4
				+ ", courses=" + courses + ", teachers=" + teachers + "]";
	}


	

}
