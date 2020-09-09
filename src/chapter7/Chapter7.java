package chapter7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.User;

public class Chapter7 {

	public static void main(String[] args) {
		User user1 = new User("User 1", 150);
		User user2 = new User("User 2", 120);
		User user3 = new User("User 3", 190);

		List<User> users = Arrays.asList(user1, user2, user3);
		
		System.out.println("method 1 - making mediators the two highest rated users");
		
		// before java 8
		Collections.sort(users, new Comparator<User>() {

			@Override
			public int compare(User user1, User user2) {
				return user1.getScores() - user2.getScores();
			}
			
		});
		
		Collections.reverse(users);
		List<User> top2Users = users.subList(0, 2);
		for (User user : top2Users) {
			user.becomeMediator();
		}
		
		// with java 8
		users.sort(Comparator.comparing(User::getScores).reversed());
		users.subList(0, 2).forEach(User::becomeMediator);
		//
		System.out.println("method 2 - making mediators the users with more than one hundred and twenty points");
		
		// before java 8
		for (User user : users) {
			if (user.getScores() > 120) {
				user.becomeMediator();
			}
		}
		
		// with java 8
		users.stream()
			.filter(u -> u.getScores() > 120)
			.forEach(User::becomeMediator);
		
		users.stream()
			.filter(User::isMediator)
			.forEach(System.out::println);
		//
		System.out.println("method 3 - collecting from the stream using the first option of the collect method");
		
		Supplier<ArrayList<User>> supplier = ArrayList::new;
		BiConsumer<ArrayList<User>, User> accumulator = ArrayList::add;
		BiConsumer<ArrayList<User>, ArrayList<User>> combiner = ArrayList::addAll;
		
		ArrayList<User> collect1 = users.stream()
			.filter(u -> u.getScores() > 120)
			.collect(supplier, accumulator, combiner);
		
		collect1.forEach(System.out::println);
		//
		System.out.println("method 4 - collecting from the stream using the second option of the collect method");
		
		// extracting the stream to a List
		List<User> collect2 = users.stream()
			.filter(u -> u.getScores() > 120)
			.collect(Collectors.toList());
		
		collect2.forEach(System.out::println);
		
		// extracting the stream to a HashSet
		HashSet<User> collect3 = users.stream()
			.filter(u -> u.getScores() > 120)
			.collect(Collectors.toCollection(HashSet::new));
			
		collect3.forEach(System.out::println);
		//
		System.out.println("method 5 - mapping objects from the stream");
		
		// before java 8
		List<Integer> scoresList1 = new ArrayList<>();
		users.forEach(u -> scoresList1.add(u.getScores()));
		
		// with java 8
		List<Integer> scoresList2 = users.stream()
			.map(User::getScores)
			.collect(Collectors.toList());
		
		scoresList2.forEach(System.out::println);
		
		// mapping to primitive types
		Stream<Integer> map = users.stream()
			.map(User::getScores); // it might generate an overhead because of the autoboxing from int to Integer
		
		// avoiding autoboxing 
		double average = users.stream()
			.mapToInt(User::getScores) // we can use streams family to handle with primitive types
			.average()
			.orElse(0.0);
		
		System.out.println(average);
		
	}
}
