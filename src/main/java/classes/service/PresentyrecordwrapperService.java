package classes.service;

import java.util.List;

import classes.entity.Presentyrecord;
import classes.wrapper.Presentyrecordwrapper;

public interface PresentyrecordwrapperService {
	public List<Presentyrecordwrapper> getPresentyrecordwrappers();

	public void savePresentyrecordwrapper(Presentyrecordwrapper thePresentyrecord);

	public Presentyrecordwrapper getPresentyrecordwrapper(int theId);

	public void deletePresentyrecordwrapper(int theId);

	public List<Presentyrecordwrapper> searchPresentyrecordwrappers(String theSearchName);
	
	public Presentyrecord getPresentyrecord(int theId);
}
