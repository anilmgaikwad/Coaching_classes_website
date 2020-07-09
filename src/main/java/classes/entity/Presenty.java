package classes.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="presenty")
public class Presenty {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	/*public Presentyrecord getPresentyRecord() {
		return presentyrecord;
	}

	public void setPresentyRecord(Presentyrecord presentyRecord) {
		this.presentyrecord = presentyRecord;
	}*/

	@Column(name="student_id")
	private int  student_id;
	
	@Column(name="status")
	private boolean status;
	
	
/*	@ManyToOne(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="presentyrecord_id")
	private Presentyrecord presentyrecord;*/



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Presenty(){}

	public Presenty(boolean status, int  student_id) {
		this.status = status;
		this.student_id = student_id;
	}

	@Override
	public String toString() {
		return "Presenty [id=" + id + ", status=" + status + ", student=" + student_id + "]";
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	
}
