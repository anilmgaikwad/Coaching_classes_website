package classes.wrapper;

import java.util.Date;

import classes.entity.Feeinstallment;

public class Feeinstallmentwrapper {
	private int id;
	 private Date date_of_day;
	 private float amount;
	 private int studentId;
	 
	 public Feeinstallmentwrapper(){}
	 public Feeinstallmentwrapper(Feeinstallment theFeeinstallment,int theStudentId){
		 this.id = theFeeinstallment.getId();
		 this.date_of_day = theFeeinstallment.getDate_of_day();
		 this.amount = theFeeinstallment.getAmount();
		 this.studentId = theStudentId;
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
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
}
