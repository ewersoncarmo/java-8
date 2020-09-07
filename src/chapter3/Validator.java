package chapter3;

@FunctionalInterface
public interface Validator<T> {

	boolean isValid(T value);
}
