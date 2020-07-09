package classes.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="feeinstallment")
public class Feeinstallment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="date_of_day")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    /*@Temporal(TemporalType.DATE)   */ 
    private Date date_of_day;
	
	@Column(name="amount")
	private float amount;
	
	public Feeinstallment(){}

	@Override
	public String toString() {
		return "Feeinstallment [id=" + id + ", date_of_day=" + date_of_day + ", amount=" + amount + "]";
	}

	public Feeinstallment(Date date_of_day, float amount) {
		this.date_of_day = date_of_day;
		this.amount = amount;
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

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
