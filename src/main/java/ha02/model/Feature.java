package ha02.model;

import ha02.visitor.Visitor;

public class Feature extends Unit {

	private int storyPoints;


	public Feature(String desc, int storyPoints) {
		super("feature", desc);
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
