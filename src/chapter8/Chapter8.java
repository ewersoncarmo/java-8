package chapter8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import domain.User;

public class Chapter8 {

	public static void main(String[] args) {
		User user1 = new User("User 1", 150);
		User user2 = new User("User 2", 120);
		User user3 = new User("User 3", 190);

		List<User> users = Arrays.asList(user1, user2, user3);

		System.out.println("method 1 - filtering and sorting an stream");
		//
		users.stream()
			.peek(u -> System.out.println("iterating element " + u.getName()))
			.filter(u -> u.getScores() > 120)
			.peek(u -> System.out.println("filtered element " + u.getName()))
			.sorted(Comparator.comparing(User::getName))
			.peek(u -> System.out.println("sorted element " + u.getName()))
			.collect(Collectors.toList());
		
	}
}
