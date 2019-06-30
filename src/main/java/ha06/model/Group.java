package ha06.model;
/*
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Group extends Drawable {

	private List<Drawable> children;

	public Group(String id) {
		super(id);
		children = new ArrayList<>();
	}

	public List<Drawable> getChildren() {
		return children;
	}

	public void setChildren(List<Drawable> children) {
		this.children = children;
	}

	private void addChild(Drawable drawable) {
		if (this.children == null) {
			children = new ArrayList<>();
		}
		this.children.add(drawable);
	}

	public void addChildren(Drawable... drawables) {
		for (Drawable drawable : drawables) {
			addChild(drawable);
		}
	}

	public Drawable getChildById(String id) {
		for (Drawable child : children) {
			if (child.getId().equals(id)) {
				return child;
			} else if (child instanceof Group) {
				Drawable drawable = ((Group) child).getChildById(id);
				if (drawable != null) {
					return null;
				}
			}
		}
		return null;
	}

	@Override
	public Group makeCopy() {
		Group group = null;
		try {
			group = this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return group;
	}

	@Override
	protected Group clone() throws CloneNotSupportedException {
		Group group = (Group) super.clone();
		List<Drawable> clones = new ArrayList<>(children.size());
		for (Drawable item : children) {
			clones.add(item.makeCopy());
		}
		group.setChildren(clones);
		return group;
	}

	@Override
	public void draw(GraphicsContext gc) {
		for (Drawable drawable : children) {
			drawable.draw(gc);
		}
	}

	@Override
	public void move(int x, int y) {
		for (Drawable drawable : children) {
			drawable.move(x, y);
		}
	}

	@Override
	public String toString() {
		return "Group: " + getId() + " children: " + children.size();
	}
}
*/