package classes.dao;

import classes.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
