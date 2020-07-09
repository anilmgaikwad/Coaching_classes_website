package classes.dao;

import java.util.List;

import classes.entity.Videoinfo;

public interface VideoinfoDAO {
	public List<Videoinfo> getVideoinfos();

	public void saveVideoinfo(Videoinfo theVideoinfo);

	public Videoinfo getVideoinfo(int theId);

	public void deleteVideoinfo(int theId);

	public List<Videoinfo> searchVideoinfos(String theSearchName);
}
