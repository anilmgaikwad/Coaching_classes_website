package classes.service;

import java.util.List;

import classes.entity.Presenty;

public interface PresentyService {
	public List<Presenty> getPresenties();

	public void savePresenty(Presenty theCourse);

	public Presenty getPresenty(int theId);

	public void deletePresenty(int theId);

	public List<Presenty> searchPresenties(String theSearchName);
}
