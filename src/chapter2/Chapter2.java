package chapter2;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import domain.User;

public class Chapter2 {

	public static void main(String[] args) {
		User user1 = new User("User 1", 150);
		User user2 = new User("User 2", 120);
		User user3 = new User("User 3", 190);

		List<User> users = Arrays.asList(user1, user2, user3);

		System.out.println("method 1 - conventional loop");
		for (User user : users) {
			System.out.println(user.getName());
		}
		//
		System.out.println("method 2 - new instance of UserConsumer class which implements Consumer interface");
		UserConsumer userConsumer = new UserConsumer();
		users.forEach(userConsumer);
		//
		System.out.println("method 3 - anonymous class which implements Consumer interface");
		users.forEach(new Consumer<User>() {

			@Override
			public void accept(User user) {
				System.out.println(user.getName());
			}
		});
		//
		System.out.println("method 4 - lambda expression");
		users.forEach(u -> System.out.println(u.getName()));
	}
}
