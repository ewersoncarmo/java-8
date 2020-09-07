package chapter2;

import java.util.function.Consumer;

import domain.User;

public class UserConsumer implements Consumer<User> {

	@Override
	public void accept(User user) {
		System.out.println(user.getName());
	}

}
