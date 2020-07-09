package classes.dao;

import java.util.List;

import classes.entity.Presentyrecord;

public interface PresentyrecordDAO {
	public List<Presentyrecord> getPresentyrecords();

	public void savePresentyrecord(Presentyrecord thePresentyrecord);

	public Presentyrecord getPresentyrecord(int theId);

	public void deletePresentyrecord(int theId);

	public List<Presentyrecord> searchPresentyrecords(String theSearchName);

}
