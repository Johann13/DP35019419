package ha04;


import java.util.ArrayList;
import java.util.List;

public class Unit extends UnitObservable {

	private String kind;
	private String desc;
	private List<Unit> children;

	public Unit(String kind) {
		super();
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
		notify("kind", kind, this.kind);
		this.kind = kind;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		notify("desc", desc, this.desc);
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
		for (UnitObserver observer : this.getObserverList()) {
			unit.addListener(observer);
		}
		this.children.add(unit);
		notify("addChildren", unit.getId(), this.getId());
	}

	public void addChildren(Unit... units) {
		if (this.children == null) {
			children = new ArrayList<>();
		}
		for (Unit u : units) {
			addChild(u);
		}
	}

	private void removeChild(Unit unit) {
		for (UnitObserver observer : this.getObserverList()) {
			unit.removeListener(observer);
		}
		this.children.remove(unit);
		notify("removeChildren", unit.getId(), this.getId());
	}

	public void removeChildren(Unit... units) {
		if (this.children != null) {
			return;
		}
		for (Unit u : units) {
			removeChild(u);
		}
	}

	@Override
	protected String className() {
		return "ha04.Unit";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Unit) {
			Unit u = (Unit) obj;
			return this.getId().equals(u.getId()) && this.getKind().equals(u.getKind()) && this.getDesc().equals(u.getDesc());
		}
		return false;
	}


}
