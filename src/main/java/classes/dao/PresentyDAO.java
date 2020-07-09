package classes.dao;

import java.util.List;

import classes.entity.Presenty;

public interface PresentyDAO {
	public List<Presenty> getPresenties();

	public void savePresenty(Presenty thePresenty);

	public Presenty getPresenty(int theId);

	public void deletePresenty(int theId);

	public List<Presenty> searchPresenties(String theSearchName);
}
