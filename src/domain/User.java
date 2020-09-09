package domain;

public class User {

	private String name;
	private int scores;
	private boolean mediator;
	
	public User() {}
	
	public User(String name) {
		this.name = name;
	}
	
	public User(String name, int scores) {
		this.name = name;
		this.scores = scores;
		this.mediator = false;
	}

	public String getName() {
		return name;
	}

	public int getScores() {
		return scores;
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
