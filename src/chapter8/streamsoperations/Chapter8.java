package chapter8.streamsoperations;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
		//
		System.out.println("method 2 - reducing elements");
		
		// mapping and getting with sum method
		int sum = users.stream()
			.mapToInt(User::getScores)
			.sum();
		
		System.out.println(sum);
		
		// mapping and getting with reduce method using lambda expression
		int reduce = users.stream()
			.mapToInt(User::getScores)
			.reduce((a, b) -> a + b)
			.getAsInt();
		
		System.out.println(reduce);
		
		// mapping and getting with reduce method using method reference
		int reduce2 = users.stream()
			.mapToInt(User::getScores)
			.reduce(Integer::sum) // the parameter is IntBinaryOperator interface which has a method applyAsInt
			                      // Integer::sum method reference is acceptable here because both have the same signature
			.getAsInt();  
			
		System.out.println(reduce2);
		//
		System.out.println("method 3 - testing predicates");
		
		// testing if ANY user is mediator
		users.stream()
			.anyMatch(User::isMediator);
		
		// testing if ALL users are mediators
		users.stream()
			.allMatch(User::isMediator);
			
		// testing if NONE of the users are mediators
		users.stream()
			.noneMatch(User::isMediator);
		
		//
		System.out.println("method 4 - more operations with streams");
		
		// generating an infinity stream
		Random random = new Random(0);
		
		List<Integer> collect = IntStream
			.generate(() -> random.nextInt())
			.limit(2)
			.boxed()
			.collect(Collectors.toList());
	
		collect.forEach(System.out::println);
		
		// stateful stream with generate
		IntStream
			.generate(new Fibonacci()) // get one instance of the Fibonacci class which is a Supplier
			                           // generate method is executed once
			.limit(10) // limits the generation of the infinity stream to 10 elements
			.forEach(System.out::println); // for each element of the stream executes the method getAsInt of the Supplier implementation
		
		// stateful stream with iterate
		IntStream
			.iterate(0, x -> x + 1) // iterate method is executed for each element
			.limit(10)
			.forEach(System.out::println);
		
		// flatMap
		UserGroup englishSpeakers = new UserGroup();
		englishSpeakers.add(user1);
		englishSpeakers.add(user2);
		
		UserGroup spanishSpeakers = new UserGroup();
		spanishSpeakers.add(user2);
		spanishSpeakers.add(user3);
		
		List<UserGroup> userGroups = Arrays.asList(englishSpeakers, spanishSpeakers);
		
		// map without converting to stream     -> returns Stream<Set<User>> - all users in each group
		// map converting to stream         	-> returns Stream<Stream<User>>
		// flatMap without converting to stream -> compilation error
		// flatMap converting to stream         -> returns Stream<User> - all users of the groups together
		userGroups.stream()
			.flatMap(g -> g.getUsers().stream())
			.distinct()
			.forEach(System.out::println);
	}

}
