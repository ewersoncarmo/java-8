package chapter8;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import domain.User;

public class UserGroup {

	private Set<User> users = new HashSet<>();
	
	public void add(User user) {
		users.add(user);
	}
	
	public Set<User> getUsers() {
		return Collections.unmodifiableSet(this.users);
	}
}
