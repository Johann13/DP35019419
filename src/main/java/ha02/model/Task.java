package ha02.model;

import ha02.visitor.Visitor;

public class Task extends Unit {
	private int storyPoints;

	public Task(String kind, int storyPoints) {
		super(kind);
		this.storyPoints = storyPoints;
	}

	public Task(String kind, String desc, int storyPoints) {
		super(kind, desc);
		this.storyPoints = storyPoints;
	}

	public int getStoryPoints() {
		return storyPoints;
	}

	public void setStoryPoints(int storyPoints) {
		this.storyPoints = storyPoints;
	}

	public int accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
