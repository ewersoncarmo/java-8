package chapter6;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import domain.User;

public class Chapter6 {

	public static void main(String[] args) {
		User user1 = new User("User 1", 150);
		User user2 = new User("User 2", 120);
		User user3 = new User("User 3", 190);

		List<User> users = Arrays.asList(user1, user2, user3);
		
		System.out.println("method 1 - invoking an void method using lambda expression");
		users.forEach(u -> u.becomeMediator());
		//
		System.out.println("method 2 - invoking an void method using method reference");
		users.forEach(User::becomeMediator);
		//
		System.out.println("method 3 - comparing using method reference");
		users.sort(Comparator.comparingInt(User::getScores).thenComparing(User::getName));
		users.forEach(System.out::println);
		// examples using method reference and lambda expression
		// both are the same
		Consumer<User> consumer1 = User::becomeMediator; // method reference
		Consumer<User> consumer2 = u -> u.becomeMediator(); // lambda expression
		//
		System.out.println("method 4 - using Supplier interface to get a result");
		Supplier<User> supplier = User::new;
		User userSupplier = supplier.get(); // it will return a new instance of User class (using default constructor)
		//
		System.out.println("method 5 - using Function interface to get a result");
		Function<String, User> function = User::new;
		User userFunction = function.apply("User 4"); // it will return a new instance of User class (using constructor with one argument)
		System.out.println(userFunction.getName());
		//
		System.out.println("method 6 - using BiFunction interface to get a result");
		BiFunction<String, Integer, User> biFunction = User::new;
		User userBiFunction = biFunction.apply("User 5", 200); // it will return a new instance of User class (using constructor with two arguments)
		System.out.println(userBiFunction.getName());
	}
}

