package chapter11.paymentmodel;

import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Client {

	public static void main(String[] args) {
		Customer paul = new Customer("Paul");
		Customer john = new Customer("John Smith");
		Customer peter = new Customer("Peter Lock");
		Customer frank = new Customer("Frank Jr");

		Product queen = new Product("Queen Live", Paths.get("/music/queen-live.mp3"), new BigDecimal(100));
		Product country = new Product("Jorge e Mateus London", Paths.get("/music/jorte-e-mateus-london.mp3"), new BigDecimal(90));
		Product flag = new Product("Brazil Flag", Paths.get("/images/brazil.jpg"), new BigDecimal(50));
		Product beauty = new Product("American Beauty", Paths.get("beauty.mov"), new BigDecimal(150));
		Product avengers = new Product("The Avengers", Paths.get("/movies/avengers.mov"), new BigDecimal(200));
		Product seven = new Product("Seven Pounds", Paths.get("/movies/seven-pounds.mov"), new BigDecimal(100));

		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		LocalDateTime lastMonth = today.minusMonths(1);
		
		Payment payment1 = new Payment(Arrays.asList(queen, country), today, paul);
		Payment payment2 = new Payment(Arrays.asList(queen, flag, seven), yesterday, john);
		Payment payment3 = new Payment(Arrays.asList(beauty, avengers, queen), today, frank);
		Payment payment4 = new Payment(Arrays.asList(queen, country, seven), lastMonth, peter);
		Payment payment5 = new Payment(Arrays.asList(beauty, seven), yesterday, paul);
		List<Payment> payments = Arrays.asList(payment1, payment2, payment3, payment4, payment5);

		BigDecimal monthlyFee = new BigDecimal("99.90");
		
		Subscription subscription1 = new Subscription(monthlyFee, yesterday.minusMonths(5), paul);
		Subscription subscription2 = new Subscription(monthlyFee, yesterday.minusMonths(8), today.minusMonths(1), john);
		Subscription subscription3 = new Subscription(monthlyFee, yesterday.minusMonths(5), today.minusMonths(2), frank);
		List<Subscription> subscriptions = Arrays.asList(subscription1, subscription2, subscription3);
		
		System.out.println("ordering the payments list");
		//
		payments.stream()
			.sorted(Comparator.comparing(Payment::getDate))
			.forEach(System.out::println);
		
		System.out.println("getting the sum of the payment 1");
		//
		BigDecimal totalPricePayment1 = payment1.getProducts().stream()
			.map(Product::getPrice)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		System.out.println(totalPricePayment1);
		
		System.out.println("getting the sum of the all payments");
		//
		BigDecimal totalPriceAllPayments = payments.stream()
			.flatMap(p -> p.getProducts().stream()
					.map(Product::getPrice))
			.reduce(BigDecimal.ZERO, BigDecimal::add);

		System.out.println(totalPriceAllPayments);
		
		System.out.println("getting the quantity of the products");
		//
		Map<Product, Long> countingSoldProducts = payments.stream()
			.flatMap(p -> p.getProducts().stream())
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		
		countingSoldProducts.entrySet().stream()
			.sorted(Comparator.comparing(p -> p.getValue()))
			.forEach(System.out::println);

		System.out.println("getting the quantity of the most sold product");
		//
		countingSoldProducts.entrySet().stream()
			.max(Comparator.comparing(p -> p.getValue()))
			.ifPresent(System.out::println);
		
		System.out.println("getting the value of the products");
		//
		Map<Product, BigDecimal> reducingSoldProducts = payments.stream()
			.flatMap(p -> p.getProducts().stream())
			.collect(Collectors.groupingBy(Function.identity(),
					Collectors.reducing(BigDecimal.ZERO, Product::getPrice, BigDecimal::add)));
		
		reducingSoldProducts.entrySet().stream()
			.sorted(Comparator.comparing(p -> p.getValue()))
			.forEach(System.out::println);
		
		System.out.println("getting the value of the most sold product");
		//
		reducingSoldProducts.entrySet().stream()
			.max(Comparator.comparing(p -> p.getValue()))
			.ifPresent(System.out::println);
		
		System.out.println("getting the products of each customer");
		//
		Map<Customer, List<Payment>> customerPayments = payments.stream()
			.collect(Collectors.groupingBy(Payment::getCustomer));

		Map<Customer, List<Product>> customerProducts = customerPayments.entrySet().stream()
			.collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue().stream()
																.flatMap(p -> p.getProducts().stream())
																.collect(Collectors.toList())));

		customerProducts.entrySet().stream()
			.forEach(System.out::println);
		
		System.out.println("getting the value of the customers");
		//
		Function<Payment, BigDecimal> totalPayment = p -> p.getProducts().stream()
			.map(Product::getPrice)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		Map<Customer, BigDecimal> customerValue = payments.stream()
			.collect(Collectors.groupingBy(Payment::getCustomer, Collectors.reducing(BigDecimal.ZERO, totalPayment, BigDecimal::add)));
		
		customerValue.entrySet().stream()
			.sorted(Comparator.comparing(c -> c.getValue()))
			.forEach(System.out::println);
		
		System.out.println("getting the most valued customer");
		//
		customerValue.entrySet().stream()
			.max(Comparator.comparing(c -> c.getValue()))
			.ifPresent(System.out::println);

		System.out.println("getting payment report per date");
		//
		Map<YearMonth, List<Payment>> paymentReport = payments.stream()
			.collect(Collectors.groupingBy(p -> YearMonth.from(p.getDate())));
		
		paymentReport.entrySet().stream()
			.forEach(System.out::println);
		
		Map<YearMonth, BigDecimal> totalPaymentMonth = payments.stream()
			.collect(Collectors.groupingBy(p -> YearMonth.from(p.getDate()), Collectors.reducing(BigDecimal.ZERO, totalPayment, BigDecimal::add)));
		
		totalPaymentMonth.entrySet().stream()
			.forEach(System.out::println);
		
		System.out.println("calculating dates");
		//
		BigDecimal subscriptionsTotalPaid = subscriptions.stream()
			.map(Subscription::getTotalPaid)
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		System.out.println(subscriptionsTotalPaid);
	}
}
