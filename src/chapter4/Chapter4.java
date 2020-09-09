package chapter4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import domain.User;

public class Chapter4 {

	public static void main(String[] args) {
		User user1 = new User("User 1", 150);
		User user2 = new User("User 2", 120);
		User user3 = new User("User 3", 190);

		List<User> usersImmutableList = Arrays.asList(user1, user2, user3);
		
		System.out.println("method 1 - method Consumer.andThen");
		Consumer<User> printMessage = u -> System.out.println("printing message");
		Consumer<User> printNames = u -> System.out.println(u.getName());
		
		usersImmutableList.forEach(printMessage.andThen(printNames));
		//
		System.out.println("method 2 - method Collection.removeIf");
		List<User> usersMutableList = new ArrayList<>();
		usersMutableList.add(user1);
		usersMutableList.add(user2);
		usersMutableList.add(user3);
		
		usersMutableList.removeIf(u -> u.getScores() > 160);
		usersMutableList.forEach(u -> System.out.println(u.getName()));
	}
}
