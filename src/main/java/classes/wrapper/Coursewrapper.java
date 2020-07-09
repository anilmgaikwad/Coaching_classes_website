package classes.wrapper;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import classes.entity.Course;
import classes.entity.Standard;

public class Coursewrapper {
	
private int id;	
	private String title;		
	private int fee;	
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
	
	private List<Standard> standards;


	public List<Standard> getStandards() {
		return standards;
	}
	public void setStandards(List<Standard> standards) {
		this.standards = standards;
		standardNames = new ArrayList<String>();
		 for (Standard theStandard:standards)
	      {
	    	  String StName = theStandard.getName()+ " "+ theStandard.getBatch();
	    	  standardNames.add(StName);
	      }
		
	}

	public List<String> getStandardNames() {
		return standardNames;
	}
	public void setStandardNames(List<String> standardNames) {
		this.standardNames = standardNames;
	}

	private List<String> standardNames;
	
	
	public Coursewrapper(Course theCourse){
		
		if(null != theCourse)
		{
			this.id = theCourse.getId();
			this.title = theCourse.getTitle();
		//	this.fee = theCourse.getFee();
			/*try{
			standard = theCourse.getStandard();
			}
			catch(RuntimeException ex){}
			if (null != standard) {
				standardName=standard.getName() + " " + standard.getDivision();
				
			}*/
			this.standards = theCourse.getStandards();
		}
		
	}
	
	

	public Coursewrapper()
	{
		
	}
}
