package classes.entity;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "presentyrecord")
public class Presentyrecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "date_of_day")
	/*@Temporal(TemporalType.DATE)*/
/*	@DateTimeFormat(pattern = "dd-MM-yyyy")*/
	private Date date_of_day;
	
	

	/*
	 * @ManyToOne(cascade={CascadeType.DETACH,CascadeType.MERGE,CascadeType.
	 * PERSIST,CascadeType.REFRESH})
	 * 
	 * @JoinColumn(name="subject_id") private Subject subject;
	 */

	/*
	 * @OneToMany(mappedBy="presentyrecord",
	 * fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	 * 
	 * @JoinColumn(name="presentyrecord_id")
	 */
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "presentyrecord_id")
	private List<Presenty> presenties;

	public List<Presenty> getPresenties() {
		return presenties;
	}

	public void setPresenties(List<Presenty> presenties) {
		this.presenties = presenties;
	}

	public void addPresenty(Presenty thePresenty) {
		if (null == presenties)
			presenties = new ArrayList<Presenty>();
		presenties.add(thePresenty);
	}

	@Override
	public String toString() {
		return "Presentyrecord [id=" + id + ", date_of_day=" + date_of_day +  "]";
	}

	public Presentyrecord(Date date_of_day, Subject subject) {
		this.date_of_day = date_of_day;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate_of_day() {
		return date_of_day;
	}

	public void setDate_of_day(Date date_of_day) {
		this.date_of_day = date_of_day;
	}

	public Presentyrecord() {
	}

}
