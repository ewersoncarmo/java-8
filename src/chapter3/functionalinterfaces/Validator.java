package chapter3.functionalinterfaces;

@FunctionalInterface
public interface Validator<T> {

	boolean isValid(T value);
}
