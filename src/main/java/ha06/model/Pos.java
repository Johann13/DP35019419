package ha06.model;

public class Pos implements Cloneable {

	private int x;
	private int y;

	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Pos makeCopy() {
		Pos pos = null;
		try {
			pos = (Pos) this.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return pos;
	}

	public void move(int x, int y) {
		this.x += x;
		this.y += y;
	}

	@Override
	public String toString() {
		return "(" + x + "|" + y + ")";
	}
}
