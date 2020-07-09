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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="feetable")
public class Feetable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="totalamount")
	private float totalamount;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getTotalamount() {
		return totalamount;
	}

	public void setTotalamount(float totalamount) {
		this.totalamount = totalamount;
	}

	public float getRemaingamount() {
		return remaingamount;
	}

	public void setRemaingamount(float remaingamount) {
		this.remaingamount = remaingamount;
	}

	public List<Feeinstallment> getFeeinstallments() {
		if(null == feeinstallments)
			feeinstallments = new ArrayList<Feeinstallment>();
		return feeinstallments;
	}

	public void setFeeinstallments(List<Feeinstallment> feeinstallments) {
		this.feeinstallments = feeinstallments;
	}
	
	public void addFeeInstallment(Feeinstallment theFeeinstallment){
		if(null == this.feeinstallments)
			this.feeinstallments = new ArrayList<Feeinstallment>();
		
		feeinstallments.add(theFeeinstallment);
	}
	
	public void deleteFromtempFeeInstallments(int feeInstallmentId){
		if(null != this.feeinstallments){
			for(Feeinstallment theFeeinstallment:this.feeinstallments){
				if(feeInstallmentId == theFeeinstallment.getId()){
					feeinstallments.remove(feeInstallmentId);
				}
			}
		}
	}

	@Column(name="remaingamount")
	private float remaingamount;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "feetable_id")
	private List<Feeinstallment> feeinstallments;

}
