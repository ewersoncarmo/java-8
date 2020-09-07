package chapter3;

public class Chapter3 {

	public static void main(String[] args) {
		System.out.println("method 1");
		Validator<String> validator1 = new Validator<String>() {

			@Override
			public boolean isValid(String value) {
				return value.matches("[0-9]{5}-[0-9]{3}");
			}
		};
		System.out.println(validator1.isValid("87005-002"));
		//
		System.out.println("method 2");
		Validator<String> validator2 = value -> {
			return value.matches("[0-9]{5}-[0-9]{3}");
		};
		System.out.println(validator2.isValid("87005-002"));
		//
		System.out.println("method 3");
		Validator<String> validator3 = value -> value.matches("[0-9]{5}-[0-9]{3}");
		System.out.println(validator3.isValid("87005-002"));
	}
}
