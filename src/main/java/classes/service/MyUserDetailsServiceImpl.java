package classes.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import classes.dao.UserDao;
import classes.entity.Role;
//import classes.entity.User;

@Service("userDetailsService")
public class MyUserDetailsServiceImpl /*implements UserDetailsService */{

	@Autowired
	private UserDao usersDAO;
	
	//@Override
	@Transactional
	public UserDetails loadUserByUsername(String theUsername) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
	/*	classes.entity.User user = usersDAO.findByUserName(theUsername);
		
		List<GrantedAuthority> authorities = 
                buildUserAuthority(user.getRoles());
		
		return buildUserForAuthentication(user, authorities);*/
		return null;
	}
	
	private List<GrantedAuthority> buildUserAuthority(Collection<Role> collection) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (Role authoritiy : collection) {
			setAuths.add(new SimpleGrantedAuthority(authoritiy.getName()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
	
	private org.springframework.security.core.userdetails.User buildUserForAuthentication(classes.entity.User user, 
			List<GrantedAuthority> authorities) {
			return new User(user.getUsername(), user.getPassword(), 
				true, true, true, true, authorities);
		}

}
