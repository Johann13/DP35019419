package ha02.model;

import ha02.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Unit {

	private String kind;
	private String desc;
	private List<Unit> children;


	public Unit(String kind) {
		this.kind = kind;
	}

	public Unit(String kind, String desc) {
		this(kind);
		this.desc = desc;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<Unit> getChildren() {
		if (children == null) {
			children = new ArrayList<>();
		}
		return children;
	}

	public void setChildren(List<Unit> children) {
		this.children = children;
	}

	private void addChild(Unit unit) {
		if (this.children == null) {
			children = new ArrayList<>();
		}
		this.children.add(unit);
	}

	public void addChildren(Unit... units) {
		for (Unit u : units) {
			addChild(u);
		}
	}

	private void removeChild(Unit unit) {
		if (this.children != null) {
			this.children.remove(unit);
		}
	}

	public void removeChildren(Unit... units) {
		for (Unit u : units) {
			removeChild(u);
		}
	}

	public int accept(Visitor visitor) {
		return visitor.visit(this);
	}
}
