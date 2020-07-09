package classes.entity;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	
	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;


	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Role> roles;
	
	public void addRole(Role theRole){
		if(null == roles)
			roles = new ArrayList<Role>();
		roles.add(theRole);
		
	}

	public User() {
	}

public void removeRole(Role theRole){
	roles.remove(theRole);
}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", userName='" + username + '\'' + ", password='" + "*********" + '\''
				+  '\''
				+ ", roles=" + roles + '}';
	}

	public User(String username, String password, List<Role> roles, Student student, Teacher teacher) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
}
