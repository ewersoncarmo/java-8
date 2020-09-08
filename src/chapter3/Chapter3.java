package chapter3;

public class Chapter3 {

	public static void main(String[] args) {
		System.out.println("method 1 - anonymous class which implements Validator interface");
		Validator<String> validator1 = new Validator<String>() {

			@Override
			public boolean isValid(String value) {
				return value.matches("[0-9]{5}-[0-9]{3}");
			}
		};
		System.out.println(validator1.isValid("87005-002"));
		//
		System.out.println("method 2 - lambda expression");
		Validator<String> validator2 = value -> {
			return value.matches("[0-9]{5}-[0-9]{3}");
		};
		System.out.println(validator2.isValid("87005-002"));
		//
		System.out.println("method 3 - a slightly simpler lambda expression");
		Validator<String> validator3 = value -> value.matches("[0-9]{5}-[0-9]{3}");
		System.out.println(validator3.isValid("87005-002"));
	}
}
