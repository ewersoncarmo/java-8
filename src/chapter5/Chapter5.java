package chapter5;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import domain.User;

public class Chapter5 {

	public static void main(String[] args) {
		User user1 = new User("User 1", 150);
		User user2 = new User("User 2", 120);
		User user3 = new User("User 3", 190);

		List<User> users = Arrays.asList(user1, user2, user3);
		
		System.out.println("method 1 - anonymous class which implements Comparator interface");
		Comparator<User> comparator1 = new Comparator<User>() {

			@Override
			public int compare(User user1, User user2) {
				return user1.getName().compareTo(user2.getName());
			}
			
		};
		
		Collections.sort(users, comparator1);
		users.forEach(u -> System.out.println(u.getName()));
		//
		System.out.println("method 2 - lambda expression");
		Comparator<User> comparator2 = (u1, u2) -> u1.getName().compareTo(u2.getName());
		Collections.sort(users, comparator2);
		users.forEach(u -> System.out.println(u.getName()));
		//
		System.out.println("method 3 - lambda expression inside the Collections.sort");
		Collections.sort(users, (u1, u2) -> u1.getName().compareTo(u2.getName()));
		users.forEach(u -> System.out.println(u.getName()));
		//
		System.out.println("method 4 - lambda expression directly inside the users list");
		users.sort((u1, u2) -> u1.getName().compareTo(u2.getName()));
		users.forEach(u -> System.out.println(u.getName()));
		//
		System.out.println("method 5 - lambda expression using Comparator.comparing to compare String");
		users.sort(Comparator.comparing(u -> u.getName()));
		users.forEach(u -> System.out.println(u.getName()));
		//
		System.out.println("method 6 - lambda expression using Comparator.comparingInt to compare Integer");
		users.sort(Comparator.comparingInt(u -> u.getScores()));
		users.forEach(u -> System.out.println(u.getName()));
	}
}
