package domain;

public class User {

	private String name;
	private int score;
	private boolean mediator;
	
	public User() {}
	
	public User(String name) {
		this.name = name;
	}
	
	public User(String name, int score) {
		this.name = name;
		this.score = score;
		this.mediator = false;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public void becomeMediator() {
		this.mediator = true;
	}
	
	public boolean isMediator() {
		return mediator;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}

}
