package chapter9.mappingpartitioninggrouping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import domain.User;

public class Chapter9 {

	public static void main(String[] args) {
		User user1 = new User("User 1", 150, true);
		User user2 = new User("User 2", 120, true);
		User user3 = new User("User 3", 90);
		User user4 = new User("User 4", 120);
		User user5 = new User("User 5", 100);

		List<User> users = Arrays.asList(user1, user2, user3, user4, user5);
		
		System.out.println("method 1 - collecting streams into map");
		//
		Map<String, User> collect = users.stream()
			.collect(Collectors.toMap(User::getName, Function.identity()));
		
		System.out.println(collect);
		//
		System.out.println("method 2 - grouping and partitioning");
		
		// before java 8
		Map<Integer, List<User>> map1 = new HashMap<>();
		for (User user : users) {
			if (!map1.containsKey(user.getScores())) {
				map1.put(user.getScores(), new ArrayList<>());
			}
			map1.get(user.getScores()).add(user);
		}
		
		System.out.println(map1);
		
		// with java 8
		Map<Integer, List<User>> map2 = new HashMap<>();
		for (User user : users) {
			map2.computeIfAbsent(user.getScores(), u -> new ArrayList<>())
				.add(user);
		}
		
		System.out.println(map1);
		
		// using Collectors.groupingBy
		Map<Integer, List<User>> map3 = users.stream()
			.collect(Collectors.groupingBy(User::getScores));
		
		System.out.println(map3);
		
		// using Collectors.groupingBy with mapping
		Map<Integer, List<String>> map4 = users.stream()
			.collect(Collectors.groupingBy(User::getScores,
					Collectors.mapping(User::getName, Collectors.toList())));

		System.out.println(map4);
		
		// using Collectors.groupingBy with summingInt
		Map<Integer, Integer> map5 = users.stream()
			.collect(Collectors.groupingBy(User::getScores,
					Collectors.summingInt(User::getScores)));

		System.out.println(map5);
		
		// using Collectors.partitioningBy
		Map<Boolean, List<User>> map6 = users.stream()
			.collect(Collectors.partitioningBy(User::isMediator));

		System.out.println(map6);
		
		// using Collectors.partitioningBy with mapping
		Map<Boolean, List<String>> map7 = users.stream()
			.collect(Collectors.partitioningBy(User::isMediator,
					Collectors.mapping(User::getName, Collectors.toList())));

		System.out.println(map7);
		
		// using Collectors.partitioningBy with summingInt
		Map<Boolean, Integer> map8 = users.stream()
			.collect(Collectors.partitioningBy(User::isMediator,
					Collectors.summingInt(User::getScores)));

		System.out.println(map8);
		
		// the partitioningBy is an easiest way to group booleans
		
	}
}
