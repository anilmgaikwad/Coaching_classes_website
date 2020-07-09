package classes.service;

import java.util.List;

import classes.entity.Standard;

public interface StandardService {
	public List<Standard> getStandards();

	public void saveStandard(Standard theStandard);

	public Standard getStandard(int theId);

	public void deleteStandard(int theId);

	public List<Standard> searchStandards(String theSearchName);

}
